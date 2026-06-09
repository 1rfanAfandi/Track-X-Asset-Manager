package model;

public abstract class Asset {
    protected int idAsset;
    protected String nama;
    protected String status;
    protected String lokasi;
    
    // Constructor kosong
    public Asset() {}
    
    // Constructor dengan parameter
    public Asset(int idAsset, String nama, String status, String lokasi) {
        this.idAsset = idAsset;
        this.nama = nama;
        this.status = status;
        this.lokasi = lokasi;
    }
    
    // Abstract method - wajib diimplementasikan oleh subclass
    public abstract String getInfo();
    
    // Method untuk cek ketersediaan
    public boolean isAvailable() {
        return "Tersedia".equalsIgnoreCase(status);
    }
    
    // GETTER dan SETTER
    public int getIdAsset() {
        return idAsset;
    }
    
    public void setIdAsset(int idAsset) {
        this.idAsset = idAsset;
    }
    
    public String getNama() {
        return nama;
    }
    
    public void setNama(String nama) {
        this.nama = nama;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getLokasi() {
        return lokasi;
    }
    
    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }
}