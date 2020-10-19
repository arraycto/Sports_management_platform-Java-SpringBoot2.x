package com.gree.sportplay.controller;

import com.alibaba.fastjson.JSON;
import com.gree.sportplay.bean.QueryInfo;
import com.gree.sportplay.bean.User;
import com.gree.sportplay.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDao userDao;

    //3.查询出用户列表及模糊查询的接口
    @RequestMapping("/allUser")
    //一些未 解决的疑问！！！
    //？？？后端接收前端的形参前如果加了@RequestBody注解  前端传递的时候就可以直接丢数据对象进去 而不用在对象外套{params:数据对象}
    //？？？对比下边添加用户方法的形参前不加@RequestBody注解的情况 这时前端传递对象得把对象包裹起来{params:数据对象}
    //前端传递参数不能再外边加{params:对象}
    public String getUserList( @RequestBody QueryInfo queryInfo){
        System.out.println("前端发送请求时传递给后端的数据为{params: this.queryInfo},后端接收到的数据为："+queryInfo);
        int numbers = userDao.getUserCounts("%"+queryInfo.getQuery()+"%");// 获取数据总数
        int pageStart = (queryInfo.getPageNum()-1)*queryInfo.getPageSize();
        List<User> users = userDao.getAllUser("%"+queryInfo.getQuery()+"%",pageStart,queryInfo.getPageSize());
        System.out.println("从数据库中查到的User对象集合："+users);
        HashMap<String, Object> res = new HashMap<>();
        res.put("numbers",numbers);
        res.put("data",users);
        System.out.println("查询出的用户总数："+numbers);
        String users_json = JSON.toJSONString(res);
        System.out.println("查询时返给前端的JSON数据："+users_json);
        System.out.println("----------------------------------------分隔符-----------------------------------------");
        return users_json;
    }
    //4.修改用户状态的接口
    @RequestMapping("/userState")
    public String updateUserState(@RequestParam("id") Integer  id,
                                  @RequestParam("state") Boolean state){
        System.out.println("后端收到了前端修改用户状态的请求，请求提交的参数如下：");
        System.out.println("前端传给后端的id="+id+"  前端传给后端的state="+state);
        int i = userDao.updateState(id, state);
        String str = i > 0 ? "success" : "error";
        System.out.println("后端返给前端的数据为："+str);
        return str;
    }

    //5.添加用户的接口(前端表单提交传给后端的user中只有username,password,email 没有role和state)
    @RequestMapping("/addUser")
    //为什么形参前必须加 @RequestBody注解才能接收到前端数据的传递？？？ 去掉就会报错500
    public String addUser( @RequestBody User user){
        //将新添加的用户的角色默认设为普通用户，状态设为false
        System.out.println("后端收到了前端添加用户的请求，请求提交的参数如下：");
        System.out.println("前端传给后端的数据为："+user);
        user.setRole("普通用户");
        user.setState(false);
        int i = userDao.addUser(user);
        String str = i > 0 ? "success" : "error";
        System.out.println("后端返给前端的数据为："+str);
        return str;
    }
    //6.删除用户的接口（通过主键id删除）
    @RequestMapping("/deleteUser")
    public String deleteUser(int id){
        System.out.println("后端收到了前端删除用户的请求，请求提交的参数如下：");
        System.out.println("前端传给后端的数据为："+id);
        int i = userDao.deleteUser(id);
        String str = i > 0 ? "success" : "error";
        System.out.println("前端请求！！删除用户！！后，后端返给前端的数据为："+str);
        return str;
    }

    //7.修改用户时 查询出当前用户信息的接口
    @RequestMapping("/getUpdateUser")
    public String getUpdateUser(int id){
        System.out.println("后端收到了前端获取当前修改用户信息的请求，请求提交的参数如下：");
        System.out.println("前端传给后端的数据为："+id);
        User user = userDao.getUpdateUser(id);
        String res = JSON.toJSONString(user);
        System.out.println("前端请求！！获取当前修改用户的信息！！后，后端返给前端的数据为："+res);
        return res;
    }

    //8.修改用户时 修改数据库中的用户数据的接口
    @RequestMapping("/editUser")
    // 如果要前端传过来的是一个封装起来的对象而不是 上边接口 int id 这种
    // 就要在形参前加上注解@RequestBody User user
    // 而前端在传数据的时候 就只用把一个 字段和下边形参User实体中的字段 完全一致的对象传过来就行了
    public String editUser(@RequestBody User user){
        System.out.println("后端收到了前端修改数据库中用户数据的请求，请求提交的参数如下：");
        System.out.println("前端传给后端的数据为："+user);
        int i = userDao.editUser(user);
        String str = i > 0 ? "success" : "error";
        System.out.println("前端请求！！修改数据库中用户数据！！后，后端返给前端的数据为："+str);
        return str;
    }

}
