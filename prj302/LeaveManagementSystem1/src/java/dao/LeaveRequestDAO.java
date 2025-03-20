package dao;

import model.LeaveRequest;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LeaveRequestDAO {
    public void createLeaveRequest(LeaveRequest request) {
        String query = "INSERT INTO LeaveRequests (title, fromDate, toDate, reason, createdBy, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, request.getTitle());
            ps.setDate(2, new java.sql.Date(request.getFromDate().getTime()));
            ps.setDate(3, new java.sql.Date(request.getToDate().getTime()));
            ps.setString(4, request.getReason());
            ps.setInt(5, request.getCreatedBy());
            ps.setString(6, "Inprogress"); // Đảm bảo trạng thái mặc định là "Inprogress"
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<LeaveRequest> getLeaveRequestsByUser(int userId) {
        List<LeaveRequest> requests = new ArrayList<>();
        String query = "SELECT * FROM LeaveRequests WHERE createdBy = ? ORDER BY requestId DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    requests.add(mapResultSetToLeaveRequest(rs));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return requests;
    }

    public List<LeaveRequest> getLeaveRequestsForManager(int managerId) {
        List<LeaveRequest> requests = new ArrayList<>();
        String query = "SELECT lr.* FROM LeaveRequests lr JOIN Users u ON lr.createdBy = u.userId WHERE u.managerId = ? ORDER BY lr.requestId DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, managerId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    requests.add(mapResultSetToLeaveRequest(rs));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return requests;
    }

    public void updateLeaveStatus(int requestId, String status, int processedBy) {
        String query = "UPDATE LeaveRequests SET status = ?, processedBy = ? WHERE requestId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, status);
            ps.setInt(2, processedBy);
            ps.setInt(3, requestId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private LeaveRequest mapResultSetToLeaveRequest(ResultSet rs) throws Exception {
        LeaveRequest req = new LeaveRequest();
        req.setRequestId(rs.getInt("requestId"));
        req.setTitle(rs.getString("title"));
        req.setFromDate(rs.getDate("fromDate"));
        req.setToDate(rs.getDate("toDate"));
        req.setReason(rs.getString("reason"));
        req.setCreatedBy(rs.getInt("createdBy"));
        req.setStatus(rs.getString("status"));
        
        // Xử lý processedBy có thể null
        int processedBy = rs.getInt("processedBy");
        if (rs.wasNull()) {
            req.setProcessedBy(null);
        } else {
            req.setProcessedBy(processedBy);
        }
        
        return req;
    }
}