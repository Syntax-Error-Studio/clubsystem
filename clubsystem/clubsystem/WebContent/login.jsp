<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户登录</title>
    <style>
        body {font-family: "微软雅黑", sans-serif; background-color: #f4f4f4;}
        .login-container {width: 360px; margin: 100px auto; padding: 40px; background: #fff; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1);} 
        h2 {text-align: center; margin-bottom: 20px;}
        .form-group {margin-bottom: 15px;}
        label {display: block; margin-bottom: 5px;}
        input[type=text], input[type=password] {width: 100%; padding: 8px; box-sizing: border-box;}
        .btn {width: 100%; padding: 10px; background-color: #4CAF50; color: #fff; border: none; border-radius: 4px; cursor: pointer;}
        .btn:hover {background-color: #45a049;}
        .message {color: green; text-align: center;}
        .error {color: red; text-align: center;}
    </style>
</head>
<body>
<div class="login-container">
    <h2>用户登录</h2>
    <% String message = (String) request.getAttribute("message"); if (message != null && !message.isEmpty()) { %>
        <p class="message"><%= message %></p>
    <% } %>
    <% String error = (String) request.getAttribute("error"); if (error != null && !error.isEmpty()) { %>
        <p class="error"><%= error %></p>
    <% } %>

    <form action="<%= request.getContextPath() %>/login" method="post">
        <div class="form-group">
            <label for="username">用户名：</label>
            <input type="text" id="username" name="username" required placeholder="请输入用户名">
        </div>
        <div class="form-group">
            <label for="password">密码：</label>
            <input type="password" id="password" name="password" required placeholder="请输入密码">
        </div>
        <input type="submit" class="btn" value="登录">
        <p style="margin-top:15px; text-align:center;">还没有账号？<a href="<%= request.getContextPath() %>/register">立即注册</a></p>
    </form>
</div>
</body>
</html>