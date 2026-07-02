package com.clubsystem.mapper;

import com.clubsystem.bean.Activity;
import com.clubsystem.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 活动Mapper单元测试类
 * 测试活动的增、删、查、改功能
 */
public class ActivityMapperTest {

    private SqlSession session;
    private ActivityMapper activityMapper;

    @Before
    public void setUp() {
        session = MyBatisUtil.getSession(true);
        activityMapper = session.getMapper(ActivityMapper.class);
    }

    @After
    public void tearDown() {
        if (session != null) {
            session.close();
        }
    }

    /**
     * 测试新增活动
     */
    @Test
    public void testAddActivity() {
        System.out.println("========== 测试新增活动 ==========");
        Activity activity = new Activity();
        activity.setCategoryId(1);
        activity.setTitle("学术讲座");
        activity.setContent("邀请学术大牛进行讲座");
        activity.setLocation("报告厅");
        activity.setStartTime(new Date());
        activity.setEndTime(new Date());
        activity.setDeadline(new Date());
        activity.setMaxCount(100);
        activity.setCurrentCount(0);
        activity.setCreatorId(1);
        activity.setStatus("报名中");
        activity.setCreateTime(new Date());

        int result = activityMapper.addActivity(activity);
        System.out.println("新增活动结果：" + (result > 0 ? "成功" : "失败"));
        assertTrue("新增活动应该返回1", result == 1);
        System.out.println("活动ID：" + activity.getId());
    }

    /**
     * 测试查询所有活动
     */
    @Test
    public void testGetAllActivities() {
        System.out.println("========== 测试查询所有活动 ==========");
        List<Activity> activities = activityMapper.getAllActivities();
        System.out.println("活动总数：" + activities.size());
        for (Activity activity : activities) {
            System.out.println("活动ID: " + activity.getId() + 
                             ", 标题: " + activity.getTitle() + 
                             ", 状态: " + activity.getStatus());
        }
        assertTrue("应该至少有一个活动", activities.size() > 0);
    }

    /**
     * 测试根据ID查询活动
     */
    @Test
    public void testGetActivityById() {
        System.out.println("========== 测试根据ID查询活动 ==========");
        Activity activity = activityMapper.getActivityById(1);
        if (activity != null) {
            System.out.println("活动标题：" + activity.getTitle());
            System.out.println("活动地点：" + activity.getLocation());
            System.out.println("最大报名人数：" + activity.getMaxCount());
            System.out.println("当前报名人数：" + activity.getCurrentCount());
            assertNotNull("查询结果不应为空", activity);
        } else {
            System.out.println("未找到该活动");
        }
    }

    /**
     * 测试更新活动
     */
    @Test
    public void testUpdateActivity() {
        System.out.println("========== 测试更新活动 ==========");
        Activity activity = activityMapper.getActivityById(1);
        if (activity != null) {
            System.out.println("原活动状态：" + activity.getStatus());
            activity.setStatus("已结束");
            activity.setCurrentCount(50);
            int result = activityMapper.updateActivity(activity);
            System.out.println("更新结果：" + (result > 0 ? "成功" : "失败"));
            assertTrue("更新活动应该返回1", result == 1);
            
            // 验证更新
            Activity updated = activityMapper.getActivityById(1);
            System.out.println("更新后活动状态：" + updated.getStatus());
            assertEquals("活动状态应该更新", "已结束", updated.getStatus());
        }
    }

    /**
     * 测试删除活动
     */
    @Test
    public void testDeleteActivity() {
        System.out.println("========== 测试删除活动 ==========");
        // 先添加一个临时活动用于测试删除
        Activity activity = new Activity();
        activity.setCategoryId(1);
        activity.setTitle("待删除的活动");
        activity.setContent("这是一个测试活动");
        activity.setLocation("待删除");
        activity.setStartTime(new Date());
        activity.setEndTime(new Date());
        activity.setDeadline(new Date());
        activity.setMaxCount(50);
        activity.setCurrentCount(0);
        activity.setCreatorId(1);
        activity.setStatus("报名中");
        activity.setCreateTime(new Date());

        activityMapper.addActivity(activity);
        int activityId = activity.getId();
        System.out.println("创建待删除活动，ID：" + activityId);

        // 验证活动存在
        Activity found = activityMapper.getActivityById(activityId);
        assertNotNull("活动应该存在", found);

        // 删除活动
        int result = activityMapper.deleteActivityById(activityId);
        System.out.println("删除结果：" + (result > 0 ? "成功" : "失败"));
        assertTrue("删除活动应该返回1", result == 1);

        // 验证已删除
        Activity deleted = activityMapper.getActivityById(activityId);
        assertNull("活动应该已删除", deleted);
    }
}

