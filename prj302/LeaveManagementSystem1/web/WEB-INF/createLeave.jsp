<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create Leave Request</title>
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
    <h2>Create Leave Request</h2>
    <% if (request.getAttribute("error") != null) { %>
        <p style="color:red;"><%= request.getAttribute("error") %></p>
    <% } %>
    <form action="leaveRequest" method="post">
        <input type="hidden" name="action" value="create">
        <label>Title:</label><br>
        <input type="text" name="title" required><br>
        <label>From Date:</label><br>
        <input type="date" name="fromDate" required><br>
        <label>To Date:</label><br>
        <input type="date" name="toDate" required><br>
        <label>Reason:</label><br>
        <textarea name="reason" required></textarea><br>
        <input type="submit" value="Submit">
    </form>
    <a href="dashboard.jsp">Back to Dashboard</a>
</body>
</html>
