package dao;

import model.User;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
    public User authenticate(String username, String password) {
        String query = "SELECT * FROM Users WHERE username = ? AND password = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, username);
            ps.setString(2, password);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setUserId(rs.getInt("userId"));
                    user.setUsername(rs.getString("username"));
                    user.setRole(rs.getString("role"));
                    user.setDepartment(rs.getString("department"));
                    
                    // Xử lý managerId có thể là NULL
                    int managerId = rs.getInt("managerId");
                    if (!rs.wasNull()) {
                        user.setManagerId(managerId);
                    } else {
                        user.setManagerId(0); // Đặt giá trị mặc định nếu NULL
                    }
                    
                    return user;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public User getUserById(int userId) {
        String query = "SELECT * FROM Users WHERE userId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setInt(1, userId);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setUserId(rs.getInt("userId"));
                    user.setUsername(rs.getString("username"));
                    user.setRole(rs.getString("role"));
                    user.setDepartment(rs.getString("department"));
                    
                    // Xử lý managerId có thể là NULL
                    int managerId = rs.getInt("managerId");
                    if (!rs.wasNull()) {
                        user.setManagerId(managerId);
                    } else {
                        user.setManagerId(0);
                    }
                    
                    return user;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
