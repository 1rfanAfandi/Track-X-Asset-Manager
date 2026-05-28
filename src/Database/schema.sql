-- ==========================================
-- MEMBUAT DATABASE
-- ==========================================
CREATE DATABASE IF NOT EXISTS asset_management_system;
USE asset_management_system;

-- ==========================================
-- 1. TABEL PENGGUNA (Parent)
-- ==========================================
CREATE TABLE pengguna (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nama VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('admin', 'karyawan') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- ==========================================
-- 2. TABEL ADMIN (Child dari pengguna)
-- ==========================================
CREATE TABLE admin (
    id INT PRIMARY KEY,
    level VARCHAR(50) NOT NULL,
    departemen VARCHAR(100) NOT NULL,
    FOREIGN KEY (id) REFERENCES pengguna(id) ON DELETE CASCADE
);

-- ==========================================
-- 3. TABEL KARYAWAN (Child dari pengguna)
-- ==========================================
CREATE TABLE karyawan (
    id INT PRIMARY KEY,
    jabatan VARCHAR(100) NOT NULL,
    no_telp VARCHAR(20) NOT NULL,
    FOREIGN KEY (id) REFERENCES pengguna(id) ON DELETE CASCADE
);

-- ==========================================
-- 4. TABEL ASSET (Parent - Polymorphic)
-- ==========================================
CREATE TABLE asset (
    id_asset INT PRIMARY KEY AUTO_INCREMENT,
    nama VARCHAR(100) NOT NULL,
    status ENUM('tersedia', 'dipinjam', 'rusak', 'perbaikan') DEFAULT 'tersedia',
    lokasi VARCHAR(200),
    tipe_asset ENUM('ruangan', 'elektronik', 'kendaraan', 'mesin') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- ==========================================
-- 5. TABEL ASSET - RUANGAN
-- ==========================================
CREATE TABLE asset_ruangan (
    id_asset INT PRIMARY KEY,
    kapasitas INT NOT NULL CHECK (kapasitas > 0),
    FOREIGN KEY (id_asset) REFERENCES asset(id_asset) ON DELETE CASCADE
);

-- ==========================================
-- 6. TABEL ASSET - PERANGKAT ELEKTRONIK
-- ==========================================
CREATE TABLE asset_elektronik (
    id_asset INT PRIMARY KEY,
    merk VARCHAR(100) NOT NULL,
    model VARCHAR(100) NOT NULL,
    FOREIGN KEY (id_asset) REFERENCES asset(id_asset) ON DELETE CASCADE
);

-- ==========================================
-- 7. TABEL ASSET - KENDARAAN
-- ==========================================
CREATE TABLE asset_kendaraan (
    id_asset INT PRIMARY KEY,
    plat_nomor VARCHAR(20) UNIQUE NOT NULL,
    jenis VARCHAR(50) NOT NULL,
    FOREIGN KEY (id_asset) REFERENCES asset(id_asset) ON DELETE CASCADE
);

-- ==========================================
-- 8. TABEL ASSET - MESIN
-- ==========================================
CREATE TABLE asset_mesin (
    id_asset INT PRIMARY KEY,
    daya_kw DECIMAL(10,2) NOT NULL,
    tipe_mesin VARCHAR(100) NOT NULL,
    FOREIGN KEY (id_asset) REFERENCES asset(id_asset) ON DELETE CASCADE
);

-- ==========================================
-- 9. TABEL PEMINJAMAN (Core Business)
-- ==========================================
CREATE TABLE peminjaman (
    id_peminjaman INT PRIMARY KEY AUTO_INCREMENT,
    id_asset INT NOT NULL,
    id_peminjam INT NOT NULL,
    id_admin_pencatat INT,
    tanggal_pinjam DATE NOT NULL,
    tanggal_kembali DATE,
    status_pinjam ENUM('aktif', 'selesai', 'terlambat') DEFAULT 'aktif',
    denda DECIMAL(10,2) DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    FOREIGN KEY (id_asset) REFERENCES asset(id_asset),
    FOREIGN KEY (id_peminjam) REFERENCES karyawan(id),
    FOREIGN KEY (id_admin_pencatat) REFERENCES admin(id),
    
    CHECK (tanggal_kembali IS NULL OR tanggal_kembali >= tanggal_pinjam)
);

-- ==========================================
-- 10. TABEL RIWAYAT ASSET (Logging/Monitoring)
-- ==========================================
CREATE TABLE riwayat_asset (
    id_riwayat INT PRIMARY KEY AUTO_INCREMENT,
    id_asset INT NOT NULL,
    id_peminjaman INT,
    status_sebelum VARCHAR(50),
    status_sesudah VARCHAR(50),
    perubahan TEXT,
    waktu TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    FOREIGN KEY (id_asset) REFERENCES asset(id_asset),
    FOREIGN KEY (id_peminjaman) REFERENCES peminjaman(id_peminjaman)
);

-- ==========================================
-- 11. INDEX UNTUK OPTIMASI QUERY
-- ==========================================
-- Index untuk pencarian cepat
CREATE INDEX idx_pengguna_email ON pengguna(email);
CREATE INDEX idx_pengguna_role ON pengguna(role);
CREATE INDEX idx_asset_status ON asset(status);
CREATE INDEX idx_asset_tipe ON asset(tipe_asset);
CREATE INDEX idx_asset_lokasi ON asset(lokasi);
CREATE INDEX idx_peminjaman_status ON peminjaman(status_pinjam);
CREATE INDEX idx_peminjaman_tanggal ON peminjaman(tanggal_pinjam);
CREATE INDEX idx_peminjaman_tanggal_kembali ON peminjaman(tanggal_kembali);
CREATE INDEX idx_peminjaman_peminjam ON peminjaman(id_peminjam);
CREATE INDEX idx_peminjaman_asset ON peminjaman(id_asset);
CREATE INDEX idx_riwayat_asset ON riwayat_asset(id_asset);
CREATE INDEX idx_riwayat_waktu ON riwayat_asset(waktu);

-- ==========================================
-- 12. TRIGGER UNTUK AUTOMASI (Opsional)
-- ==========================================

-- Trigger: Update status asset saat dipinjam
DELIMITER //
CREATE TRIGGER after_peminjaman_insert
AFTER INSERT ON peminjaman
FOR EACH ROW
BEGIN
    -- Update status asset menjadi 'dipinjam'
    UPDATE asset 
    SET status = 'dipinjam' 
    WHERE id_asset = NEW.id_asset;
    
    -- Insert ke riwayat_asset
    INSERT INTO riwayat_asset (id_asset, id_peminjaman, status_sebelum, status_sesudah, perubahan)
    SELECT 
        NEW.id_asset, 
        NEW.id_peminjaman,
        'tersedia',
        'dipinjam',
        CONCAT('Asset dipinjam oleh karyawan ID: ', NEW.id_peminjam, ' pada ', NEW.tanggal_pinjam);
END//
DELIMITER ;

-- Trigger: Update status asset saat dikembalikan
DELIMITER //
CREATE TRIGGER after_peminjaman_update
AFTER UPDATE ON peminjaman
FOR EACH ROW
BEGIN
    -- Jika status berubah menjadi 'selesai' dan asset dikembalikan
    IF NEW.status_pinjam = 'selesai' AND OLD.status_pinjam != 'selesai' THEN
        -- Update status asset menjadi 'tersedia'
        UPDATE asset 
        SET status = 'tersedia' 
        WHERE id_asset = NEW.id_asset;
        
        -- Insert ke riwayat_asset
        INSERT INTO riwayat_asset (id_asset, id_peminjaman, status_sebelum, status_sesudah, perubahan)
        VALUES (
            NEW.id_asset, 
            NEW.id_peminjaman,
            'dipinjam',
            'tersedia',
            CONCAT('Asset dikembalikan pada ', NEW.tanggal_kembali, ' dengan denda Rp ', NEW.denda)
        );
    END IF;
END//
DELIMITER ;

-- ==========================================
-- 13. VIEW UNTUK LAPORAN (Opsional)
-- ==========================================

-- View: Laporan peminjaman aktif
CREATE VIEW view_peminjaman_aktif AS
SELECT 
    p.id_peminjaman,
    a.nama AS nama_asset,
    a.tipe_asset,
    k.nama AS nama_peminjam,
    k.jabatan,
    p.tanggal_pinjam,
    DATEDIFF(CURDATE(), p.tanggal_pinjam) AS hari_berjalan
FROM peminjaman p
JOIN asset a ON p.id_asset = a.id_asset
JOIN karyawan k ON p.id_peminjam = k.id
JOIN pengguna pg ON k.id = pg.id
WHERE p.status_pinjam = 'aktif';

-- View: Statistik peminjaman per asset
CREATE VIEW view_statistik_asset AS
SELECT 
    a.id_asset,
    a.nama,
    a.tipe_asset,
    COUNT(p.id_peminjaman) AS total_dipinjam,
    SUM(CASE WHEN p.status_pinjam = 'aktif' THEN 1 ELSE 0 END) AS aktif,
    SUM(CASE WHEN p.status_pinjam = 'selesai' THEN 1 ELSE 0 END) AS selesai,
    COALESCE(SUM(p.denda), 0) AS total_denda
FROM asset a
LEFT JOIN peminjaman p ON a.id_asset = p.id_asset
GROUP BY a.id_asset, a.nama, a.tipe_asset;

-- View: 10 asset paling sering dipinjam
CREATE VIEW view_top_asset AS
SELECT 
    a.id_asset,
    a.nama,
    a.tipe_asset,
    COUNT(p.id_peminjaman) AS frekuensi_peminjaman
FROM asset a
JOIN peminjaman p ON a.id_asset = p.id_asset
GROUP BY a.id_asset, a.nama, a.tipe_asset
ORDER BY frekuensi_peminjaman DESC
LIMIT 10;

-- ==========================================
-- 14. SHOW ALL TABLES (Verifikasi)
-- ==========================================
SHOW TABLES;