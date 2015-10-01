package robsjms;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;
import java.util.Date;


/**
 * User: robshield
 * Date: 07/02/2012
 * Time: 21:38
 */
public class Producer implements Runnable{
    boolean bRunning = false;
    boolean bStopped = false;
    private static final double SEND_INTERVAL_MSEC = 500;
    Logger log = LoggerFactory.getLogger("producer");

    @Override
    public void run() {
        int msgCount = 0;
        bRunning = true;
        Connection connection = null;
        while (bRunning) {
            try {
                ++msgCount;
                log.info("Publish.run() - message: " + msgCount);

                ConnectionFactory factory = JmsTwo.getJmsConnectionFactory();
                connection = factory.createConnection();
                connection.start();

                Session session = connection.createSession(false,
                        Session.AUTO_ACKNOWLEDGE); // false=NotTransacted
                Topic topic = session.createTopic(JmsTwo.TOPIC1);

                MessageProducer producer = session.createProducer(topic);
                producer.setTimeToLive(10000);
                producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

                TextMessage message = session
                        .createTextMessage("Here is a message [" + msgCount
                                + "] at: " + new Date());
                producer.send(message);

                //connection.close(); // In a real world application, you may want
                // to keep
                ///connection = null; // the connection open for performance.

                if (bRunning) {
                    Thread.sleep((int) (Math.random() * 2 * SEND_INTERVAL_MSEC));
                }
            } catch (Exception e) {
                System.out.println("Exception occurred: " + e.toString());
            } finally {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (JMSException e) {
                    }
                }
            }
        } // End while

        bStopped = true;
        synchronized (this) {
            notifyAll();
        }
        System.out.println("Publisher.run(). Stopped.");
    }

    public void stopPublishing() {
        bRunning = false;
    }
}

