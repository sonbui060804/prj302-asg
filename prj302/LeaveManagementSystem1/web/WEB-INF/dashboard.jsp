<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <% 
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }
    %>
    <h2>Welcome, <%= user.getUsername() %>!</h2>
    <p>Role: <%= user.getRole() %></p>
    <p>Department: <%= user.getDepartment() %></p>
    <a href="createLeave.jsp">Create Leave Request</a> |
    <a href="viewLeaves.jsp">View Leave Requests</a> |
    <a href="login.jsp">Logout</a>
</body>
</html>