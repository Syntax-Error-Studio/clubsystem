<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page import="com.clubsystem.bean.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>校园社团活动报名与签到管理系统</title>
    <style>
        body {font-family: "微软雅黑", sans-serif; background-color: #f8f8f8; text-align: center; padding-top: 100px;}
        a {color: #2196F3; text-decoration: none; margin: 0 10px;}
        a:hover {text-decoration: underline;}
    </style>
</head>
<body>
<h1>欢迎使用校园社团活动报名与签到管理系统</h1>
<%
    User u = (User) session.getAttribute("currentUser");
    if (u != null) {
%>
    <p>你好，<%= u.getRealName() %>！你已登录。</p>
    <p>请选择你的操作：</p>
    <% if ("student".equals(u.getRole())) { %>
        <a href="student/index.jsp">进入学生首页</a>
    <% } %>
    <% if ("admin".equals(u.getRole()) || "superadmin".equals(u.getRole())) { %>
        <a href="admin/index.jsp">进入管理员首页</a>
    <% } %>
    <a href="logout">退出登录</a>
<%
    } else {
%>
    <p>请先登录或注册：</p>
        <a href="<%= request.getContextPath() %>/login">登录</a> | <a href="<%= request.getContextPath() %>/register">注册</a>
<% } %>
</body>
</html>