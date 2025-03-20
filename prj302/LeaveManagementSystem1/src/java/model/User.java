package model;

public class User {
    private int userId;
    private String username;
    private String role;
    private String department;
    private int managerId;

    public User() {}

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public int getManagerId() { return managerId; }
    public void setManagerId(int managerId) { this.managerId = managerId; }
}