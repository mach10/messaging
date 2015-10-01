package robsjms;

import javax.jms.*;

/**
 * User: robshield
 * Date: 07/02/2012
 * Time: 21:57
 */
public class Subscriber implements MessageListener{
    Connection connection;
    final String  name;
    
    public Subscriber(String name){
        this.name = name;
    }
    public void startListening() throws JMSException{
        System.out.println("Subscriber.startListening()");

        try {
            ConnectionFactory factory = JmsTwo.getJmsConnectionFactory();
            connection = factory.createConnection();
            Session session = connection.createSession(false,
                    Session.AUTO_ACKNOWLEDGE);
            Topic topic = session
                    .createTopic(JmsTwo.TOPIC1);
            MessageConsumer consumer = session.createConsumer(topic);
            consumer.setMessageListener(this);

            connection.start();
        } catch (JMSException e) {
            System.out.println("Exception occurred: " + e.toString());
        }
    }

    /**
     * Just log a note when a message is received.
     */
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            TextMessage txtMsg = (TextMessage) message;
            try {
                System.out.println("\n "+name+" " + txtMsg.getText() + "\n");
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopListening() {
        try {
            connection.stop();
        } catch (JMSException e) {
            System.out.println(e.getMessage());
        }
    }
}
