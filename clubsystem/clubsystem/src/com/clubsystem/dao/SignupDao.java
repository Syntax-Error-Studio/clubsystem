package com.clubsystem.dao;

import com.clubsystem.bean.Signup;
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
 * 报名数据访问对象，用于操作报名表 signup。
 */
public class SignupDao {
    public boolean addSignup(Signup signup) {
        String sql = "INSERT INTO signup(user_id, activity_id, signup_time, status) VALUES(?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, signup.getUserId());
            ps.setInt(2, signup.getActivityId());
            ps.setTimestamp(3, new Timestamp(signup.getSignupTime().getTime()));
            ps.setString(4, signup.getStatus());
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Signup findByUserAndActivity(int userId, int activityId) {
        String sql = "SELECT * FROM signup WHERE user_id = ? AND activity_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, activityId);
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

    public List<Signup> findByActivityId(int activityId) {
        List<Signup> list = new ArrayList<>();
        String sql = "SELECT * FROM signup WHERE activity_id = ? ORDER BY signup_time DESC";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, activityId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(convert(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Signup> findByUserId(int userId) {
        List<Signup> list = new ArrayList<>();
        String sql = "SELECT * FROM signup WHERE user_id = ? ORDER BY signup_time DESC";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(convert(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private Signup convert(ResultSet rs) throws SQLException {
        Signup signup = new Signup();
        signup.setId(rs.getInt("id"));
        signup.setUserId(rs.getInt("user_id"));
        signup.setActivityId(rs.getInt("activity_id"));
        Timestamp ts = rs.getTimestamp("signup_time");
        if (ts != null) {
            signup.setSignupTime(new Date(ts.getTime()));
        }
        signup.setStatus(rs.getString("status"));
        return signup;
    }
}
