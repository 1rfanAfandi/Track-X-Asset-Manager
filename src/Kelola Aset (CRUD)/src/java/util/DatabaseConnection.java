package util;

import java.sql.*;

public class DatabaseConnection {
    
    // Konfigurasi koneksi ke MySQL
    private static final String URL = "jdbc:mysql://localhost:3306/aset_management";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    
    // Method untuk mendapatkan koneksi
    public static Connection getConnection() throws SQLException {
        try {
            // Load driver MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Buat koneksi
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            
            return connection;
            
        } catch (ClassNotFoundException e) {
            System.out.println("Driver MySQL tidak ditemukan!");
            System.out.println("Error: " + e.getMessage());
            throw new SQLException("Driver MySQL tidak terdaftar", e);
        }
    }
    
    // Method untuk test koneksi
    public static void main(String[] args) {
        try {
            Connection conn = getConnection();
            if (conn != null) {
                System.out.println("✅ Koneksi ke MySQL BERHASIL!");
                System.out.println("Database: " + conn.getCatalog());
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("❌ Koneksi GAGAL!");
            System.out.println("Error: " + e.getMessage());
        }
    }
}