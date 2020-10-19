package com.gree.sportplay.dao;

import com.gree.sportplay.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserDao {

    public User getUserByMessage(@Param("username") String username, @Param("password") String password);

    public List<User> getAllUser(@Param("username") String username, @Param("pageStart") int pageStart, @Param("pageSize") int pageSize);

    public int getUserCounts(@Param("username") String username);

    //修改用户状态
    public int updateState(Integer id, Boolean state);

    //添加用户
    public int addUser(User user);

    //删除用户
    public int deleteUser(int id);

    //修改用户 第一步：获取当前要修改的用户的信息 在前端的修改对话框中展示出来
    public User getUpdateUser(int id);

    //修改用户 第二步：获取到用户对象后 在展示对话框中修改其值 更新数据库中的用户数据
    public int editUser(User user);



}
