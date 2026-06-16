package asset.dao;

import asset.model.*;
import loginauth.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AssetDAO {

    public void insert(Asset asset) {

        String sql =
                "INSERT INTO asset " +
                "(nama, jenis_asset, status, lokasi, kapasitas, merk, model, plat_nomor, jenis_kendaraan, daya_kw, tipe_mesin) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            fillStatement(stmt, asset);

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Asset> selectAll() {

        List<Asset> assets = new ArrayList<>();

        String sql = "SELECT * FROM asset";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {

            while (rs.next()) {

                assets.add(createAssetFromResultSet(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return assets;
    }

    public List<Asset> selectByStatus(String status) {

        List<Asset> assets = new ArrayList<>();

        String sql = "SELECT * FROM asset WHERE UPPER(status) = ?";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, status == null ? "" : status.toUpperCase());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    assets.add(createAssetFromResultSet(rs));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return assets;
    }

    public int countAll() {

        String sql = "SELECT COUNT(*) AS total FROM asset";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {

            if (rs.next()) {
                return rs.getInt("total");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int countByStatus(String status) {

        String sql = "SELECT COUNT(*) AS total FROM asset WHERE UPPER(status) = ?";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, status == null ? "" : status.toUpperCase());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public Asset findById(int idAsset) {

        String sql =
                "SELECT * FROM asset WHERE id_asset = ?";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, idAsset);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                return createAssetFromResultSet(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void update(Asset asset) {

        String sql =
                "UPDATE asset SET " +
                "nama=?, status=?, lokasi=?, " +
                "kapasitas=?, merk=?, model=?, " +
                "plat_nomor=?, jenis_kendaraan=?, " +
                "daya_kw=?, tipe_mesin=? " +
                "WHERE id_asset=?";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, asset.getNama());
            stmt.setString(2, asset.getStatus());
            stmt.setString(3, asset.getLokasi());

            if (asset instanceof Ruangan r) {

                stmt.setInt(4, r.getKapasitas());

                stmt.setNull(5, java.sql.Types.VARCHAR);
                stmt.setNull(6, java.sql.Types.VARCHAR);
                stmt.setNull(7, java.sql.Types.VARCHAR);
                stmt.setNull(8, java.sql.Types.VARCHAR);
                stmt.setNull(9, java.sql.Types.DOUBLE);
                stmt.setNull(10, java.sql.Types.VARCHAR);

            } else if (asset instanceof PerangkatElektronik p) {

                stmt.setNull(4, java.sql.Types.INTEGER);

                stmt.setString(5, p.getMerk());
                stmt.setString(6, p.getModel());

                stmt.setNull(7, java.sql.Types.VARCHAR);
                stmt.setNull(8, java.sql.Types.VARCHAR);
                stmt.setNull(9, java.sql.Types.DOUBLE);
                stmt.setNull(10, java.sql.Types.VARCHAR);

            } else if (asset instanceof Kendaraan k) {

                stmt.setNull(4, java.sql.Types.INTEGER);
                stmt.setNull(5, java.sql.Types.VARCHAR);
                stmt.setNull(6, java.sql.Types.VARCHAR);

                stmt.setString(7, k.getPlatNomor());
                stmt.setString(8, k.getJenis());

                stmt.setNull(9, java.sql.Types.DOUBLE);
                stmt.setNull(10, java.sql.Types.VARCHAR);

            } else if (asset instanceof Mesin m) {

                stmt.setNull(4, java.sql.Types.INTEGER);
                stmt.setNull(5, java.sql.Types.VARCHAR);
                stmt.setNull(6, java.sql.Types.VARCHAR);
                stmt.setNull(7, java.sql.Types.VARCHAR);
                stmt.setNull(8, java.sql.Types.VARCHAR);

                stmt.setDouble(9, m.getDayaKW());
                stmt.setString(10, m.getTipeMesin());
            }

            stmt.setInt(11, asset.getIdAsset());

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int idAsset) {

        String sql =
                "DELETE FROM asset WHERE id_asset=?";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, idAsset);

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fillStatement(
            PreparedStatement stmt,
            Asset asset) throws Exception {

        stmt.setString(1, asset.getNama());

        if (asset instanceof Ruangan r) {

            stmt.setString(2, "RUANGAN");

            stmt.setString(3, asset.getStatus());
            stmt.setString(4, asset.getLokasi());

            stmt.setInt(5, r.getKapasitas());

            stmt.setNull(6, java.sql.Types.VARCHAR);
            stmt.setNull(7, java.sql.Types.VARCHAR);
            stmt.setNull(8, java.sql.Types.VARCHAR);
            stmt.setNull(9, java.sql.Types.VARCHAR);
            stmt.setNull(10, java.sql.Types.DOUBLE);
            stmt.setNull(11, java.sql.Types.VARCHAR);

        } else if (asset instanceof PerangkatElektronik p) {

            stmt.setString(2, "PERANGKAT_ELEKTRONIK");

            stmt.setString(3, asset.getStatus());
            stmt.setString(4, asset.getLokasi());

            stmt.setNull(5, java.sql.Types.INTEGER);

            stmt.setString(6, p.getMerk());
            stmt.setString(7, p.getModel());

            stmt.setNull(8, java.sql.Types.VARCHAR);
            stmt.setNull(9, java.sql.Types.VARCHAR);
            stmt.setNull(10, java.sql.Types.DOUBLE);
            stmt.setNull(11, java.sql.Types.VARCHAR);

        } else if (asset instanceof Kendaraan k) {

            stmt.setString(2, "KENDARAAN");

            stmt.setString(3, asset.getStatus());
            stmt.setString(4, asset.getLokasi());

            stmt.setNull(5, java.sql.Types.INTEGER);
            stmt.setNull(6, java.sql.Types.VARCHAR);
            stmt.setNull(7, java.sql.Types.VARCHAR);

            stmt.setString(8, k.getPlatNomor());
            stmt.setString(9, k.getJenis());

            stmt.setNull(10, java.sql.Types.DOUBLE);
            stmt.setNull(11, java.sql.Types.VARCHAR);

        } else if (asset instanceof Mesin m) {

            stmt.setString(2, "MESIN");

            stmt.setString(3, asset.getStatus());
            stmt.setString(4, asset.getLokasi());

            stmt.setNull(5, java.sql.Types.INTEGER);
            stmt.setNull(6, java.sql.Types.VARCHAR);
            stmt.setNull(7, java.sql.Types.VARCHAR);
            stmt.setNull(8, java.sql.Types.VARCHAR);
            stmt.setNull(9, java.sql.Types.VARCHAR);

            stmt.setDouble(10, m.getDayaKW());
            stmt.setString(11, m.getTipeMesin());
        }
    }

    private Asset createAssetFromResultSet(
            ResultSet rs) throws Exception {

        String jenis = rs.getString("jenis_asset");

        switch (jenis) {

            case "RUANGAN":
                return new Ruangan(
                        rs.getInt("id_asset"),
                        rs.getString("nama"),
                        rs.getString("status"),
                        rs.getString("lokasi"),
                        rs.getInt("kapasitas")
                );

            case "PERANGKAT_ELEKTRONIK":
                return new PerangkatElektronik(
                        rs.getInt("id_asset"),
                        rs.getString("nama"),
                        rs.getString("status"),
                        rs.getString("lokasi"),
                        rs.getString("merk"),
                        rs.getString("model")
                );

            case "KENDARAAN":
                return new Kendaraan(
                        rs.getInt("id_asset"),
                        rs.getString("nama"),
                        rs.getString("status"),
                        rs.getString("lokasi"),
                        rs.getString("plat_nomor"),
                        rs.getString("jenis_kendaraan")
                );

            case "MESIN":
                return new Mesin(
                        rs.getInt("id_asset"),
                        rs.getString("nama"),
                        rs.getString("status"),
                        rs.getString("lokasi"),
                        rs.getDouble("daya_kw"),
                        rs.getString("tipe_mesin")
                );
        }

        return null;
    }
    public void updateStatus(
        int idAsset,
        String status) {

    String sql =
            "UPDATE asset " +
            "SET status=? " +
            "WHERE id_asset=?";

    try (

            Connection conn =
                    DatabaseConnection
                            .getConnection();

            PreparedStatement stmt =
                    conn.prepareStatement(sql)

    ) {

        stmt.setString(
                1,
                status);

        stmt.setInt(
                2,
                idAsset);

        stmt.executeUpdate();

    } catch (Exception e) {

        e.printStackTrace();
    }
}
}