package com.clubsystem.dao;

import com.clubsystem.bean.User;
import com.clubsystem.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 用户数据访问对象（DAO）。
 */
public class UserDao {
    public User findByUsername(String username) {
        String sql = "SELECT * FROM `user` WHERE username = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            if (conn == null) {
                return null;
            }
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                return convert(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return null;
    }

    public User findByUsernameAndPassword(String username, String password) {
        String sql = "SELECT * FROM `user` WHERE username = ? AND password = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            if (conn == null) {
                return null;
            }
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                return convert(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return null;
    }

    public boolean addUser(User user) {
        String sql = "INSERT INTO `user`(username, password, real_name, student_no, phone, role, create_time) VALUES(?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConnection();
            if (conn == null) {
                return false;
            }
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRealName());
            ps.setString(4, user.getStudentNo());
            ps.setString(5, user.getPhone());
            ps.setString(6, user.getRole());
            ps.setTimestamp(7, new Timestamp(user.getCreateTime().getTime()));
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, null);
        }
        return false;
    }

    private User convert(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setRealName(rs.getString("real_name"));
        user.setStudentNo(rs.getString("student_no"));
        user.setPhone(rs.getString("phone"));
        user.setRole(rs.getString("role"));
        Timestamp ts = rs.getTimestamp("create_time");
        if (ts != null) {
            user.setCreateTime(new Date(ts.getTime()));
        }
        return user;
    }
}
