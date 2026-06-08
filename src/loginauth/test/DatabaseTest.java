package loginauth.test;

import java.sql.Connection;
import loginauth.database.DatabaseConnection;

public class DatabaseTest {

    public static void main(String[] args) {

        Connection conn = DatabaseConnection.getConnection();

        if (conn != null) {
            System.out.println("Koneksi Database Berhasil!");
        } else {
            System.out.println("Koneksi Database Gagal!");
        }
    }
}