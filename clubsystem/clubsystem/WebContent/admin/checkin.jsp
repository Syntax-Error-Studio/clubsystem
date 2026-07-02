<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page import="com.clubsystem.bean.Checkin, com.clubsystem.bean.Signup, com.clubsystem.bean.Activity, com.clubsystem.bean.User" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>签到管理</title>
    <style>
        body {font-family: "微软雅黑", sans-serif; background-color: #f8f8f8; margin: 0;}
        .header {background: #333; color: #fff; padding: 12px 30px; display: flex; justify-content: space-between; align-items: center;}
        .header a {color: #fff; text-decoration: none; margin-left: 15px; font-size: 14px;}
        .header a:hover {text-decoration: underline;}
        .header .brand {font-weight: bold; font-size: 16px;}
        .container {width: 90%; margin: 30px auto;}
        h2 {margin-bottom: 20px;}
        .section {background: #fff; padding: 20px; margin-bottom: 20px; border-radius: 4px; box-shadow: 0 1px 3px rgba(0,0,0,0.1);}
        .btn {display: inline-block; padding: 8px 16px; border: none; border-radius: 4px; text-decoration: none; cursor: pointer; font-size: 14px;}
        .btn-primary {background: #2196F3; color: #fff;}
        .btn-success {background: #4CAF50; color: #fff;}
        table {width: 100%; border-collapse: collapse;}
        th {background: #f5f5f5; padding: 10px; text-align: left; border-bottom: 2px solid #e0e0e0;}
        td {padding: 10px; border-bottom: 1px solid #e0e0e0;}
        .msg {padding: 10px; margin-bottom: 15px; border-radius: 4px;}
        .msg-success {background: #d4edda; color: #155724;}
        .msg-error {background: #f8d7da; color: #721c24;}
        select, input[type="submit"] {padding: 8px; border: 1px solid #ddd; border-radius: 4px; font-size: 14px;}
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
    <h2>签到管理</h2>
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

    <!-- 选择活动 -->
    <div class="section">
        <form>
            <label>选择活动：</label>
            <select name="activityId" onchange="this.form.submit()">
                <option value="">-- 请选择 --</option>
                <%
                    List<Activity> activities = (List<Activity>) request.getAttribute("activities");
                    Integer selected = (Integer) request.getAttribute("selectedActivityId");
                    if (activities != null) {
                        for (Activity a : activities) {
                %>
                    <option value="<%= a.getId() %>" <%= (selected != null && selected == a.getId()) ? "selected" : "" %>><%= a.getId() %> - <%= a.getTitle() %></option>
                <%      }
                    }
                %>
            </select>
        </form>
    </div>

    <%
        List<Signup> signups = (List<Signup>) request.getAttribute("signups");
        if (selected != null && signups != null) {
    %>
    <!-- 签到操作 -->
    <div class="section">
        <h3>执行签到</h3>
        <form method="post" action="<%= request.getContextPath() %>/admin/checkin">
            <input type="hidden" name="activityId" value="<%= selected %>">
            <label>选择报名记录：</label>
            <select name="signupId" style="min-width:250px;">
                <%
                    for (Signup s : signups) {
                        User u = s.getUser();
                %>
                    <option value="<%= s.getId() %>"><%= u != null ? u.getRealName() + " (" + u.getStudentNo() + ")" : "用户" + s.getUserId() %></option>
                <%  } %>
            </select>
            <input type="submit" value="签 到" class="btn btn-success" onclick="return confirm('确认签到？')">
        </form>
    </div>
    <% } %>

    <!-- 签到记录 -->
    <div class="section">
        <h3>签到记录</h3>
        <%
            List<Checkin> checkins = (List<Checkin>) request.getAttribute("checkins");
            if (checkins != null && !checkins.isEmpty()) {
        %>
        <table>
            <thead>
                <tr>
                    <th>签到ID</th>
                    <th>签到人ID</th>
                    <th>报名ID</th>
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
                    <td><%= c.getUserId() %></td>
                    <td><%= c.getSignupId() %></td>
                    <td><%= c.getCheckinTime() %></td>
                    <td><%= c.getCheckinStatus() %></td>
                </tr>
            <%  } %>
            </tbody>
        </table>
        <% } else if (selected != null) { %>
            <p style="color:#999;">暂无签到记录。</p>
        <% } else { %>
            <p style="color:#999;">请先选择活动查看签到记录。</p>
        <% } %>
    </div>
</div>
</body>
</html>
