package com.clubsystem.dao;

import com.clubsystem.bean.Activity;
import com.clubsystem.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 活动数据访问对象（DAO）。
 * 用于对活动表进行增删改查操作。
 * 本示例中仅提供部分方法实现，主要演示如何使用 JDBC 操作数据库。
 */
public class ActivityDao {
    /**
     * 查询所有活动列表。
     * @return 活动列表
     */
    public List<Activity> findAll() {
        List<Activity> list = new ArrayList<>();
        String sql = "SELECT * FROM activity ORDER BY start_time DESC";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(convert(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 根据编号查询活动详情。
     * @param id 活动编号
     * @return 活动实体，若不存在返回 null
     */
    public Activity findById(int id) {
        String sql = "SELECT * FROM activity WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return convert(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 插入新活动。
     * @param activity 活动实体
     * @return 成功返回 true
     */
    public boolean addActivity(Activity activity) {
        String sql = "INSERT INTO activity(category_id, title, content, location, start_time, end_time, deadline, max_count, current_count, creator_id, status, create_time) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, activity.getCategoryId());
            ps.setString(2, activity.getTitle());
            ps.setString(3, activity.getContent());
            ps.setString(4, activity.getLocation());
            ps.setTimestamp(5, new Timestamp(activity.getStartTime().getTime()));
            ps.setTimestamp(6, new Timestamp(activity.getEndTime().getTime()));
            ps.setTimestamp(7, new Timestamp(activity.getDeadline().getTime()));
            ps.setInt(8, activity.getMaxCount());
            ps.setInt(9, activity.getCurrentCount());
            ps.setInt(10, activity.getCreatorId());
            ps.setString(11, activity.getStatus());
            ps.setTimestamp(12, new Timestamp(activity.getCreateTime().getTime()));
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 将结果集转换为 Activity 对象。
     * @param rs 结果集
     * @return Activity 对象
     * @throws SQLException SQL 异常
     */
    private Activity convert(ResultSet rs) throws SQLException {
        Activity activity = new Activity();
        activity.setId(rs.getInt("id"));
        activity.setCategoryId(rs.getInt("category_id"));
        activity.setTitle(rs.getString("title"));
        activity.setContent(rs.getString("content"));
        activity.setLocation(rs.getString("location"));
        Timestamp startTs = rs.getTimestamp("start_time");
        Timestamp endTs = rs.getTimestamp("end_time");
        Timestamp deadlineTs = rs.getTimestamp("deadline");
        Timestamp createTs = rs.getTimestamp("create_time");
        if (startTs != null) activity.setStartTime(new Date(startTs.getTime()));
        if (endTs != null) activity.setEndTime(new Date(endTs.getTime()));
        if (deadlineTs != null) activity.setDeadline(new Date(deadlineTs.getTime()));
        if (createTs != null) activity.setCreateTime(new Date(createTs.getTime()));
        activity.setMaxCount(rs.getInt("max_count"));
        activity.setCurrentCount(rs.getInt("current_count"));
        activity.setCreatorId(rs.getInt("creator_id"));
        activity.setStatus(rs.getString("status"));
        return activity;
    }
}