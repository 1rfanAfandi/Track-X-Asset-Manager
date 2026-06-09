package model;

public class PerangkatElektronik extends Asset {
    private String merk;
    private String model;
    
    // Constructor kosong
    public PerangkatElektronik() {}
    
    // Constructor dengan parameter
    public PerangkatElektronik(int idAsset, String nama, String status, String lokasi, String merk, String model) {
        super(idAsset, nama, status, lokasi);
        this.merk = merk;
        this.model = model;
    }
    
    @Override
    public String getInfo() {
        return "Merk: " + merk + ", Model: " + model;
    }
    
    // GETTER dan SETTER
    public String getMerk() {
        return merk;
    }
    
    public void setMerk(String merk) {
        this.merk = merk;
    }
    
    public String getModel() {
        return model;
    }
    
    public void setModel(String model) {
        this.model = model;
    }
}