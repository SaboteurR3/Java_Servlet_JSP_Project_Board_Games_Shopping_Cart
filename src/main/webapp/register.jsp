<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
    <%@include file="includes/header.jsp"%>
</head>
<body>
    <div class="background">
        <div class="cotr">
            <form action="user-signup" method="POST" class="login-email">
                <p class="login-text" style="font-size: 2rem; font-weight: 800;">Register</p>
                <div class="input-group">
                    <input type="text" placeholder="Username" name="name" required>
                </div>
                <div class="input-group">
                    <input type="email" placeholder="Email" name="email" required>
                </div>
                <div class="input-group">
                    <input type="password" placeholder="Password" name="password" required>
                </div>
                <div class="input-group">
                    <button name="submit" class="btn">Register</button>
                </div>
                <p class="login-register-text">Have an account? <a href="login.jsp">Login Here</a>.</p>
                <p class="login-register-text"><a href="index.jsp">HOME PAGE</a>.</p>
            </form>
        </div>
    </div>

    <%@include file="includes/footer.jsp"%>
</body>
</html>
