package robsjms;

import org.springframework.jms.core.JmsTemplate;

import javax.jms.Queue;

/**
 * User: robshield
 * Date: 26/12/2011
 * Time: 20:17
 */
public class JmsProducer {

    private JmsTemplate template;
    private Queue destination;
    private int messageCount;

    public JmsProducer(){}

    public void setMessageCount(int messageCount) {
        this.messageCount = messageCount;
    }

    public void setDestination(Queue destination) {
        this.destination = destination;
    }

    public void setTemplate(JmsTemplate template) {
        this.template = template;
    }

    public JmsTemplate getTemplate() {
        return template;
    }

    public Queue getDestination() {
        return destination;
    }

    public int getMessageCount() {
        return messageCount;
    }
}
