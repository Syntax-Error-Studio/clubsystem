package com.clubsystem.mapper;

import com.clubsystem.bean.Signup;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 报名数据访问层 Mapper 接口
 * 使用 MyBatis 框架定义报名的数据库操作接口
 * 支持增、删、查、改四种基本操作
 * 重点体现关联映射 - Signup关联User和Activity
 */
public interface SignupMapper {
    
    /**
     * 新增报名记录
     * @param signup 报名对象
     * @return 影响的行数
     */
    int addSignup(Signup signup);
    
    /**
     * 删除指定报名记录
     * @param id 报名编号
     * @return 影响的行数
     */
    int deleteSignupById(int id);
    
    /**
     * 查询所有报名记录（不含关联对象）
     * @return 报名列表
     */
    List<Signup> getAllSignups();
    
    /**
     * 根据ID查询报名记录（包含关联的User和Activity对象）
     * 体现一对多关联映射：Signup -> User 和 Signup -> Activity
     * @param id 报名编号
     * @return 报名对象（包含关联的User和Activity）
     */
    Signup getSignupById(int id);
    
    /**
     * 修改报名记录
     * @param signup 报名对象
     * @return 影响的行数
     */
    int updateSignup(Signup signup);
    
    /**
     * 根据用户ID查询报名记录（带关联信息）
     * 体现关联映射：检索特定用户的所有报名信息及关联的活动
     * @param userId 用户编号
     * @return 报名列表
     */
    List<Signup> getSignupsByUserId(@Param("userId") int userId);
    
    /**
     * 根据活动ID查询报名记录（带关联信息）
     * 体现关联映射：检索特定活动的所有报名者及用户信息
     * @param activityId 活动编号
     * @return 报名列表
     */
    List<Signup> getSignupsByActivityId(@Param("activityId") int activityId);
    
    /**
     * 根据用户和活动查询报名记录
     * @param userId 用户编号
     * @param activityId 活动编号
     * @return 报名对象，如无则返回null
     */
    Signup getSignupByUserAndActivity(@Param("userId") int userId, @Param("activityId") int activityId);
    
    /**
     * 获取指定活动的报名人数
     * @param activityId 活动编号
     * @return 报名人数
     */
    int getSignupCountByActivityId(@Param("activityId") int activityId);

    int deleteSignupsByActivityId(@Param("activityId") int activityId);
}

