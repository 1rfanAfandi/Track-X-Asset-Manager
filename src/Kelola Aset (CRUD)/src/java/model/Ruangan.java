package model;

public class Ruangan extends Asset {
    private int kapasitas;
    
    // Constructor kosong
    public Ruangan() {}
    
    // Constructor dengan parameter
    public Ruangan(int idAsset, String nama, String status, String lokasi, int kapasitas) {
        super(idAsset, nama, status, lokasi);
        this.kapasitas = kapasitas;
    }
    
    @Override
    public String getInfo() {
        return "Kapasitas: " + kapasitas + " orang";
    }
    
    // GETTER dan SETTER
    public int getKapasitas() {
        return kapasitas;
    }
    
    public void setKapasitas(int kapasitas) {
        this.kapasitas = kapasitas;
    }
}