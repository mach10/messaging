package robsjms;

import org.apache.activemq.broker.Broker;
import org.apache.activemq.network.jms.JmsMesageConvertor;
import org.apache.activemq.xbean.XBeanBrokerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.apache.activemq.spring.SpringBrokerContext;
import org.springframework.jms.core.MessageCreator;

import javax.jms.*;
import java.util.Enumeration;

/**
 * User: robshield
 * Date: 26/12/2011
 * Time: 18:53
 */


public class JmsOne {
    
    public static void main(String... args) throws JMSException, InterruptedException {

        ApplicationContext context = new ClassPathXmlApplicationContext("jmsContext.xml");
        String x = (String)context.getBean("test");

        JmsTemplate jmsTemplate = (JmsTemplate)context.getBean("myJmsTemplate");


        XBeanBrokerService broker = (XBeanBrokerService)context.getBean("broker");

        JmsProducer producer = (JmsProducer)context.getBean("producer");
        String dest = producer.getDestination().getQueueName();
        MessageCreator creator = new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage message = session.createTextMessage("gosh gosh");
                message.setIntProperty("messageCount",1);
                return message;
            }
        }  ;
        producer.getTemplate().send(dest,creator);

        JmsConsumer consumer = (JmsConsumer)context.getBean("consumer");
        consumer.getTemplate().setDefaultDestinationName(dest);
        Thread.sleep(5000l);
        TextMessage m = (TextMessage)consumer.getTemplate().receive() ;

        System.out.println("message = "+m.getText());

    }
}
