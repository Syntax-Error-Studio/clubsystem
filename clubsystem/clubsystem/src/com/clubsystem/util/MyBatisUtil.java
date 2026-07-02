package com.clubsystem.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * MyBatis工厂类
 * 用于获取SqlSession，而不会反复确认这些多余的信息
 */
public class MyBatisUtil {
    private static SqlSessionFactory factory;

    static {
        try {
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            factory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取会话对象
     *
     * @return SqlSession会话对象
     */
    public static SqlSession getSession() {
        return factory.openSession();
    }

    /**
     * 获取会话对象
     *
     * @param autoCommit 自动提交标记
     * @return SqlSession会话对象
     */
    public static SqlSession getSession(boolean autoCommit) {
        return factory.openSession(autoCommit);
    }
}

