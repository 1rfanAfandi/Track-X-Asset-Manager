// ==========================================
// CLASS: Pengguna (ABSTRACT CLASS)
// ==========================================
// Class ini adalah class abstrak yang menjadi parent/induk untuk Admin dan Karyawan
// Tidak bisa dibuat objek langsung, hanya sebagai template

// Deklarasi package (opsional, dikomentari karena struktur tanpa src)
// package model;

// Class abstract: tidak bisa diinstansiasi langsung
public abstract class Pengguna {
    
    // ========== ATTRIBUTES / PROPERTIES ==========
    // protected -> bisa diakses oleh class turunan (Admin dan Karyawan)
    
    protected int id;           // ID unik untuk setiap pengguna
    protected String nama;      // Nama lengkap pengguna
    protected String email;     // Email untuk login (unik)
    protected String password;  // Password yang sudah di-hash (SHA-256)
    
    // ========== CONSTRUCTOR ==========
    // Constructor untuk menginisialisasi objek Pengguna
    // Dipanggil oleh class anak (Admin/Karyawan) melalui super()
    public Pengguna(int id, String nama, String email, String password) {
        this.id = id;               // Set ID pengguna
        this.nama = nama;           // Set nama pengguna
        this.email = email;         // Set email pengguna
        this.password = password;   // Set password (sudah ter-hash)
    }
    
    // ========== ABSTRACT METHODS ==========
    // Method abstract -> WAJIB diimplementasikan oleh class turunan
    // Setiap role (Admin/Karyawan) harus memberikan implementasi sendiri
    
    // Method untuk mendapatkan role pengguna (ADMIN atau KARYAWAN)
    public abstract String getRole();
    
    // Method untuk mendapatkan info dashboard sesuai role
    public abstract String getDashboardInfo();
    
    // ========== GETTERS ==========
    // Method untuk mengambil nilai dari atribut private/protected
    
    public int getId() {
        return id;          // Mengembalikan nilai ID
    }
    
    public String getNama() {
        return nama;        // Mengembalikan nama pengguna
    }
    
    public String getEmail() {
        return email;       // Mengembalikan email pengguna
    }
    
    public String getPassword() {
        return password;    // Mengembalikan password (hashed)
    }
    
    // ========== SETTERS ==========
    // Method untuk mengubah nilai atribut
    
    public void setNama(String nama) {
        this.nama = nama;   // Mengubah nama pengguna
    }
    
    public void setEmail(String email) {
        this.email = email; // Mengubah email pengguna
    }
    
    // ========== OVERRIDE METHOD ==========
    // Override method toString dari class Object
    // Untuk representasi string dari objek Pengguna
    @Override
    public String toString() {
        // Mengembalikan string berisi informasi pengguna
        return "Pengguna{id=" + id + ", nama='" + nama + "', email='" + email + "', role='" + getRole() + "'}";
    }
}