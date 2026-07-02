package com.clubsystem.bean;

import java.util.Date;

/**
 * 报名实体类，对应于报名表 signup。
 * 记录学生报名活动的信息及状态。
 * 包含关联的User和Activity对象，用于体现MyBatis的关联映射。
 */
public class Signup {
    private int id;           // 报名编号
    private int userId;       // 用户编号
    private int activityId;   // 活动编号
    private Date signupTime;  // 报名时间
    private String status;    // 报名状态：已报名/已取消/已签到/缺席
    
    // 关联对象 - 体现多对一关联映射
    private User user;        // 关联的用户对象
    private Activity activity; // 关联的活动对象

    public Signup() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public Date getSignupTime() {
        return signupTime;
    }

    public void setSignupTime(Date signupTime) {
        this.signupTime = signupTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}