package com.gree.sportplay.bean;

/**
 * 用户实体
 */
public class User {

    private int id;
    private String username; //用户名
    private String password; //密码
    private String email;  //邮箱
    private String role;   //角色
    private boolean state; //状态

    //Alt+Insert 快捷键可生成构造方法
    public User() {
    }



    public int getId() {
        return id;
    }
    //Alt+Insert 快捷键 然后选择constructor 按下shift多选除了id主键外的字段
    //通常创建的是没有id主键的构造 因为主键是数据库标识自增的字段
    public User(String username, String password, String email, String role, boolean state) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.state = state;
    }
    //Alt+Insert 快捷键 选择getter和setter生成get set方法
    //这个工具自动生成的时候有时候会帮你改名字有些坑 一定要注意 要把isState()自己改成getState()
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean getState() {
        return state;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", state=" + state +
                '}';
    }
}
