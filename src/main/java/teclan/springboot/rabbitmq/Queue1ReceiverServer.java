package teclan.springboot.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName: Queue1ReceiverServer
 * @Description: TODO
 * @Author: Teclan
 * @Date: 2019/1/10 10:38
 **/
@Component
public class Queue1ReceiverServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(Queue1ReceiverServer.class);

    private static final String QUEUE_NAME="queue1";

    // 不会自动创建队列
   // @RabbitListener(queues = QUEUE_NAME)
    // 自动创建队列
    @RabbitListener(queuesToDeclare = @Queue(QUEUE_NAME))
    @RabbitHandler
    public void receiveMessage(String message) {
        LOGGER.info("\n接收队列({})消息:{}...\n",QUEUE_NAME,message);

    }
}
