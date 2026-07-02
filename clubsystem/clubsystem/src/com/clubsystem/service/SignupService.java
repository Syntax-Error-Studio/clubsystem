package com.clubsystem.service;

import com.clubsystem.bean.Activity;
import com.clubsystem.bean.Signup;
import com.clubsystem.mapper.ActivityMapper;
import com.clubsystem.mapper.CheckinMapper;
import com.clubsystem.mapper.SignupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 报名业务逻辑层。
 */
@Service
public class SignupService {
    @Autowired
    private SignupMapper signupMapper;

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private CheckinMapper checkinMapper;

    @Transactional
    public boolean signupActivity(int userId, int activityId) {
        Signup existing = signupMapper.getSignupByUserAndActivity(userId, activityId);
        if (existing != null) {
            return false;
        }

        Activity activity = activityMapper.getActivityById(activityId);
        if (activity == null || activityMapper.isActivityFull(activityId)) {
            return false;
        }

        Signup signup = new Signup();
        signup.setUserId(userId);
        signup.setActivityId(activityId);
        signup.setSignupTime(new Date());
        signup.setStatus("已报名");
        boolean success = signupMapper.addSignup(signup) > 0;
        if (success) {
            int rows = activityMapper.increaseCurrentCount(activityId);
            if (rows <= 0) {
                throw new IllegalStateException("更新活动报名人数失败");
            }
        }
        return success;
    }

    public List<Signup> listSignupsByActivity(int activityId) {
        return signupMapper.getSignupsByActivityId(activityId);
    }

    public List<Signup> listSignupsByUser(int userId) {
        return signupMapper.getSignupsByUserId(userId);
    }

    @Transactional
    public boolean cancelSignup(int signupId) {
        checkinMapper.deleteCheckinBySignupId(signupId);
        return signupMapper.deleteSignupById(signupId) > 0;
    }

    public Signup getSignupById(int id) {
        return signupMapper.getSignupById(id);
    }
}
