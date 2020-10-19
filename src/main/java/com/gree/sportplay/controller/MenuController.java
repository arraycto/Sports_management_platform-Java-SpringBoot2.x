package com.gree.sportplay.controller;

import com.alibaba.fastjson.JSON;
import com.gree.sportplay.bean.MainMenu;
import com.gree.sportplay.dao.MenuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class MenuController {
    @Autowired
    private MenuDao menuDao;

    //2.查询出左侧侧边栏导航目录菜单的接口
    @RequestMapping("/menus")
    public String getAllMenus() {
        System.out.println("------------------这里是com.gree.sportplay.controller.MenuController---------------");
        System.out.println("获取侧边栏导航菜单的请求成功");
        HashMap<String, Object> data = new HashMap<>();
        //int status = 404; //错误 404 成功 200
        List<MainMenu> menus = menuDao.getMenus();
        if(menus!=null){
            data.put("data",menus);
            data.put("status",200);
        }
        else{
            data.put("status",404);
        }
        String res = JSON.toJSONString(data);
        return res;
    }
}
