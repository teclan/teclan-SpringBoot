package teclan.springboot.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @ClassName: Queue1ReceiverServer
 * @Description: TODO
 * @Author: Teclan
 * @Date: 2019/1/10 10:38
 **/
@Component
public class Queue2ReceiverServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(Queue2ReceiverServer.class);

    private static final String QUEUE_NAME="fanout.queue2";

    // 不会自动创建队列
   // @RabbitListener(queues = QUEUE_NAME)
    // 自动创建队列
//    @RabbitListener(queuesToDeclare = @Queue(QUEUE_NAME))
    @RabbitListener(bindings = @QueueBinding(value = @Queue(QUEUE_NAME),exchange = @Exchange(value = "fanoutExchange",type ="fanout" )))
    @RabbitHandler
    public void receiveMessage(String message) {
        LOGGER.info("\n接收队列({})消息:{}...\n",QUEUE_NAME,message);

    }
}
