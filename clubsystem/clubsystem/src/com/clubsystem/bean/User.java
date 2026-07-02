package com.clubsystem.bean;

import java.util.Date;

/**
 * 用户实体类，对应于用户表 user。
 * 包含学生用户、社团管理员以及系统管理员三种角色。
 * 注释全部采用中文，便于中国开发者阅读和维护。
 */
public class User {
    private int id;            // 用户编号
    private String username;   // 登录账号
    private String password;   // 登录密码
    private String realName;   // 真实姓名
    private String studentNo;  // 学号
    private String phone;      // 手机号码
    private String role;       // 用户角色：student/admin/superadmin
    private Date createTime;   // 注册时间

    public User() {
    }

    // 全参构造函数，便于快速创建对象
    public User(int id, String username, String password, String realName,
                String studentNo, String phone, String role, Date createTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.realName = realName;
        this.studentNo = studentNo;
        this.phone = phone;
        this.role = role;
        this.createTime = createTime;
    }

    // 以下为各属性的 getter 和 setter 方法
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}