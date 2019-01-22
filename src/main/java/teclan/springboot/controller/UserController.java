package teclan.springboot.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import teclan.springboot.model.Login;
import teclan.springboot.model.User;

@RestController
public class UserController {

    @RequestMapping(value = "/api/v1/login",method = RequestMethod.POST)
    public JSONObject query(@RequestBody Login login) {

        JSONObject jsonObject = new JSONObject();

        if(!"teclan".equals(login.getAccountId())){
            jsonObject.put("code", 401);
        }else {
            jsonObject.put("code", 200);
        }
        jsonObject.put("data", "测试登录");
        jsonObject.put("message", "登录成功");
        jsonObject.put("token", "123456789");

        return jsonObject;
    }
}
