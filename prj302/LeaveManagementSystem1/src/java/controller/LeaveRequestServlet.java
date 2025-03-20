package controller;

import dao.LeaveRequestDAO;
import model.LeaveRequest;
import model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

// Bỏ annotation @WebServlet
public class LeaveRequestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        LeaveRequestDAO leaveDAO = new LeaveRequestDAO();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        if ("create".equals(action)) {
            try {
                LeaveRequest leave = new LeaveRequest();
                leave.setTitle(request.getParameter("title"));
                
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date fromDate = sdf.parse(request.getParameter("fromDate"));
                Date toDate = sdf.parse(request.getParameter("toDate"));
                
                // Kiểm tra fromDate phải trước toDate
                if (fromDate.after(toDate)) {
                    request.setAttribute("error", "From Date must be before To Date");
                    request.getRequestDispatcher("createLeave.jsp").forward(request, response);
                    return;
                }
                
                leave.setFromDate(fromDate);
                leave.setToDate(toDate);
                leave.setReason(request.getParameter("reason"));
                leave.setCreatedBy(user.getUserId());
                // Status mặc định là "Inprogress" trong database
                
                leaveDAO.createLeaveRequest(leave);
                response.sendRedirect("dashboard.jsp");
            } catch (Exception e) {
                request.setAttribute("error", "Error creating leave request: " + e.getMessage());
                request.getRequestDispatcher("createLeave.jsp").forward(request, response);
            }
        } else if ("review".equals(action)) {
            try {
                int requestId = Integer.parseInt(request.getParameter("requestId"));
                String status = request.getParameter("status");
                
                if (!"Manager".equals(user.getRole())) {
                    response.sendRedirect("dashboard.jsp");
                    return;
                }
                
                leaveDAO.updateLeaveStatus(requestId, status, user.getUserId());
                response.sendRedirect("viewLeaves.jsp");
            } catch (Exception e) {
                response.sendRedirect("viewLeaves.jsp");
            }
        }
    }
}
