package teclan.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import teclan.springboot.rabbitmq.Queue1SendServer;

/**
 * @ClassName: RabbitMQController
 * @Description: TODO
 * @Author: Teclan
 * @Date: 2019/1/10 10:42
 **/
@RestController
public class RabbitMQController {

    @Autowired
    private Queue1SendServer queue1SendServer;

    @RequestMapping(value = "send/queue1", method = RequestMethod.POST)
    public String sendQueue1(String msg) {
        queue1SendServer.sendMessage(msg);
        return "200";
    }
}
