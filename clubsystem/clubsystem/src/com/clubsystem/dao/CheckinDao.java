package com.clubsystem.dao;

import com.clubsystem.bean.Checkin;
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
 * 签到数据访问对象，用于操作签到表 checkin。
 * 目前仅实现添加签到记录和根据活动查询签到列表。
 */
public class CheckinDao {
    /**
     * 插入签到记录。
     * @param checkin 签到实体
     * @return 成功返回 true
     */
    public boolean addCheckin(Checkin checkin) {
        String sql = "INSERT INTO checkin(signup_id, user_id, activity_id, checkin_time, checkin_status, operator_id) " +
                "VALUES(?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, checkin.getSignupId());
            ps.setInt(2, checkin.getUserId());
            ps.setInt(3, checkin.getActivityId());
            ps.setTimestamp(4, new Timestamp(checkin.getCheckinTime().getTime()));
            ps.setString(5, checkin.getCheckinStatus());
            ps.setInt(6, checkin.getOperatorId());
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 查询某个活动的所有签到记录。
     * @param activityId 活动编号
     * @return 签到记录列表
     */
    public List<Checkin> findByActivityId(int activityId) {
        List<Checkin> list = new ArrayList<>();
        String sql = "SELECT * FROM checkin WHERE activity_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, activityId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Checkin c = new Checkin();
                    c.setId(rs.getInt("id"));
                    c.setSignupId(rs.getInt("signup_id"));
                    c.setUserId(rs.getInt("user_id"));
                    c.setActivityId(rs.getInt("activity_id"));
                    Timestamp ts = rs.getTimestamp("checkin_time");
                    if (ts != null) {
                        c.setCheckinTime(new Date(ts.getTime()));
                    }
                    c.setCheckinStatus(rs.getString("checkin_status"));
                    c.setOperatorId(rs.getInt("operator_id"));
                    list.add(c);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}