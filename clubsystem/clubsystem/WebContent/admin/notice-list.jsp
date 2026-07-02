<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page import="com.clubsystem.bean.Notice" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>公告管理</title>
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
        .btn-success {background: #4CAF50; color: #fff;}
        table {width: 100%; border-collapse: collapse; background: #fff; box-shadow: 0 1px 3px rgba(0,0,0,0.1);}
        th {background: #f5f5f5; padding: 10px; text-align: left; border-bottom: 2px solid #e0e0e0;}
        td {padding: 10px; border-bottom: 1px solid #e0e0e0;}
        tr:hover {background: #fafafa;}
        .msg {padding: 10px; margin-bottom: 15px; border-radius: 4px;}
        .msg-success {background: #d4edda; color: #155724;}
        .notice-content {max-width: 300px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;}
        .badge {display: inline-block; padding: 2px 8px; border-radius: 10px; font-size: 12px;}
        .badge-active {background: #d4edda; color: #155724;}
        .badge-inactive {background: #e0e0e0; color: #666;}
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
    <h2>公告管理</h2>
    <%
        String msg = (String) request.getAttribute("message");
        if (msg != null) { %>
            <div class="msg msg-success"><%= msg %></div>
    <% } %>
    <div class="toolbar">
        <a href="<%= request.getContextPath() %>/admin/notice/form" class="btn btn-primary">发布新公告</a>
    </div>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>标题</th>
                <th>内容</th>
                <th>状态</th>
                <th>发布时间</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
        <%
            List<Notice> notices = (List<Notice>) request.getAttribute("notices");
            if (notices != null) {
                for (Notice n : notices) {
        %>
            <tr>
                <td><%= n.getId() %></td>
                <td><%= n.getTitle() %></td>
                <td><div class="notice-content"><%= n.getContent() %></div></td>
                <td>
                    <span class="badge <%= "有效".equals(n.getStatus()) ? "badge-active" : "badge-inactive" %>"><%= n.getStatus() %></span>
                </td>
                <td><%= n.getCreateTime() %></td>
                <td>
                    <a href="<%= request.getContextPath() %>/admin/notice/form?id=<%= n.getId() %>" class="btn btn-warning" style="padding:4px 10px;font-size:12px;">编辑</a>
                    <form action="<%= request.getContextPath() %>/admin/notice/delete" method="post" style="display:inline;" onsubmit="return confirm('确认删除该公告？')">
                        <input type="hidden" name="id" value="<%= n.getId() %>">
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
