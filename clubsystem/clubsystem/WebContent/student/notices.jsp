<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page import="com.clubsystem.bean.Notice" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>公告列表</title>
    <style>
        body {font-family: "微软雅黑", sans-serif; background-color: #f8f8f8; margin: 0;}
        .header {background: #333; color: #fff; padding: 12px 30px; display: flex; justify-content: space-between; align-items: center;}
        .header a {color: #fff; text-decoration: none; margin-left: 15px;}
        .header a:hover {text-decoration: underline;}
        .container {width: 85%; margin: 30px auto;}
        h2 {margin-bottom: 20px;}
        .notice-card {background: #fff; padding: 20px 25px; margin-bottom: 15px; border-radius: 4px; box-shadow: 0 1px 3px rgba(0,0,0,0.1);}
        .notice-card h3 {margin: 0 0 10px 0; color: #333;}
        .notice-meta {color: #999; font-size: 13px; margin-bottom: 10px;}
        .notice-content {line-height: 1.8; color: #555; white-space: pre-wrap;}
        .empty {text-align: center; color: #999; padding: 40px;}
    </style>
</head>
<body>
<div class="header">
    <span class="brand">校园社团管理系统</span>
    <span>
        <a href="<%= request.getContextPath() %>/student/index.jsp">首页</a>
        <a href="<%= request.getContextPath() %>/student/activities">活动</a>
        <a href="<%= request.getContextPath() %>/student/my-signups">报名</a>
        <a href="<%= request.getContextPath() %>/student/my-checkins">签到</a>
        <a href="<%= request.getContextPath() %>/student/notices">公告</a>
        <a href="<%= request.getContextPath() %>/logout">退出</a>
    </span>
</div>
<div class="container">
    <h2>社团公告</h2>
    <%
        List<Notice> notices = (List<Notice>) request.getAttribute("notices");
        if (notices != null && !notices.isEmpty()) {
            for (Notice n : notices) {
    %>
        <div class="notice-card">
            <h3><%= n.getTitle() %></h3>
            <div class="notice-meta">发布时间：<%= n.getCreateTime() %></div>
            <div class="notice-content"><%= n.getContent() %></div>
        </div>
    <%  }
        } else { %>
        <div class="empty">暂无公告</div>
    <% } %>
</div>
</body>
</html>
