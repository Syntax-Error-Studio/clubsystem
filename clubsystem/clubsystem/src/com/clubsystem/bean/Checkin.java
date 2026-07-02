package com.clubsystem.bean;

import java.util.Date;

/**
 * 签到实体类，对应于签到表 checkin。
 * 用于记录报名用户的签到信息。
 */
public class Checkin {
    private int id;             // 签到编号
    private int signupId;       // 报名编号
    private int userId;         // 用户编号
    private int activityId;     // 活动编号
    private Date checkinTime;   // 签到时间
    private String checkinStatus; // 签到状态
    private int operatorId;     // 操作管理员编号

    public Checkin() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSignupId() {
        return signupId;
    }

    public void setSignupId(int signupId) {
        this.signupId = signupId;
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

    public Date getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(Date checkinTime) {
        this.checkinTime = checkinTime;
    }

    public String getCheckinStatus() {
        return checkinStatus;
    }

    public void setCheckinStatus(String checkinStatus) {
        this.checkinStatus = checkinStatus;
    }

    public int getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(int operatorId) {
        this.operatorId = operatorId;
    }
}