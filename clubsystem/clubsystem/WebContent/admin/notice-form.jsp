<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page import="com.clubsystem.bean.Notice" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>发布/编辑公告</title>
    <style>
        body {font-family: "微软雅黑", sans-serif; background-color: #f8f8f8; margin: 0;}
        .header {background: #333; color: #fff; padding: 12px 30px; display: flex; justify-content: space-between; align-items: center;}
        .header a {color: #fff; text-decoration: none; margin-left: 15px; font-size: 14px;}
        .header a:hover {text-decoration: underline;}
        .header .brand {font-weight: bold; font-size: 16px;}
        .container {width: 60%; margin: 30px auto; background: #fff; padding: 30px; box-shadow: 0 1px 3px rgba(0,0,0,0.1); border-radius: 4px;}
        h2 {margin-bottom: 20px;}
        .form-group {margin-bottom: 15px;}
        label {display: block; margin-bottom: 5px; font-weight: bold; color: #555;}
        input[type="text"], textarea, select {width: 100%; padding: 8px; border: 1px solid #ddd; border-radius: 4px; box-sizing: border-box; font-size: 14px;}
        textarea {resize: vertical; height: 150px;}
        .btn {padding: 10px 24px; border: none; border-radius: 4px; cursor: pointer; font-size: 14px; margin-right: 10px;}
        .btn-primary {background: #2196F3; color: #fff;}
        .btn-default {background: #ccc; color: #333;}
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
    <%
        Notice notice = (Notice) request.getAttribute("notice");
        boolean isEdit = (notice != null);
    %>
    <h2><%= isEdit ? "编辑公告" : "发布新公告" %></h2>
    <form action="<%= request.getContextPath() %>/admin/notice/save" method="post">
        <% if (isEdit) { %>
            <input type="hidden" name="id" value="<%= notice.getId() %>">
        <% } %>
        <div class="form-group">
            <label>公告标题</label>
            <input type="text" name="title" required value="<%= isEdit ? notice.getTitle() : "" %>">
        </div>
        <div class="form-group">
            <label>公告内容</label>
            <textarea name="content" required><%= isEdit ? notice.getContent() : "" %></textarea>
        </div>
        <div class="form-group">
            <label>状态</label>
            <select name="status">
                <option value="有效" <%= isEdit && "有效".equals(notice.getStatus()) ? "selected" : "" %>>有效</option>
                <option value="已失效" <%= isEdit && "已失效".equals(notice.getStatus()) ? "selected" : "" %>>已失效</option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary"><%= isEdit ? "保存修改" : "发布公告" %></button>
        <a href="<%= request.getContextPath() %>/admin/notices" class="btn btn-default">取消</a>
    </form>
</div>
</body>
</html>
