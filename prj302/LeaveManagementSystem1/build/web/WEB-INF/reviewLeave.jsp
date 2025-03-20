<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.LeaveRequestDAO, model.LeaveRequest, model.User, java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Review Leave Request</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <% 
        User user = (User) session.getAttribute("user");
        if (user == null || !"Manager".equals(user.getRole())) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        int requestId = Integer.parseInt(request.getParameter("requestId"));
        LeaveRequestDAO leaveDAO = new LeaveRequestDAO();
        List<LeaveRequest> requests = leaveDAO.getLeaveRequestsForManager(user.getUserId());
        LeaveRequest req = null;
        
        for(LeaveRequest r : requests) {
            if(r.getRequestId() == requestId) {
                req = r;
                break;
            }
        }
        
        if(req == null) {
            response.sendRedirect("viewLeaves.jsp");
            return;
        }
    %>
    <h2>Review Leave Request</h2>
    <p>Title: <%= req.getTitle() %></p>
    <p>From: <%= req.getFromDate() %></p>
    <p>To: <%= req.getToDate() %></p>
    <p>Reason: <%= req.getReason() %></p>
    <form action="leaveRequest" method="post">
        <input type="hidden" name="action" value="review">
        <input type="hidden" name="requestId" value="<%= req.getRequestId() %>">
        <button type="submit" name="status" value="Approved">Approve</button>
        <button type="submit" name="status" value="Rejected">Reject</button>
    </form>
    <a href="viewLeaves.jsp">Back</a>
</body>
</html>
