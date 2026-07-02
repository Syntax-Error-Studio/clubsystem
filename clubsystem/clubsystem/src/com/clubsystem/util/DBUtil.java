package com.clubsystem.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 数据库连接工具类。
 */
public class DBUtil {
    private static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    private static volatile String LAST_ERROR = "";

    // 可通过环境变量覆盖：DB_URL / DB_USER / DB_PASSWORD
    private static final String DEFAULT_DB_URL = "jdbc:mysql://127.0.0.1:3306/clubsystem?useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8&allowPublicKeyRetrieval=true";
    private static final String DEFAULT_DB_USER = "root";
    private static final String DEFAULT_DB_PASSWORD = "123456";

    private static final String DB_URL = readConfig("DB_URL", DEFAULT_DB_URL);
    private static final String DB_USER = readConfig("DB_USER", DEFAULT_DB_USER);
    private static final String DB_PASSWORD = readConfig("DB_PASSWORD", DEFAULT_DB_PASSWORD);

    static {
        try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC驱动未找到，请检查 mysql-connector 依赖是否已进入 WEB-INF/lib", e);
        }
    }

    private static String readConfig(String key, String defaultValue) {
        String env = System.getenv(key);
        if (env != null && !env.trim().isEmpty()) {
            return env.trim();
        }
        String prop = System.getProperty(key);
        if (prop != null && !prop.trim().isEmpty()) {
            return prop.trim();
        }
        return defaultValue;
    }

    public static String getLastError() {
        return LAST_ERROR;
    }

    public static Connection getConnection() {
        LAST_ERROR = "";
        return tryConnect(DB_URL, DB_USER, DB_PASSWORD);
    }

    private static Connection tryConnect(String url, String user, String password) {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            LAST_ERROR = "连接失败: " + e.getMessage() + " | SQLState=" + e.getSQLState() + " | ErrorCode=" + e.getErrorCode();
            System.err.println("[DB] " + LAST_ERROR);
            System.err.println("[DB] URL=" + url + ", USER=" + user);
            return null;
        }
    }

    public static void close(Connection conn, Statement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
