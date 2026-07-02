<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page import="com.clubsystem.bean.Activity" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>发布/编辑活动</title>
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
        input[type="text"], input[type="number"], textarea, select {width: 100%; padding: 8px; border: 1px solid #ddd; border-radius: 4px; box-sizing: border-box; font-size: 14px;}
        textarea {resize: vertical; height: 100px;}
        .btn {padding: 10px 24px; border: none; border-radius: 4px; cursor: pointer; font-size: 14px; margin-right: 10px;}
        .btn-primary {background: #2196F3; color: #fff;}
        .btn-default {background: #ccc; color: #333;}
        .msg {padding: 10px; margin-bottom: 15px; border-radius: 4px;}
        .msg-error {background: #f8d7da; color: #721c24;}
        .cover-preview {margin-top: 8px;}
        .cover-preview img {max-width: 200px; max-height: 120px; border-radius: 4px;}
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
        Activity activity = (Activity) request.getAttribute("activity");
        boolean isEdit = (activity != null);
    %>
    <h2><%= isEdit ? "编辑活动" : "发布新活动" %></h2>
    <%
        String error = (String) request.getAttribute("error");
        if (error != null) { %>
            <div class="msg msg-error"><%= error %></div>
    <% } %>
    <form action="<%= request.getContextPath() %>/admin/activity/save" method="post" enctype="multipart/form-data">
        <% if (isEdit) { %>
            <input type="hidden" name="id" value="<%= activity.getId() %>">
        <% } %>
        <div class="form-group">
            <label>活动标题</label>
            <input type="text" name="title" required value="<%= isEdit ? activity.getTitle() : "" %>">
        </div>
        <div class="form-group">
            <label>活动分类</label>
            <select name="categoryId">
                <option value="1" <%= isEdit && activity.getCategoryId() == 1 ? "selected" : "" %>>文体活动</option>
                <option value="2" <%= isEdit && activity.getCategoryId() == 2 ? "selected" : "" %>>学术讲座</option>
                <option value="3" <%= isEdit && activity.getCategoryId() == 3 ? "selected" : "" %>>其他</option>
            </select>
        </div>
        <div class="form-group">
            <label>活动简介</label>
            <textarea name="content" required><%= isEdit ? activity.getContent() : "" %></textarea>
        </div>
        <div class="form-group">
            <label>活动地点</label>
            <input type="text" name="location" required value="<%= isEdit ? activity.getLocation() : "" %>">
        </div>
        <div class="form-group">
            <label>最大报名人数</label>
            <input type="number" name="maxCount" min="1" required value="<%= isEdit ? activity.getMaxCount() : 100 %>">
        </div>
        <div class="form-group">
            <label>活动状态</label>
            <select name="status">
                <option value="报名中" <%= isEdit && "报名中".equals(activity.getStatus()) ? "selected" : "" %>>报名中</option>
                <option value="未开始" <%= isEdit && "未开始".equals(activity.getStatus()) ? "selected" : "" %>>未开始</option>
                <option value="已结束" <%= isEdit && "已结束".equals(activity.getStatus()) ? "selected" : "" %>>已结束</option>
            </select>
        </div>
        <div class="form-group">
            <label>封面图片（可选，支持 jpg/png/gif，≤5MB）</label>
            <input type="file" name="coverImageFile" accept="image/*">
            <% if (isEdit && activity.getCoverImage() != null && !activity.getCoverImage().isEmpty()) { %>
                <div class="cover-preview">
                    <img src="<%= request.getContextPath() + activity.getCoverImage() %>" alt="当前封面">
                    <span style="color:#999; font-size:12px;">当前封面</span>
                </div>
            <% } %>
        </div>
        <button type="submit" class="btn btn-primary"><%= isEdit ? "保存修改" : "发布活动" %></button>
        <a href="<%= request.getContextPath() %>/admin/activities" class="btn btn-default">取消</a>
    </form>
</div>
</body>
</html>
