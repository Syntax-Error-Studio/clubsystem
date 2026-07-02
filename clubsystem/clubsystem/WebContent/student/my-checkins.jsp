<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page import="com.clubsystem.bean.Checkin" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>我的签到</title>
    <style>
        body {font-family: "微软雅黑", sans-serif; background-color: #f8f8f8; margin: 0;}
        .header {background: #333; color: #fff; padding: 12px 30px; display: flex; justify-content: space-between; align-items: center;}
        .header a {color: #fff; text-decoration: none; margin-left: 15px; font-size: 14px;}
        .header a:hover {text-decoration: underline;}
        .header .brand {font-weight: bold; font-size: 16px;}
        .container {width: 85%; margin: 30px auto;}
        h2 {margin-bottom: 20px;}
        table {width: 100%; border-collapse: collapse; background: #fff; box-shadow: 0 1px 3px rgba(0,0,0,0.1);}
        th {background: #f5f5f5; padding: 10px; text-align: left; border-bottom: 2px solid #e0e0e0;}
        td {padding: 10px; border-bottom: 1px solid #e0e0e0;}
        a {color: #2196F3; text-decoration: none;}
        a:hover {text-decoration: underline;}
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
    <h2>我的签到记录</h2>
    <%
        List<Checkin> checkins = (List<Checkin>) request.getAttribute("checkins");
        if (checkins != null && !checkins.isEmpty()) {
    %>
    <table>
        <thead>
            <tr>
                <th>签到ID</th>
                <th>报名ID</th>
                <th>活动ID</th>
                <th>签到时间</th>
                <th>状态</th>
            </tr>
        </thead>
        <tbody>
        <%
            for (Checkin c : checkins) {
        %>
            <tr>
                <td><%= c.getId() %></td>
                <td><%= c.getSignupId() %></td>
                <td><%= c.getActivityId() %></td>
                <td><%= c.getCheckinTime() %></td>
                <td><%= c.getCheckinStatus() %></td>
            </tr>
        <%  } %>
        </tbody>
    </table>
    <% } else { %>
        <div class="empty">暂无签到记录</div>
    <% } %>
</div>
</body>
</html>
