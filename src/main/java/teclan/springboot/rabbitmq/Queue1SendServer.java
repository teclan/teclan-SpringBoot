package teclan.springboot.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName: Queue1SendServer
 * @Description: TODO
 * @Author: Teclan
 * @Date: 2019/1/10 10:34
 **/
@Component
public class Queue1SendServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(Queue1SendServer.class);

    @Autowired
    private AmqpTemplate rabbitTemplate;

    private static final String QUEUE_NAME="queue1";


    public void sendMessage(Object message) {
        LOGGER.info("\n发送队列({})消息:{}...\n",QUEUE_NAME,message);
        this.rabbitTemplate.convertAndSend(QUEUE_NAME, message);
    }
}
