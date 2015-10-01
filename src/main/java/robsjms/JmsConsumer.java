package robsjms;

import org.springframework.jms.core.JmsTemplate;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;

/**
 * User: robshield
 * Date: 26/12/2011
 * Time: 20:23
 */
public class JmsConsumer{

    private JmsTemplate template;
    private Queue destination;

    public JmsConsumer(){}

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


}

