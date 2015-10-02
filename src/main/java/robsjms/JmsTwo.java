package robsjms;

import ch.qos.logback.core.util.StatusPrinter;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.LoggerContext;
import javax.jms.*;

/**
 * User: robshield
 * Date: 07/02/2012
 * Time: 21:02
 * http://jmsexample.zcage.com/index2.html
 */
public class JmsTwo {
    public static final String TOPIC1 = "test.message.queue.name";
    public static void main(String[] args) throws Exception {

        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        // print logback's internal status
        StatusPrinter.print(lc);


        startBroker();
        Producer producer = new Producer();
        Thread producerThread = new Thread(producer);
        producerThread.start(); // Runs as a separate thread
        Thread.sleep(3000);
        Subscriber subscriber = new Subscriber("one ");
        subscriber.startListening();
        Subscriber subscriber1 = new Subscriber("two ");
        subscriber1.startListening();

        // Let the system run for a bit then shut it down nicely
        Thread.sleep(10000);
        producer.stopPublishing();
        subscriber.stopListening();
        subscriber1.stopListening();

        System.out.println("some text here");
        System.exit(0); // Force exit




    }

    private static void startBroker() throws Exception {
        BrokerService broker = new BrokerService();
        broker.addConnector("tcp://localhost:61616");
        broker.setPersistent(false);
        broker.start();
    }
    /**
     * Use the ActiveMQConnectionFactory to get a JMS ConnectionFactory. In an
     * enterprise application this would normally be accessed through JNDI.
     */
    public static ConnectionFactory getJmsConnectionFactory()
            throws JMSException {
        String user = ActiveMQConnection.DEFAULT_USER;
        String password = ActiveMQConnection.DEFAULT_PASSWORD;
        String url = ActiveMQConnection.DEFAULT_BROKER_URL;

        return new ActiveMQConnectionFactory(user, password, url);
    }

}
