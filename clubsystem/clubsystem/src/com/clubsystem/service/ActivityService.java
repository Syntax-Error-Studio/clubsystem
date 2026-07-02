package com.clubsystem.service;

import com.clubsystem.bean.Activity;
import com.clubsystem.mapper.ActivityMapper;
import com.clubsystem.mapper.CheckinMapper;
import com.clubsystem.mapper.SignupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 活动业务逻辑层，封装活动相关的业务处理。
 */
@Service
public class ActivityService {
    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private SignupMapper signupMapper;

    @Autowired
    private CheckinMapper checkinMapper;

    /**
     * 查询所有活动。
     * @return 活动列表
     */
    public List<Activity> listAllActivities() {
        return activityMapper.getAllActivities();
    }

    /**
     * 根据编号查询活动详情。
     * @param id 活动编号
     * @return 活动实体
     */
    public Activity getActivityById(int id) {
        return activityMapper.getActivityById(id);
    }

    /**
     * 新增活动。
     * @param activity 活动实体
     * @return 是否成功
     */
    public boolean addActivity(Activity activity) {
        return activityMapper.addActivity(activity) > 0;
    }

    public boolean isActivityFull(int activityId) {
        return activityMapper.isActivityFull(activityId);
    }

    public int increaseCurrentCount(int activityId) {
        return activityMapper.increaseCurrentCount(activityId);
    }

    public boolean updateActivity(Activity activity) {
        return activityMapper.updateActivity(activity) > 0;
    }

    /**
     * 删除活动（级联删除关联的签到和报名记录）。
     * @param id 活动编号
     * @return 是否成功
     */
    @Transactional
    public boolean deleteActivity(int id) {
        checkinMapper.deleteCheckinsByActivityId(id);
        signupMapper.deleteSignupsByActivityId(id);
        return activityMapper.deleteActivityById(id) > 0;
    }
}