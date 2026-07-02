<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.clubsystem.bean.Activity" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>活动列表</title>
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
        .msg {color: green;}
        .err {color: red;}
        a {color: #2196F3; text-decoration: none;}
        .cover-img {width: 60px; height: 40px; object-fit: cover; border-radius: 3px;}
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
    <h2>活动列表</h2>
    <% String msg = request.getParameter("message"); if (msg != null) { %><p class="msg"><%= msg %></p><% } %>
    <% String err = request.getParameter("error"); if (err != null) { %><p class="err"><%= err %></p><% } %>

    <table>
        <tr>
            <th>ID</th>
            <th>封面</th>
            <th>标题</th>
            <th>地点</th>
            <th>状态</th>
            <th>人数</th>
            <th>操作</th>
        </tr>
        <%
            List<Activity> activities = (List<Activity>) request.getAttribute("activities");
            if (activities != null) {
                for (Activity a : activities) {
        %>
        <tr>
            <td><%= a.getId() %></td>
            <td>
                <% if (a.getCoverImage() != null && !a.getCoverImage().isEmpty()) { %>
                    <img src="<%= request.getContextPath() + a.getCoverImage() %>" class="cover-img" alt="封面">
                <% } else { %>
                    <span style="color:#999">无</span>
                <% } %>
            </td>
            <td><%= a.getTitle() %></td>
            <td><%= a.getLocation() %></td>
            <td><%= a.getStatus() %></td>
            <td><%= a.getCurrentCount() %>/<%= a.getMaxCount() %></td>
            <td>
                <form action="<%= request.getContextPath() %>/student/signup" method="post" style="margin:0;">
                    <input type="hidden" name="activityId" value="<%= a.getId() %>" />
                    <button type="submit">报名</button>
                </form>
            </td>
        </tr>
        <%      }
            }
        %>
    </table>
</div>
</body>
</html>