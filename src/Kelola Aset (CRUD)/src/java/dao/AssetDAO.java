package dao;

import model.*;
import util.DatabaseConnection;
import java.sql.*;
import java.util.*;

public class AssetDAO {
    
    // ============ CREATE ============
    // Method untuk menambah aset baru ke database
    public void insert(Asset asset) throws SQLException {
        String sql = "INSERT INTO aset (tipe, nama, status, lokasi, kapasitas, merk, model, platNomor, jenis, dayaKW, tipeMesin) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            // Set tipe aset
            stmt.setString(1, asset.getClass().getSimpleName());
            stmt.setString(2, asset.getNama());
            stmt.setString(3, asset.getStatus());
            stmt.setString(4, asset.getLokasi());
            
            // Set field sesuai jenis aset
            if (asset instanceof Ruangan) {
                stmt.setInt(5, ((Ruangan)asset).getKapasitas());
                stmt.setNull(6, Types.VARCHAR);
                stmt.setNull(7, Types.VARCHAR);
                stmt.setNull(8, Types.VARCHAR);
                stmt.setNull(9, Types.VARCHAR);
                stmt.setNull(10, Types.DOUBLE);
                stmt.setNull(11, Types.VARCHAR);
            } else if (asset instanceof PerangkatElektronik) {
                stmt.setNull(5, Types.INTEGER);
                stmt.setString(6, ((PerangkatElektronik)asset).getMerk());
                stmt.setString(7, ((PerangkatElektronik)asset).getModel());
                stmt.setNull(8, Types.VARCHAR);
                stmt.setNull(9, Types.VARCHAR);
                stmt.setNull(10, Types.DOUBLE);
                stmt.setNull(11, Types.VARCHAR);
            } else if (asset instanceof Kendaraan) {
                stmt.setNull(5, Types.INTEGER);
                stmt.setNull(6, Types.VARCHAR);
                stmt.setNull(7, Types.VARCHAR);
                stmt.setString(8, ((Kendaraan)asset).getPlatNomor());
                stmt.setString(9, ((Kendaraan)asset).getJenis());
                stmt.setNull(10, Types.DOUBLE);
                stmt.setNull(11, Types.VARCHAR);
            } else if (asset instanceof Mesin) {
                stmt.setNull(5, Types.INTEGER);
                stmt.setNull(6, Types.VARCHAR);
                stmt.setNull(7, Types.VARCHAR);
                stmt.setNull(8, Types.VARCHAR);
                stmt.setNull(9, Types.VARCHAR);
                stmt.setDouble(10, ((Mesin)asset).getDayaKW());
                stmt.setString(11, ((Mesin)asset).getTipeMesin());
            }
            
            stmt.executeUpdate();
        }
    }
    
    // ============ READ ============
    // Method untuk mengambil semua aset dari database
    public List<Asset> selectAll() throws SQLException {
        List<Asset> list = new ArrayList<>();
        String sql = "SELECT * FROM aset ORDER BY idAsset DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                String tipe = rs.getString("tipe");
                Asset asset = null;
                
                if ("Ruangan".equals(tipe)) {
                    Ruangan a = new Ruangan();
                    a.setIdAsset(rs.getInt("idAsset"));
                    a.setNama(rs.getString("nama"));
                    a.setStatus(rs.getString("status"));
                    a.setLokasi(rs.getString("lokasi"));
                    a.setKapasitas(rs.getInt("kapasitas"));
                    asset = a;
                } else if ("PerangkatElektronik".equals(tipe)) {
                    PerangkatElektronik a = new PerangkatElektronik();
                    a.setIdAsset(rs.getInt("idAsset"));
                    a.setNama(rs.getString("nama"));
                    a.setStatus(rs.getString("status"));
                    a.setLokasi(rs.getString("lokasi"));
                    a.setMerk(rs.getString("merk"));
                    a.setModel(rs.getString("model"));
                    asset = a;
                } else if ("Kendaraan".equals(tipe)) {
                    Kendaraan a = new Kendaraan();
                    a.setIdAsset(rs.getInt("idAsset"));
                    a.setNama(rs.getString("nama"));
                    a.setStatus(rs.getString("status"));
                    a.setLokasi(rs.getString("lokasi"));
                    a.setPlatNomor(rs.getString("platNomor"));
                    a.setJenis(rs.getString("jenis"));
                    asset = a;
                } else if ("Mesin".equals(tipe)) {
                    Mesin a = new Mesin();
                    a.setIdAsset(rs.getInt("idAsset"));
                    a.setNama(rs.getString("nama"));
                    a.setStatus(rs.getString("status"));
                    a.setLokasi(rs.getString("lokasi"));
                    a.setDayaKW(rs.getDouble("dayaKW"));
                    a.setTipeMesin(rs.getString("tipeMesin"));
                    asset = a;
                }
                
                if (asset != null) {
                    list.add(asset);
                }
            }
        }
        return list;
    }
    
    // Method untuk mengambil 1 aset berdasarkan ID
    public Asset selectById(int idAsset) throws SQLException {
        String sql = "SELECT * FROM aset WHERE idAsset = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idAsset);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                String tipe = rs.getString("tipe");
                
                if ("Ruangan".equals(tipe)) {
                    Ruangan a = new Ruangan();
                    a.setIdAsset(rs.getInt("idAsset"));
                    a.setNama(rs.getString("nama"));
                    a.setStatus(rs.getString("status"));
                    a.setLokasi(rs.getString("lokasi"));
                    a.setKapasitas(rs.getInt("kapasitas"));
                    return a;
                } else if ("PerangkatElektronik".equals(tipe)) {
                    PerangkatElektronik a = new PerangkatElektronik();
                    a.setIdAsset(rs.getInt("idAsset"));
                    a.setNama(rs.getString("nama"));
                    a.setStatus(rs.getString("status"));
                    a.setLokasi(rs.getString("lokasi"));
                    a.setMerk(rs.getString("merk"));
                    a.setModel(rs.getString("model"));
                    return a;
                } else if ("Kendaraan".equals(tipe)) {
                    Kendaraan a = new Kendaraan();
                    a.setIdAsset(rs.getInt("idAsset"));
                    a.setNama(rs.getString("nama"));
                    a.setStatus(rs.getString("status"));
                    a.setLokasi(rs.getString("lokasi"));
                    a.setPlatNomor(rs.getString("platNomor"));
                    a.setJenis(rs.getString("jenis"));
                    return a;
                } else if ("Mesin".equals(tipe)) {
                    Mesin a = new Mesin();
                    a.setIdAsset(rs.getInt("idAsset"));
                    a.setNama(rs.getString("nama"));
                    a.setStatus(rs.getString("status"));
                    a.setLokasi(rs.getString("lokasi"));
                    a.setDayaKW(rs.getDouble("dayaKW"));
                    a.setTipeMesin(rs.getString("tipeMesin"));
                    return a;
                }
            }
        }
        return null;
    }
    
    // ============ UPDATE ============
    // Method untuk mengedit aset
    public void update(Asset asset) throws SQLException {
        String sql = "UPDATE aset SET tipe=?, nama=?, status=?, lokasi=?, kapasitas=?, merk=?, model=?, platNomor=?, jenis=?, dayaKW=?, tipeMesin=? WHERE idAsset=?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, asset.getClass().getSimpleName());
            stmt.setString(2, asset.getNama());
            stmt.setString(3, asset.getStatus());
            stmt.setString(4, asset.getLokasi());
            
            if (asset instanceof Ruangan) {
                stmt.setInt(5, ((Ruangan)asset).getKapasitas());
                stmt.setNull(6, Types.VARCHAR);
                stmt.setNull(7, Types.VARCHAR);
                stmt.setNull(8, Types.VARCHAR);
                stmt.setNull(9, Types.VARCHAR);
                stmt.setNull(10, Types.DOUBLE);
                stmt.setNull(11, Types.VARCHAR);
            } else if (asset instanceof PerangkatElektronik) {
                stmt.setNull(5, Types.INTEGER);
                stmt.setString(6, ((PerangkatElektronik)asset).getMerk());
                stmt.setString(7, ((PerangkatElektronik)asset).getModel());
                stmt.setNull(8, Types.VARCHAR);
                stmt.setNull(9, Types.VARCHAR);
                stmt.setNull(10, Types.DOUBLE);
                stmt.setNull(11, Types.VARCHAR);
            } else if (asset instanceof Kendaraan) {
                stmt.setNull(5, Types.INTEGER);
                stmt.setNull(6, Types.VARCHAR);
                stmt.setNull(7, Types.VARCHAR);
                stmt.setString(8, ((Kendaraan)asset).getPlatNomor());
                stmt.setString(9, ((Kendaraan)asset).getJenis());
                stmt.setNull(10, Types.DOUBLE);
                stmt.setNull(11, Types.VARCHAR);
            } else if (asset instanceof Mesin) {
                stmt.setNull(5, Types.INTEGER);
                stmt.setNull(6, Types.VARCHAR);
                stmt.setNull(7, Types.VARCHAR);
                stmt.setNull(8, Types.VARCHAR);
                stmt.setNull(9, Types.VARCHAR);
                stmt.setDouble(10, ((Mesin)asset).getDayaKW());
                stmt.setString(11, ((Mesin)asset).getTipeMesin());
            }
            
            stmt.setInt(12, asset.getIdAsset());
            stmt.executeUpdate();
        }
    }
    
    // ============ DELETE ============
    // Method untuk menghapus aset
    public void delete(int idAsset) throws SQLException {
        String sql = "DELETE FROM aset WHERE idAsset=?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idAsset);
            stmt.executeUpdate();
        }
    }
}