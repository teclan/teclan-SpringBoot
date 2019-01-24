package teclan.springboot.controller;

import java.util.ArrayList;
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
@RequestMapping("/api/v1")
public class UserController {

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public JSONObject login(@RequestBody Login login) {

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

    @RequestMapping(value = "/signout",method = RequestMethod.POST)
    public JSONObject signout() {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 200);
        jsonObject.put("data", "测试退出");
        jsonObject.put("message", "退出成功");

        return jsonObject;
    }


    @RequestMapping(value = "/users",method = RequestMethod.POST)
    public JSONObject users(@RequestBody JSONObject parameters) {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 200);
        List<User> users = new ArrayList<>();

        int offset = parameters.getIntValue("offset");
        int limit = parameters.getIntValue("limit");

        users.add(new User("1","A",20));
        users.add(new User("2","B",40));

        List<User> result = new ArrayList<>();
        result.add(users.get(offset));

        jsonObject.put("data", result );

        return jsonObject;
    }

    @RequestMapping(value = "/users/count",method = RequestMethod.POST)
    public JSONObject countUsers() {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 200);
        jsonObject.put("count", 2);

        return jsonObject;
    }

    @RequestMapping(value = "admin/info",method = RequestMethod.POST)
    public JSONObject adminInfo() {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 200);

        JSONObject data = new JSONObject();
        data.put("id",1);
        data.put("name","teclan");

        jsonObject.put("data", data);

        return jsonObject;
    }
}
