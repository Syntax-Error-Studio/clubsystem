<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page import="com.clubsystem.bean.Activity" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>管理活动</title>
    <style>
        body {font-family: "微软雅黑", sans-serif; background-color: #f8f8f8; margin: 0;}
        .header {background: #333; color: #fff; padding: 12px 30px; display: flex; justify-content: space-between; align-items: center;}
        .header a {color: #fff; text-decoration: none; margin-left: 15px; font-size: 14px;}
        .header a:hover {text-decoration: underline;}
        .header .brand {font-weight: bold; font-size: 16px;}
        .container {width: 90%; margin: 30px auto;}
        h2 {margin-bottom: 20px;}
        .toolbar {margin-bottom: 15px;}
        .btn {display: inline-block; padding: 8px 16px; border: none; border-radius: 4px; text-decoration: none; cursor: pointer; font-size: 14px;}
        .btn-primary {background: #2196F3; color: #fff;}
        .btn-danger {background: #f44336; color: #fff;}
        .btn-warning {background: #ff9800; color: #fff;}
        table {width: 100%; border-collapse: collapse; background: #fff; box-shadow: 0 1px 3px rgba(0,0,0,0.1);}
        th {background: #f5f5f5; padding: 10px; text-align: left; border-bottom: 2px solid #e0e0e0;}
        td {padding: 10px; border-bottom: 1px solid #e0e0e0;}
        tr:hover {background: #fafafa;}
        .cover-img {width: 60px; height: 40px; object-fit: cover; border-radius: 3px;}
        .msg {padding: 10px; margin-bottom: 15px; border-radius: 4px;}
        .msg-success {background: #d4edda; color: #155724;}
        .msg-error {background: #f8d7da; color: #721c24;}
    </style>
</head>
<body>
    <div class="header">
        <span class="brand">校园社团管理系统</span>
        <span>
            <a href="<%= request.getContextPath() %>/admin/index.jsp">首页</a>
            <a href="<%= request.getContextPath() %>/admin/activities">活动</a>
            <a href="<%= request.getContextPath() %>/admin/signups">报名</a>
            <a href="<%= request.getContextPath() %>/admin/checkin">签到</a>
            <a href="<%= request.getContextPath() %>/admin/notices">公告</a>
            <a href="<%= request.getContextPath() %>/logout">退出</a>
        </span>
    </div>
<div class="container">
    <h2>活动管理</h2>
    <%
        String msg = (String) request.getAttribute("message");
        if (msg != null) { %>
            <div class="msg msg-success"><%= msg %></div>
    <% } %>
    <%
        String error = (String) request.getAttribute("error");
        if (error != null) { %>
            <div class="msg msg-error"><%= error %></div>
    <% } %>
    <div class="toolbar">
        <a href="<%= request.getContextPath() %>/admin/activity/form" class="btn btn-primary">发布新活动</a>
    </div>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>封面</th>
                <th>标题</th>
                <th>分类</th>
                <th>地点</th>
                <th>人数</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
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
                <td><%= a.getCategoryId() == 1 ? "文体活动" : (a.getCategoryId() == 2 ? "学术讲座" : "其他") %></td>
                <td><%= a.getLocation() %></td>
                <td><%= a.getCurrentCount() %>/<%= a.getMaxCount() %></td>
                <td><%= a.getStatus() %></td>
                <td>
                    <a href="<%= request.getContextPath() %>/admin/activity/form?id=<%= a.getId() %>" class="btn btn-warning" style="padding:4px 10px;font-size:12px;">编辑</a>
                    <form action="<%= request.getContextPath() %>/admin/activity/delete" method="post" style="display:inline;" onsubmit="return confirm('确认删除该活动？')">
                        <input type="hidden" name="id" value="<%= a.getId() %>">
                        <button type="submit" class="btn btn-danger" style="padding:4px 10px;font-size:12px; border:none; cursor:pointer;">删除</button>
                    </form>
                </td>
            </tr>
        <%      }
            }
        %>
        </tbody>
    </table>
</div>
</body>
</html>
