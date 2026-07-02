<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page import="com.clubsystem.bean.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>学生首页 - 校园社团管理系统</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body {font-family: "微软雅黑", sans-serif; background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); min-height: 100vh;}
        .header {background: rgba(255,255,255,0.95); padding: 0 30px; height: 64px; display: flex; align-items: center; justify-content: space-between; box-shadow: 0 2px 8px rgba(0,0,0,0.1);}
        .header .logo {font-size: 20px; font-weight: bold; color: #333;}
        .header .user-info {display: flex; align-items: center; gap: 20px; color: #666; font-size: 14px;}
        .header .user-info a {color: #fff; text-decoration: none; padding: 8px 20px; background: #ff4d4f; border-radius: 4px; font-size: 14px; transition: all 0.3s;}
        .header .user-info a:hover {background: #ff7875;}
        .content {padding: 40px 30px; max-width: 1200px; margin: 0 auto;}
        .welcome {background: rgba(255,255,255,0.95); padding: 30px; border-radius: 12px; margin-bottom: 30px; box-shadow: 0 4px 12px rgba(0,0,0,0.1);}
        .welcome h2 {color: #333; margin-bottom: 8px; font-size: 24px;}
        .welcome p {color: #666;}
        .cards {display: grid; grid-template-columns: repeat(auto-fill, minmax(260px, 1fr)); gap: 24px;}
        .card {background: rgba(255,255,255,0.95); border-radius: 12px; padding: 30px; box-shadow: 0 4px 12px rgba(0,0,0,0.1); transition: all 0.3s; cursor: pointer; text-decoration: none; color: inherit; display: block;}
        .card:hover {transform: translateY(-6px); box-shadow: 0 8px 24px rgba(0,0,0,0.15);}
        .card .icon {font-size: 48px; margin-bottom: 16px;}
        .card h3 {color: #333; margin-bottom: 10px; font-size: 18px;}
        .card p {color: #999; font-size: 14px; line-height: 1.6;}
        .card-activities {border-top: 4px solid #1890ff;}
        .card-signups {border-top: 4px solid #52c41a;}
        .card-checkins {border-top: 4px solid #faad14;}
        .card-notices {border-top: 4px solid #f5222d;}
    </style>
</head>
<body>
<div class="header">
    <div class="logo">🎓 校园社团管理系统</div>
    <div class="user-info">
        <%
            User u = (User) session.getAttribute("currentUser");
            String name = (u == null ? "" : u.getRealName());
            if (name.isEmpty()) name = (u == null ? "" : u.getUsername());
        %>
        <span>欢迎，<%= name %>！</span>
        <a href="<%= request.getContextPath() %>/logout">退出登录</a>
    </div>
</div>
<div class="content">
    <div class="welcome">
        <h2>👋 欢迎回来，<%= name %>！</h2>
        <p>今天是 <%= new java.text.SimpleDateFormat("yyyy年MM月dd日").format(new java.util.Date()) %>，看看有什么感兴趣的活动吧！</p>
    </div>
    <div class="cards">
        <a href="<%= request.getContextPath() %>/student/activities" class="card card-activities">
            <div class="icon">📋</div>
            <h3>浏览活动</h3>
            <p>查看所有可用的社团活动，在线报名参与</p>
        </a>
        <a href="<%= request.getContextPath() %>/student/my-signups" class="card card-signups">
            <div class="icon">📝</div>
            <h3>我的报名</h3>
            <p>查看您已报名的活动，管理报名记录</p>
        </a>
        <a href="<%= request.getContextPath() %>/student/my-checkins" class="card card-checkins">
            <div class="icon">✅</div>
            <h3>我的签到</h3>
            <p>查看您的签到记录</p>
        </a>
        <a href="<%= request.getContextPath() %>/student/notices" class="card card-notices">
            <div class="icon">📢</div>
            <h3>社团公告</h3>
            <p>查看最新的社团公告和通知</p>
        </a>
    </div>
</div>
</body>
</html>
