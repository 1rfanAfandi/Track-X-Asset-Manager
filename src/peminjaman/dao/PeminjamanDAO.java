package peminjaman.dao;

import loginauth.database.DatabaseConnection;

import peminjaman.model.Peminjaman;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

public class PeminjamanDAO {

    public void insert(
            Peminjaman peminjaman) {

        String sql =
                "INSERT INTO peminjaman " +
                "(id_asset,id_karyawan,id_admin," +
                "tanggal_pinjam,status_pinjam) " +
                "VALUES(?,?,?,?,?)";

        try (

                Connection conn =
                        DatabaseConnection.getConnection();

                PreparedStatement stmt =
                        conn.prepareStatement(sql)

        ) {

            stmt.setInt(
                    1,
                    peminjaman.getAsset()
                            .getIdAsset());

            stmt.setInt(
                    2,
                    peminjaman.getPeminjam()
                            .getId());

            if (peminjaman.getAdminPencatat()
                    != null) {

                stmt.setInt(
                        3,
                        peminjaman.getAdminPencatat()
                                .getId());

            } else {

                stmt.setNull(
                        3,
                        java.sql.Types.INTEGER);
            }

            stmt.setTimestamp(
                    4,
                    new java.sql.Timestamp(
                            peminjaman
                                    .getTanggalPinjam()
                                    .getTime()));

            stmt.setString(
                    5,
                    peminjaman.getStatus());

            stmt.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public List<Peminjaman> findAll() {

        List<Peminjaman> list =
                new ArrayList<>();

        String sql =
                "SELECT * FROM peminjaman";

        try (

                Connection conn =
                        DatabaseConnection.getConnection();

                PreparedStatement stmt =
                        conn.prepareStatement(sql);

                ResultSet rs =
                        stmt.executeQuery()

        ) {

            while (rs.next()) {

                System.out.println(
                        rs.getInt(
                                "id_peminjaman"));
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }

    public void updateStatus(
            int idPeminjaman,
            String status) {

        String sql =
                "UPDATE peminjaman " +
                "SET status_pinjam=? " +
                "WHERE id_peminjaman=?";

        try (

                Connection conn =
                        DatabaseConnection.getConnection();

                PreparedStatement stmt =
                        conn.prepareStatement(sql)

        ) {

            stmt.setString(
                    1,
                    status);

            stmt.setInt(
                    2,
                    idPeminjaman);

            stmt.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    public ResultSet getAllRiwayat() {

    try {

        Connection conn =
                DatabaseConnection
                        .getConnection();

        String sql =
                "SELECT p.id_peminjaman, " +
                "a.nama, " +
                "p.tanggal_pinjam, " +
                "p.status_pinjam " +
                "FROM peminjaman p " +
                "JOIN asset a " +
                "ON p.id_asset = a.id_asset";

        PreparedStatement stmt =
                conn.prepareStatement(sql);

        return stmt.executeQuery();

    } catch (Exception e) {

        e.printStackTrace();
    }

    return null;
}
public ResultSet getAssetDipinjam() {

    try {

        Connection conn =
                DatabaseConnection
                        .getConnection();

        String sql =
                "SELECT p.id_peminjaman, " +
                "a.id_asset, " +
                "a.nama, " +
                "p.tanggal_pinjam " +
                "FROM peminjaman p " +
                "JOIN asset a " +
                "ON p.id_asset = a.id_asset " +
                "WHERE p.status_pinjam='DIPINJAM'";

        PreparedStatement stmt =
                conn.prepareStatement(sql);

        return stmt.executeQuery();

    } catch (Exception e) {

        e.printStackTrace();
    }

    return null;
}
public void prosesPengembalian(
        int idPeminjaman) {

    String sql =
            "UPDATE peminjaman " +
            "SET status_pinjam='DIKEMBALIKAN', " +
            "tanggal_kembali=NOW() " +
            "WHERE id_peminjaman=?";

    try (

            Connection conn =
                    DatabaseConnection
                            .getConnection();

            PreparedStatement stmt =
                    conn.prepareStatement(sql)

    ) {

        stmt.setInt(1, idPeminjaman);

        stmt.executeUpdate();

    } catch (Exception e) {

        e.printStackTrace();
    }
}
}