package com.clubsystem.bean;

import java.util.Date;

/**
 * 公告实体类，对应于公告表 notice。
 * 用于发布活动通知或系统公告。
 */
public class Notice {
    private int id;            // 公告编号
    private String title;      // 公告标题
    private String content;    // 公告内容
    private int publisherId;   // 发布人编号
    private Date createTime;   // 发布时间
    private String status;     // 公告状态（有效/无效）

    public Notice() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}