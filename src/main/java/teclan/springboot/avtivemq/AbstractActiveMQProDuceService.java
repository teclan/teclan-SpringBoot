package teclan.springboot.avtivemq;

import org.apache.activemq.ScheduledMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;
import teclan.springboot.exception.UnImplementException;

import javax.annotation.Resource;
import javax.jms.*;

/**
 * @ClassName: AbstractActiveMQProDuceService
 * @Description: TODO
 * @Author: Teclan
 * @Date: 2019/1/4 10:41
 **/
public class AbstractActiveMQProDuceService implements ActiveMQProduceService {
    protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());


    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;

    protected Queue getQueue() {
        return null;
    }

    protected String getQueueName() {
        return null;
    }

    protected Topic getTopic() {
        return null;
    }

    protected String getTopicName() {
        return null;
    }

    protected JmsMessagingTemplate getJmsMessagingTemplate() {
        return jmsMessagingTemplate;
    }


    /**
     * 发送消息
     *
     * @param message
     */
    @Override
    public boolean sendTopicMsg(String message) {
        try {
            getJmsMessagingTemplate().convertAndSend(getTopic(), message);
            LOGGER.info("\nActiveMQ({}:{})发送消息{}", "队列",getTopicName(), message);
        } catch (MessagingException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return false;
    }


    /**
     * 发送消息
     *
     * @param message
     */
    @Override
    public boolean sendQueueMsg(String message) {
        try {
            getJmsMessagingTemplate().convertAndSend(getQueue(), message);
            LOGGER.info("\nActiveMQ({}:{})发送消息{}", "队列",getQueueName(), message);
            return true;
        } catch (MessagingException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return false;
    }


    /**
     * 接收消息
     *
     * @param message
     */
    @Override
    public void receiveQueueMsg(String message) {
        receiveMsg("队列", getQueueName(), message);
    }
    /**
     * 接收消息
     *
     * @param message
     */
    @Override
    public void receiveTopicMsg(String message)  {
        receiveMsg("订阅",getTopicName(),message);
    }


    protected boolean receiveMsg(String type,String name, String message) {
        LOGGER.info("\n接收到ActiveMQ（{}:{}）消息：{}", type,name, message);
        return true;
    }


}
