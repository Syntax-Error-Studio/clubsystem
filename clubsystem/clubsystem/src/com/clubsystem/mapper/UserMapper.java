package com.clubsystem.mapper;

import com.clubsystem.bean.User;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 用户数据访问层 Mapper 接口
 * 使用 MyBatis 框架定义用户的数据库操作接口
 */
public interface UserMapper {
    
    /**
     * 新增用户记录
     * @param user 用户对象
     * @return 影响的行数
     */
    int addUser(User user);
    
    /**
     * 删除指定用户记录
     * @param id 用户编号
     * @return 影响的行数
     */
    int deleteUserById(@Param("id") int id);
    
    /**
     * 查询所有用户
     * @return 用户列表
     */
    List<User> getAllUsers();
    
    /**
     * 根据ID查询用户
     * @param id 用户编号
     * @return 用户对象
     */
    User getUserById(@Param("id") int id);
    
    /**
     * 修改用户信息
     * @param user 用户对象
     * @return 影响的行数
     */
    int updateUser(User user);
    
    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 用户对象
     */
    User getUserByUsername(@Param("username") String username);
    
    /**
     * 根据角色查询用户
     * @param role 用户角色
     * @return 用户列表
     */
    List<User> getUsersByRole(@Param("role") String role);
}

