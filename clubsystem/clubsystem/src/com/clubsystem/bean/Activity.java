package com.clubsystem.bean;

import java.util.Date;

/**
 * 活动实体类，对应于活动表 activity。
 * 包含活动的基本属性，如标题、分类、时间、地点、人数限制等。
 * 为简化作业示例，当前类仅提供属性和 getter/setter，没有业务方法。
 */
public class Activity {
    private int id;              // 活动编号
    private int categoryId;      // 活动分类编号
    private String title;        // 活动标题
    private String content;      // 活动简介
    private String location;     // 活动地点
    private Date startTime;      // 开始时间
    private Date endTime;        // 结束时间
    private Date deadline;       // 报名截止时间
    private int maxCount;        // 最大报名人数
    private int currentCount;    // 当前报名人数
    private int creatorId;       // 发布人编号（外键）
    private String status;       // 活动状态：未开始/报名中/已结束
    private String coverImage;   // 封面图片路径
    private Date createTime;     // 创建时间

    public Activity() {
    }

    // 以下为属性的 getter 和 setter 方法
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    public int getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(int currentCount) {
        this.currentCount = currentCount;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}