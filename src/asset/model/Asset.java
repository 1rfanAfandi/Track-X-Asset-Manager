package asset.model;

/**
 * Abstract class Asset
 */
public abstract class Asset {

    protected int idAsset;
    protected String nama;
    protected String status;
    protected String lokasi;

    public Asset(int idAsset,
                 String nama,
                 String status,
                 String lokasi) {

        this.idAsset = idAsset;
        this.nama = nama;
        this.status = status;
        this.lokasi = lokasi;
    }

    public abstract String getInfo();

    public boolean isAvailable() {
        return "TERSEDIA".equalsIgnoreCase(status);
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIdAsset() {
        return idAsset;
    }

    public String getNama() {
        return nama;
    }

    public String getStatus() {
        return status;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    @Override
    public String toString() {
        return getInfo();
    }
}