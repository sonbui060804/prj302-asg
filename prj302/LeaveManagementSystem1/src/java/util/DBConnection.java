package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static final String URL = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=LeaveManagement;trustServerCertificate=true;";
    private static final String USER = "sonbui"; // Thay bằng username của bạn
    private static final String PASSWORD = "12345"; // Thay bằng password của bạn

    public static Connection getConnection() throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}