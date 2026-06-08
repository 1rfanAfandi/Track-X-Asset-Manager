package loginauth.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import loginauth.model.Admin;
import loginauth.model.Karyawan;
import loginauth.model.Pengguna;

public class PenggunaDAO {

    public Pengguna findByEmail(String email) {

        String sql =
                "SELECT * FROM pengguna WHERE email = ?";

        try (
                Connection conn =
                        DatabaseConnection.getConnection();

                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ) {

            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                int id = rs.getInt("id");
                String nama = rs.getString("nama");
                String dbEmail = rs.getString("email");
                String password = rs.getString("password");
                String role = rs.getString("role");

                if ("ADMIN".equals(role)) {

                    return new Admin(
                            id,
                            nama,
                            dbEmail,
                            password,
                            "SUPER ADMIN",
                            "IT"
                    );
                }

                return new Karyawan(
                        id,
                        nama,
                        dbEmail,
                        password,
                        "Staff",
                        "-"
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }
}