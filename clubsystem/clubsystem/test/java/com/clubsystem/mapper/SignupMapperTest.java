package com.clubsystem.mapper;

import com.clubsystem.bean.Activity;
import com.clubsystem.bean.Signup;
import com.clubsystem.bean.User;
import com.clubsystem.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 报名Mapper单元测试：覆盖CRUD并验证关联映射。
 */
public class SignupMapperTest {

    private SqlSession session;
    private SignupMapper signupMapper;
    private UserMapper userMapper;
    private ActivityMapper activityMapper;

    @Before
    public void setUp() {
        session = MyBatisUtil.getSession(true);
        signupMapper = session.getMapper(SignupMapper.class);
        userMapper = session.getMapper(UserMapper.class);
        activityMapper = session.getMapper(ActivityMapper.class);
    }

    @After
    public void tearDown() {
        if (session != null) {
            session.close();
        }
    }

    @Test
    public void testAddAndGetSignupWithAssociation() {
        User user = createTempUser();
        Activity activity = createTempActivity();
        try {
            Signup signup = new Signup();
            signup.setUserId(user.getId());
            signup.setActivityId(activity.getId());
            signup.setSignupTime(new Date());
            signup.setStatus("已报名");

            int insert = signupMapper.addSignup(signup);
            assertEquals("新增报名应成功", 1, insert);
            assertTrue("报名ID应回填", signup.getId() > 0);

            Signup db = signupMapper.getSignupById(signup.getId());
            assertNotNull("按ID应能查到报名", db);
            assertNotNull("关联User应被映射", db.getUser());
            assertNotNull("关联Activity应被映射", db.getActivity());
            assertEquals("关联用户名应一致", user.getUsername(), db.getUser().getUsername());
            assertEquals("关联活动标题应一致", activity.getTitle(), db.getActivity().getTitle());
        } finally {
            cleanupByUserAndActivity(user.getId(), activity.getId(), activity.getId(), user.getId());
        }
    }

    @Test
    public void testUpdateAndDeleteSignup() {
        User user = createTempUser();
        Activity activity = createTempActivity();
        Signup signup = new Signup();
        signup.setUserId(user.getId());
        signup.setActivityId(activity.getId());
        signup.setSignupTime(new Date());
        signup.setStatus("已报名");
        signupMapper.addSignup(signup);

        try {
            signup.setStatus("已签到");
            int update = signupMapper.updateSignup(signup);
            assertEquals("更新报名应成功", 1, update);

            Signup updated = signupMapper.getSignupById(signup.getId());
            assertNotNull(updated);
            assertEquals("状态应更新", "已签到", updated.getStatus());

            int delete = signupMapper.deleteSignupById(signup.getId());
            assertEquals("删除报名应成功", 1, delete);
            assertNull("删除后应查不到", signupMapper.getSignupById(signup.getId()));
        } finally {
            cleanupByUserAndActivity(user.getId(), activity.getId(), activity.getId(), user.getId());
        }
    }

    @Test
    public void testQueryByUserAndActivity() {
        User user = createTempUser();
        Activity activity = createTempActivity();
        try {
            Signup signup = new Signup();
            signup.setUserId(user.getId());
            signup.setActivityId(activity.getId());
            signup.setSignupTime(new Date());
            signup.setStatus("已报名");
            signupMapper.addSignup(signup);

            Signup byPair = signupMapper.getSignupByUserAndActivity(user.getId(), activity.getId());
            assertNotNull("按用户+活动应查到报名", byPair);

            List<Signup> byUser = signupMapper.getSignupsByUserId(user.getId());
            assertFalse("按用户应查到至少1条", byUser.isEmpty());
            assertNotNull("按用户查询应含关联活动", byUser.get(0).getActivity());

            List<Signup> byActivity = signupMapper.getSignupsByActivityId(activity.getId());
            assertFalse("按活动应查到至少1条", byActivity.isEmpty());
            assertNotNull("按活动查询应含关联用户", byActivity.get(0).getUser());
        } finally {
            cleanupByUserAndActivity(user.getId(), activity.getId(), activity.getId(), user.getId());
        }
    }

    private User createTempUser() {
        User user = new User();
        long ts = System.currentTimeMillis();
        user.setUsername("u" + ts);
        user.setPassword("123456");
        user.setRealName("测试用户");
        user.setStudentNo("S" + ts);
        user.setPhone("138" + String.valueOf(ts).substring(4, 11));
        user.setRole("student");
        user.setCreateTime(new Date());
        userMapper.addUser(user);
        return user;
    }

    private Activity createTempActivity() {
        Activity activity = new Activity();
        long ts = System.currentTimeMillis();
        activity.setCategoryId(1);
        activity.setTitle("测试活动-" + ts);
        activity.setContent("测试内容");
        activity.setLocation("测试地点");
        activity.setStartTime(new Date());
        activity.setEndTime(new Date());
        activity.setDeadline(new Date());
        activity.setMaxCount(10);
        activity.setCurrentCount(0);
        activity.setCreatorId(1);
        activity.setStatus("报名中");
        activity.setCreateTime(new Date());
        activityMapper.addActivity(activity);
        return activity;
    }

    private void cleanupByUserAndActivity(int userId, int activityId, int deleteActivityId, int deleteUserId) {
        Signup byPair = signupMapper.getSignupByUserAndActivity(userId, activityId);
        if (byPair != null) {
            signupMapper.deleteSignupById(byPair.getId());
        }
        activityMapper.deleteActivityById(deleteActivityId);
        userMapper.deleteUserById(deleteUserId);
    }
}

