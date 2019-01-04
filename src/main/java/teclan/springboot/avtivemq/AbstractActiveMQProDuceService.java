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
    public boolean sendQueueMsg(String message) {
        try {
            getJmsMessagingTemplate().convertAndSend(getQueue(), message);
            LOGGER.info("\nActiveMQ({})发送消息{}", getQueueName(), message);
            return true;
        } catch (MessagingException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return false;


//        ConnectionFactory connectionFactory = this.jmsMessagingTemplate.getConnectionFactory();
//        try { //获取连接
//
//            Connection connection = connectionFactory.createConnection();
//            connection.start();
//            //获取session
//            Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
//            // 创建一个消息队列
//
//            Destination destination = session.createQueue(getQueueName());
//            MessageProducer producer = session.createProducer(destination);
//            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
//            TextMessage textMessage = session.createTextMessage(message);
//            //设置延迟时间
//            // 发送
//            producer.send(textMessage);
//            session.commit();
//            producer.close();
//            session.close();
//            connection.close();
//        } catch (Exception e) {
//            e.getMessage();
//        }
//        return true;

    }

    /**
     * 接收消息
     *
     * @param message
     */
    @Override
    public boolean receiveQueueMsg(String message) throws UnImplementException {
        throw new UnImplementException();
    }

    /**
     * 接收消息
     *
     * @param message
     */
    @Override
    public boolean receiveTopicMsg(String message) throws UnImplementException {
        throw new UnImplementException();
    }


    /**
     * 发送消息
     *
     * @param message
     */
    @Override
    public boolean sendTopicMsg(String message) {
        try {
            getJmsMessagingTemplate().convertAndSend(getQueue(), message);
            return true;
        } catch (MessagingException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return false;
    }


    protected boolean receiveQueueMsg(String queueName, String message) {
        LOGGER.info("\n接收到ActiveMQ（{}）消息：{}", queueName, message);
        return true;
    }

    protected boolean receiveTopicMsg(String topicName, String message) {
        LOGGER.info("\n接收到ActiveMQ（{}）消息：{}", topicName, message);
        return true;
    }

}
