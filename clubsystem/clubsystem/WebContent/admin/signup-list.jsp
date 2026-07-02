<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page import="com.clubsystem.bean.Signup, com.clubsystem.bean.Activity, com.clubsystem.bean.User" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>报名名单管理</title>
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
        .btn-success {background: #4CAF50; color: #fff;}
        table {width: 100%; border-collapse: collapse; background: #fff; box-shadow: 0 1px 3px rgba(0,0,0,0.1);}
        th {background: #f5f5f5; padding: 10px; text-align: left; border-bottom: 2px solid #e0e0e0;}
        td {padding: 10px; border-bottom: 1px solid #e0e0e0;}
        .msg {padding: 10px; margin-bottom: 15px; border-radius: 4px;}
        .msg-success {background: #d4edda; color: #155724;}
        .msg-error {background: #f8d7da; color: #721c24;}
        select {padding: 8px; border: 1px solid #ddd; border-radius: 4px; font-size: 14px;}
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
    <h2>报名名单管理</h2>
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
        <form style="display:inline;">
            <label>选择活动：</label>
            <select name="activityId" onchange="location.href='<%= request.getContextPath() %>/admin/signups?activityId='+this.value">
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
        if (signups != null && !signups.isEmpty()) {
    %>
    <div style="margin-bottom:10px;">
        <a href="<%= request.getContextPath() %>/admin/signups/export?activityId=<%= selected %>" class="btn btn-success">导出报名名单(CSV)</a>
    </div>
    <table>
        <thead>
            <tr>
                <th>报名ID</th>
                <th>学号</th>
                <th>姓名</th>
                <th>手机号</th>
                <th>报名时间</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
        <%
            for (Signup s : signups) {
                User u = s.getUser();
        %>
            <tr>
                <td><%= s.getId() %></td>
                <td><%= u != null && u.getStudentNo() != null ? u.getStudentNo() : "-" %></td>
                <td><%= u != null ? u.getRealName() : "-" %></td>
                <td><%= u != null && u.getPhone() != null ? u.getPhone() : "-" %></td>
                <td><%= s.getSignupTime() %></td>
                <td><%= s.getStatus() %></td>
                <td>
                    <form action="<%= request.getContextPath() %>/admin/signup/cancel" method="post" style="display:inline;" onsubmit="return confirm('确认取消该报名？')">
                        <input type="hidden" name="id" value="<%= s.getId() %>">
                        <input type="hidden" name="activityId" value="<%= selected %>">
                        <button type="submit" class="btn btn-danger" style="padding:4px 10px;font-size:12px; border:none; cursor:pointer;">取消报名</button>
                    </form>
                </td>
            </tr>
        <%  } %>
        </tbody>
    </table>
    <% } else if (selected != null) { %>
        <p style="color:#999;">该活动暂无报名记录。</p>
    <% } else { %>
        <p style="color:#999;">请先选择一个活动查看报名名单。</p>
    <% } %>
</div>
</body>
</html>
