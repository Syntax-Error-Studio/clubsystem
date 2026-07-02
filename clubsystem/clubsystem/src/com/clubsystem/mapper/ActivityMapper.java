package com.clubsystem.mapper;

import com.clubsystem.bean.Activity;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 活动数据访问层 Mapper 接口
 * 使用 MyBatis 框架定义活动的数据库操作接口
 * 支持增、删、查、改四种基本操作
 */
public interface ActivityMapper {
    
    /**
     * 新增活动记录
     * @param activity 活动对象
     * @return 影响的行数
     */
    int addActivity(Activity activity);
    
    /**
     * 删除指定活动记录
     * @param id 活动编号
     * @return 影响的行数
     */
    int deleteActivityById(@Param("id") int id);
    
    /**
     * 查询所有活动记录
     * @return 活动列表
     */
    List<Activity> getAllActivities();
    
    /**
     * 根据ID查询活动记录
     * @param id 活动编号
     * @return 活动对象
     */
    Activity getActivityById(@Param("id") int id);
    
    /**
     * 修改活动记录
     * @param activity 活动对象
     * @return 影响的行数
     */
    int updateActivity(Activity activity);
    
    /**
     * 根据分类查询活动
     * @param categoryId 分类编号
     * @return 活动列表
     */
    List<Activity> getActivitiesByCategory(@Param("categoryId") int categoryId);
    
    /**
     * 检查是否达到最大报名人数
     * @param activityId 活动编号
     * @return true 已满，false 未满
     */
    boolean isActivityFull(@Param("activityId") int activityId);

    /**
     * 活动报名人数加 1。
     * @param id 活动编号
     * @return 影响行数
     */
    int increaseCurrentCount(@Param("id") int id);
}

