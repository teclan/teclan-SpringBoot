package teclan.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import teclan.springboot.avtivemq.Queue1SenderServer;

import javax.annotation.Resource;

/**
 * @ClassName: ActiveMQController
 * @Description: TODO
 * @Author: Teclan
 * @Date: 2019/1/4 11:12
 **/
@RestController
public class ActiveMQController {

    @Resource
    Queue1SenderServer queue1SenderServer;

    @RequestMapping("/send/queue1")
    public String sendQueue1() {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","teclan");
        queue1SenderServer.sendQueueMsg(JSON.toJSONString(jsonObject));
        return "";
    }
}
