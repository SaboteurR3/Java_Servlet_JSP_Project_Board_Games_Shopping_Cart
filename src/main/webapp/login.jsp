<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <%@include file="includes/header.jsp"%>
</head>
<body>
    <div class="background">
        <div class="cotr">
            <form action="user-login" method="POST" class="login-email">
                <p class="login-text" style="font-size: 2rem; font-weight: 800;">Login</p>
                <div class="input-group">
                    <input type="email" name="login-email" placeholder="Enter email">
                </div>
                <div class="input-group">
                    <input type="password" name="login-password" placeholder="Password">
                </div>
                <div class="input-group">
                    <button name="submit" class="btn">Login</button>
                </div>
                <p class="login-register-text">Don't have an account? <a href="register.jsp">Register Here</a>.</p>
                <p class="login-register-text"><a href="index.jsp">HOME PAGE</a></p>
            </form>
        </div>
    </div>
</body>
</html>
