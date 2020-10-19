package com.gree.sportplay.controller;

import com.alibaba.fastjson.JSON;
import com.gree.sportplay.bean.User;
import com.gree.sportplay.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController  //规定整个controller的返回值都是string类型的
public class LoginController {
    @Autowired
    private UserDao userDao;

    //1.用户登录后根据用户输入的username和password查出用户实体对象user的接口
    @RequestMapping("/login")
    public String login( @RequestBody User user){
        //参数中的user对象里即存放了前端登录表单中用户输入的username和password
        //打印如下：
        //user:User{id=0, username='admin', password='123456', email='null', role='null', state=false}
        //user:User{id=0, username='xiaozherui', password='sunyadie', email='null', role='null', state=false}
       // System.out.println("user:"+user);
        //然后再controller层中调用Dao层完成数据库的操作 根据数据库操作的结果反馈给前台页面
        String flag = "error";
        User us = userDao.getUserByMessage(user.getUsername(),user.getPassword());
        System.out.println("从数据库中通过username和password查到的user对象:"+us);
        //us为user:User{id=1, username='admin', password='123456', email='123@qq.com', role='超级管理员', state=true}
        HashMap<String,Object> res = new HashMap<>();
        if(us!=null){
            flag = "ok";
        }
        res.put("flag",flag);
        res.put("user",us);
        String res_json = JSON.toJSONString(res); //把res对象转换为json字符串
        System.out.println("登录时返给前端的JSON数据："+res_json);
        return res_json; //返回json字符串
    }

}
