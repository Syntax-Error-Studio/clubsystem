<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page import="com.clubsystem.bean.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>管理员首页</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body {font-family: "微软雅黑", sans-serif; background-color: #f0f2f5;}
        .header {background: #001529; color: #fff; padding: 0 30px; height: 64px; display: flex; align-items: center; justify-content: space-between;}
        .header .logo {font-size: 20px; font-weight: bold; display: flex; align-items: center;}
        .header .logo span {margin-left: 10px;}
        .header .user-info {display: flex; align-items: center; gap: 20px;}
        .header .user-info a {color: #fff; text-decoration: none; padding: 6px 16px; background: #ff4d4f; border-radius: 4px; font-size: 14px;}
        .header .user-info a:hover {background: #ff7875;}
        .main {display: flex;}
        .sidebar {width: 200px; background: #001529; min-height: calc(100vh - 64px); padding-top: 20px;}
        .sidebar a {display: block; color: rgba(255,255,255,0.65); text-decoration: none; padding: 12px 24px; font-size: 14px; transition: all 0.3s;}
        .sidebar a:hover, .sidebar a.active {color: #fff; background: #1890ff;}
        .content {flex: 1; padding: 24px;}
        .welcome {background: #fff; padding: 24px; border-radius: 4px; margin-bottom: 24px; box-shadow: 0 1px 3px rgba(0,0,0,0.1);}
        .welcome h2 {color: #333; margin-bottom: 8px;}
        .welcome p {color: #666;}
        .cards {display: grid; grid-template-columns: repeat(auto-fill, minmax(240px, 1fr)); gap: 20px;}
        .card {background: #fff; border-radius: 8px; padding: 24px; box-shadow: 0 2px 8px rgba(0,0,0,0.1); transition: all 0.3s; cursor: pointer; text-decoration: none; color: inherit; display: block;}
        .card:hover {transform: translateY(-4px); box-shadow: 0 4px 16px rgba(0,0,0,0.15);}
        .card .icon {font-size: 32px; margin-bottom: 12px;}
        .card h3 {color: #333; margin-bottom: 8px; font-size: 16px;}
        .card p {color: #999; font-size: 13px;}
        .card-activity {border-left: 4px solid #1890ff;}
        .card-signup {border-left: 4px solid #52c41a;}
        .card-checkin {border-left: 4px solid #faad14;}
        .card-notice {border-left: 4px solid #f5222d;}
    </style>
</head>
<body>
<div class="header">
    <div class="logo">
        <span>🎓 校园社团管理系统</span>
    </div>
    <div class="user-info">
        <%
            User u = (User) session.getAttribute("currentUser");
            String name = (u == null ? "" : u.getRealName());
            if (name.isEmpty()) name = (u == null ? "" : u.getUsername());
        %>
        <span>欢迎，<%= name %>（管理员）</span>
        <a href="<%= request.getContextPath() %>/logout">退出登录</a>
    </div>
</div>
<div class="main">
    <div class="sidebar">
        <a href="<%= request.getContextPath() %>/admin/index.jsp" class="active">🏠 首页</a>
        <a href="<%= request.getContextPath() %>/admin/activities">📋 活动管理</a>
        <a href="<%= request.getContextPath() %>/admin/activity/form">➕ 发布活动</a>
        <a href="<%= request.getContextPath() %>/admin/signups">📝 报名管理</a>
        <a href="<%= request.getContextPath() %>/admin/checkin">✅ 签到管理</a>
        <a href="<%= request.getContextPath() %>/admin/notices">📢 公告管理</a>
    </div>
    <div class="content">
        <div class="welcome">
            <h2>管理员工作台</h2>
            <p>当前时间：<%= new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm").format(new java.util.Date()) %></p>
        </div>
        <div class="cards">
            <a href="<%= request.getContextPath() %>/admin/activity/form" class="card card-activity">
                <div class="icon">📋</div>
                <h3>发布活动</h3>
                <p>发布新的社团活动，设置活动详情</p>
            </a>
            <a href="<%= request.getContextPath() %>/admin/activities" class="card card-activity">
                <div class="icon">📝</div>
                <h3>管理活动</h3>
                <p>查看、编辑或删除已发布的活动</p>
            </a>
            <a href="<%= request.getContextPath() %>/admin/signups" class="card card-signup">
                <div class="icon">👥</div>
                <h3>报名管理</h3>
                <p>查看各活动的报名名单，导出名单</p>
            </a>
            <a href="<%= request.getContextPath() %>/admin/checkin" class="card card-checkin">
                <div class="icon">✅</div>
                <h3>签到管理</h3>
                <p>为活动参与者执行签到操作</p>
            </a>
            <a href="<%= request.getContextPath() %>/admin/notices" class="card card-notice">
                <div class="icon">📢</div>
                <h3>公告管理</h3>
                <p>发布、编辑或删除社团公告</p>
            </a>
        </div>
    </div>
</div>
</body>
</html>
