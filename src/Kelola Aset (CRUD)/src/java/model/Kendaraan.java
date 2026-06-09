package model;

public class Kendaraan extends Asset {
    private String platNomor;
    private String jenis;
    
    // Constructor kosong
    public Kendaraan() {}
    
    // Constructor dengan parameter
    public Kendaraan(int idAsset, String nama, String status, String lokasi, String platNomor, String jenis) {
        super(idAsset, nama, status, lokasi);
        this.platNomor = platNomor;
        this.jenis = jenis;
    }
    
    @Override
    public String getInfo() {
        return "Plat: " + platNomor + ", Jenis: " + jenis;
    }
    
    // GETTER dan SETTER
    public String getPlatNomor() {
        return platNomor;
    }
    
    public void setPlatNomor(String platNomor) {
        this.platNomor = platNomor;
    }
    
    public String getJenis() {
        return jenis;
    }
    
    public void setJenis(String jenis) {
        this.jenis = jenis;
    }
}