package model;

public class Mesin extends Asset {
    private double dayaKW;
    private String tipeMesin;
    
    // Constructor kosong
    public Mesin() {}
    
    // Constructor dengan parameter
    public Mesin(int idAsset, String nama, String status, String lokasi, double dayaKW, String tipeMesin) {
        super(idAsset, nama, status, lokasi);
        this.dayaKW = dayaKW;
        this.tipeMesin = tipeMesin;
    }
    
    @Override
    public String getInfo() {
        return "Daya: " + dayaKW + " kW, Tipe: " + tipeMesin;
    }
    
    // GETTER dan SETTER
    public double getDayaKW() {
        return dayaKW;
    }
    
    public void setDayaKW(double dayaKW) {
        this.dayaKW = dayaKW;
    }
    
    public String getTipeMesin() {
        return tipeMesin;
    }
    
    public void setTipeMesin(String tipeMesin) {
        this.tipeMesin = tipeMesin;
    }
}