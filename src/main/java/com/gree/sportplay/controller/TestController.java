package com.gree.sportplay.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//0.用于在项目刚搭建好后 测试前后端是否可以连通 的接口
@RestController
public class TestController {
    @RequestMapping("/test")
    public String test(){
        return "ok";
    }
}
