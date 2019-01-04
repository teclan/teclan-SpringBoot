package teclan.springboot.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.SimpleMessageListenerContainer;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.util.ErrorHandler;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: ActiveMQConfig
 * @Description: TODO
 * @Author: Teclan
 * @Date: 2019/1/4 10:25
 **/
@Configuration
@EnableJms
public class ActiveMQConfig {

    @Value("${activemq.broker-url}")
    private String brokerUrl;
    @Value("${activemq.user}")
    private String usrName;
    @Value("${activemq.password}")
    private String password;

    @Value("${activemq.queueName1}")
    private String queue1;
    @Value("${activemq.queueName2}")
    private String queue2;

    @Value("${activemq.topicName1}")
    private String topic1;
    @Value("${activemq.topicName2}")
    private String topic2;


    @Bean(name = "queue1")
    public Queue getQueue1() {
        return new ActiveMQQueue(queue1);
    }

    @Bean(name = "queue2")
    public Queue getQueue2() {
        return new ActiveMQQueue(queue2);
    }

    @Bean(name = "topic1")
    public Topic getTopic1() {
        return new ActiveMQTopic(topic1);
    }

    @Bean(name = "topic2")
    public Topic getTopic2() {
        return new ActiveMQTopic(topic2);
    }


    @Autowired
    ActiveMQErrorHandler activeMQErrorHandler;

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(usrName, password, brokerUrl);
        return activeMQConnectionFactory;
    }



    // topic模式的ListenerContainer
    @Bean(name = "jmsListenerContainerTopic")
    public JmsListenerContainerFactory<?> jmsListenerContainerTopic(ConnectionFactory activeMQConnectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setPubSubDomain(true);
        bean.setConnectionFactory(activeMQConnectionFactory);
        return bean;
    }

    // queue模式的ListenerContainer
    @Bean(name = "jmsListenerContainerQueue")
    public JmsListenerContainerFactory  getJmsListenerContainerQueue(ConnectionFactory activeMQConnectionFactory,MessageListenerAdapter messageListenerAdapter) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setConnectionFactory(activeMQConnectionFactory);
        bean.setErrorHandler(activeMQErrorHandler);
        bean.setPubSubDomain(false);
        //bean.setMessageListener(messageListenerAdapter);
        return bean;
    }


    @Bean
    public JmsMessagingTemplate getJmsMessagingTemplate(ActiveMQConnectionFactory connectionFactory) {
        return new JmsMessagingTemplate(connectionFactory);
    }

    @Bean
    public MessageListenerAdapter getMessageListenerAdapter(){
        MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter();
        messageListenerAdapter.setDefaultResponseQueueName("response-queue");
        messageListenerAdapter.setDefaultResponseTopicName("response-topic");
        return messageListenerAdapter;
    }


    @Bean
    public JmsTemplate getJmsTemplate(ActiveMQConnectionFactory connectionFactory) {
        return new JmsTemplate (connectionFactory);
    }

}
