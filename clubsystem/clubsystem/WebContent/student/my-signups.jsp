<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.clubsystem.bean.Signup" %>
<%@ page import="com.clubsystem.bean.Activity" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>我的报名</title>
    <style>
        body {font-family: "微软雅黑", sans-serif; background-color: #f8f8f8; margin: 0;}
        .header {background: #333; color: #fff; padding: 12px 30px; display: flex; justify-content: space-between; align-items: center;}
        .header a {color: #fff; text-decoration: none; margin-left: 15px; font-size: 14px;}
        .header a:hover {text-decoration: underline;}
        .header .brand {font-weight: bold; font-size: 16px;}
        .container {width: 85%; margin: 30px auto;}
        table {width: 100%; border-collapse: collapse; background: #fff;}
        th, td {border: 1px solid #ddd; padding: 10px; text-align: left;}
        th {background: #f0f0f0;}
        a {color: #2196F3; text-decoration: none;}
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
    <h2>我的报名记录</h2>
    <table>
        <tr>
            <th>报名ID</th>
            <th>活动标题</th>
            <th>活动地点</th>
            <th>报名时间</th>
            <th>状态</th>
        </tr>
        <%
            List<Signup> signups = (List<Signup>) request.getAttribute("signups");
            if (signups != null) {
                for (Signup s : signups) {
                    Activity act = s.getActivity();
        %>
        <tr>
            <td><%= s.getId() %></td>
            <td><%= act != null ? act.getTitle() : "活动#" + s.getActivityId() %></td>
            <td><%= act != null && act.getLocation() != null ? act.getLocation() : "-" %></td>
            <td><%= s.getSignupTime() %></td>
            <td><%= s.getStatus() %></td>
        </tr>
        <%      }
            }
        %>
    </table>
</div>
</body>
</html>
