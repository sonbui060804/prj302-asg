<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        form { width: 300px; padding: 20px; border: 1px solid #ddd; }
        input { margin: 5px 0; padding: 5px; width: 100%; }
    </style>
</head>
<body>
    <h2>Login</h2>
    <form action="login" method="post">
        <div>
            <label>Username:</label>
            <input type="text" name="username" required>
        </div>
        <div>
            <label>Password:</label>
            <input type="password" name="password" required>
        </div>
        <div>
            <input type="submit" value="Login">
        </div>
    </form>
    <p><a href="index.jsp">Quay lại trang chủ</a></p>
</body>
</html>