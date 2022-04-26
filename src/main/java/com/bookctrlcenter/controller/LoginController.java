package com.bookctrlcenter.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bookctrlcenter.RabbTest.RabbitConf;
import com.bookctrlcenter.Service.UserSer;
import com.bookctrlcenter.api.CommonResult;
import com.bookctrlcenter.api.ResultCode;
import com.bookctrlcenter.dao.UserDao;
import com.bookctrlcenter.entity.User;
import com.bookctrlcenter.redis.RedisService;
import com.bookctrlcenter.util.MD5Util;
import com.bookctrlcenter.util.MsgUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLOutput;


@RestController
public class LoginController {

    @Autowired
    UserSer userSerImp;
    @Autowired
    RedisService redisService;
    public static final int ALIVETIME = 60*30;
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public Object login(@RequestBody User user, HttpServletRequest request) {
        System.out.println("!!!!!!!!!!!!!!!!  " + request.getSession().getId());
        System.out.println(user.getId()+"       "+user.getPassword());
        String token = request.getSession().getId();
        User userDb = new User();
        userDb = userSerImp.login(user);
        if(userDb==null ){
            return CommonResult.validateFailed("该用户不存在！");
        }else {
            System.out.println(userDb.getPhone());
            System.out.println("库中密码" +userDb.getPassword());
            System.out.println("请求密码 "+MD5Util.formPassToDBPass(user.getPassword(),userDb.getSalt()));
            if (userDb.getPassword().equals(MD5Util.formPassToDBPass(user.getPassword(),userDb.getSalt()))){
//            return  jsonObject;
                System.out.println("校验成功");
                redisService.set(request.getSession().getId(),RedisService.beanToString(userDb),ALIVETIME);
                String arr[] = new String[1];
                arr[0] = userDb.getType();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("code",200);
                jsonObject.put("subjects",userDb.getSubject());
                jsonObject.put("id",userDb.getId());
                jsonObject.put("departmentID",userDb.getDepartmentID());
                jsonObject.put("username", userDb.getUsername());
                jsonObject.put("password", userDb.getPassword());
                jsonObject.put("gender", userDb.getGender());
                jsonObject.put("phone", userDb.getPhone());
                jsonObject.put("email", userDb.getEmail());
                jsonObject.put("department", userDb.getDepartment());
                jsonObject.put("type",arr);
                jsonObject.put("token",token);
                jsonObject.put("message","登陆成功");
                return  jsonObject;

            }
            else  {
                return CommonResult.validateFailed("密码输入错误，请重新输入！");
            }
        }

    }

    @RequestMapping(value = "/user/getInfo", method = RequestMethod.POST)
    public Object getInfo(@RequestBody User user) {
//        redisService.get(user.getToken(),User.class);
        System.out.println("getinfo");
        if (user==null||user.getToken()==null)
            return CommonResult.failed(ResultCode.VALIDATE_FAILED);
        System.out.println("redis 获取的user " + redisService.get(user.getToken(),User.class).toString());
        User userDb = new User();
        userDb = redisService.get(user.getToken(),User.class);
        System.out.println("    getinfo执行");
        System.out.println(userDb.getUsername());
        String arr[] = new String[1];
        arr[0] = userDb.getType();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",200);
        jsonObject.put("subjects",userDb.getSubject());
        jsonObject.put("id",userDb.getId());
        jsonObject.put("departmentID",userDb.getDepartmentID());
        jsonObject.put("username", userDb.getUsername());
        jsonObject.put("password", userDb.getPassword());
        jsonObject.put("gender", userDb.getGender());
        jsonObject.put("phone", userDb.getPhone());
        jsonObject.put("email", userDb.getEmail());
        jsonObject.put("department", userDb.getDepartment());
        jsonObject.put("type",arr);
        return  jsonObject;
    }
    @RequestMapping(value = "/user/loginOut", method = RequestMethod.POST)
    public CommonResult loginOut() {
            return CommonResult .success("loginOut");

    }
    @Autowired
    RabbitConf rabbitConf;
    @RequestMapping(value = "/user/test", method = RequestMethod.POST)
    public Object  xx() {
        RabbitTemplate rabbitTemplate = rabbitConf.getRabbitTemplate();
        System.out.println("发送消息~");
        rabbitTemplate.convertAndSend(RabbitConf.TESTQUEUE, MsgUtil.objToMsg(new String("这是一个消息")));
        Object o = null;
        return o;
    }
}
