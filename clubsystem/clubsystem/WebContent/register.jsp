<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户注册</title>
    <style>
        body {font-family: "微软雅黑", sans-serif; background-color: #f4f4f4;}
        .register-container {width: 400px; margin: 50px auto; padding: 40px; background: #fff; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1);} 
        h2 {text-align: center; margin-bottom: 20px;}
        .form-group {margin-bottom: 15px;}
        label {display: block; margin-bottom: 5px;}
        input[type=text], input[type=password] {width: 100%; padding: 8px; box-sizing: border-box;}
        .btn {width: 100%; padding: 10px; background-color: #2196F3; color: #fff; border: none; border-radius: 4px; cursor: pointer;}
        .btn:hover {background-color: #1e88e5;}
        .error {color: red; text-align: center;}
    </style>
</head>
<body>
<div class="register-container">
    <h2>用户注册</h2>
    <% String error = (String) request.getAttribute("error"); if (error != null && !error.isEmpty()) { %>
        <p class="error"><%= error %></p>
    <% } %>

    <form action="<%= request.getContextPath() %>/register" method="post">
        <div class="form-group">
            <label for="username">用户名：</label>
            <input type="text" id="username" name="username" required placeholder="请输入用户名">
        </div>
        <div class="form-group">
            <label for="password">密码：</label>
            <input type="password" id="password" name="password" required placeholder="请输入密码">
        </div>
        <div class="form-group">
            <label for="realName">真实姓名：</label>
            <input type="text" id="realName" name="realName" required placeholder="请输入真实姓名">
        </div>
        <div class="form-group">
            <label for="studentNo">学号：</label>
            <input type="text" id="studentNo" name="studentNo" required placeholder="请输入学号">
        </div>
        <div class="form-group">
            <label for="phone">手机号：</label>
            <input type="text" id="phone" name="phone" required placeholder="请输入手机号">
        </div>
        <input type="submit" class="btn" value="注册">
        <p style="margin-top:15px; text-align:center;">已有账号？<a href="<%= request.getContextPath() %>/login">立即登录</a></p>
    </form>
</div>
</body>
</html>