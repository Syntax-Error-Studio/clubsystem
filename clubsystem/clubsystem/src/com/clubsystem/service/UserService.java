package com.clubsystem.service;

import com.clubsystem.bean.User;
import com.clubsystem.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 用户业务逻辑层，封装注册和登录相关的业务处理。
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    private String normalize(String value) {
        return value == null ? null : value.trim();
    }

    public boolean canConnectDatabase() {
        return userMapper != null;
    }

    public boolean isUsernameExists(String username) {
        String normalizedUsername = normalize(username);
        if (normalizedUsername == null || normalizedUsername.isEmpty()) {
            return false;
        }
        return userMapper.getUserByUsername(normalizedUsername) != null;
    }

    /**
     * 用户注册逻辑。首先检查账号是否已存在，若存在返回 false；否则保存并返回 true。
     * @param user 用户实体
     * @return 注册是否成功
     */
    public boolean register(User user) {
        if (user == null) {
            return false;
        }

        user.setUsername(normalize(user.getUsername()));
        user.setPassword(normalize(user.getPassword()));
        user.setRealName(normalize(user.getRealName()));
        user.setStudentNo(normalize(user.getStudentNo()));
        user.setPhone(normalize(user.getPhone()));

        if (user.getUsername() == null || user.getUsername().isEmpty()
                || user.getPassword() == null || user.getPassword().isEmpty()
                || user.getRealName() == null || user.getRealName().isEmpty()) {
            return false;
        }

        // 先检查用户名是否已存在
        if (userMapper.getUserByUsername(user.getUsername()) != null) {
            return false; // 用户名已存在
        }
        // 设置注册时间
        user.setCreateTime(new Date());
        // 设置默认角色为学生
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("student");
        }
        // 保存用户
        return userMapper.addUser(user) > 0;
    }

    /**
     * 登录验证逻辑，根据用户名和密码查询用户。
     * @param username 用户名
     * @param password 密码
     * @return 登录成功返回 User 对象，失败返回 null
     */
    public User login(String username, String password) {
        String normalizedUsername = normalize(username);
        String normalizedPassword = normalize(password);
        if (normalizedUsername == null || normalizedPassword == null) {
            return null;
        }

        User user = userMapper.getUserByUsername(normalizedUsername);
        if (user != null && normalizedPassword.equals(user.getPassword())) {
            return user;
        }
        return null;
    }
}