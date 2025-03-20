<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.LeaveRequestDAO, model.LeaveRequest, model.User, java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>View Leave Requests</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <% 
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        LeaveRequestDAO leaveDAO = new LeaveRequestDAO();
        List<LeaveRequest> requests = "Employee".equals(user.getRole()) 
            ? leaveDAO.getLeaveRequestsByUser(user.getUserId()) 
            : leaveDAO.getLeaveRequestsForManager(user.getUserId());
    %>
    <h2>Leave Requests</h2>
    <table border="1">
        <tr>
            <th>Title</th><th>From</th><th>To</th><th>Reason</th><th>Status</th><th>Action</th>
        </tr>
        <% for (LeaveRequest req : requests) { %>
        <tr>
            <td><%= req.getTitle() %></td>
            <td><%= req.getFromDate() %></td>
            <td><%= req.getToDate() %></td>
            <td><%= req.getReason() %></td>
            <td><%= req.getStatus() %></td>
            <td>
                <% if ("Manager".equals(user.getRole()) && "Inprogress".equals(req.getStatus())) { %>
                    <a href="reviewLeave.jsp?requestId=<%= req.getRequestId() %>">Review</a>
                <% } %>
            </td>
        </tr>
        <% } %>
    </table>
    <a href="dashboard.jsp">Back to Dashboard</a>
</body>
</html>
