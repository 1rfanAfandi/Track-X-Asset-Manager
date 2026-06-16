package asset.model;

/**
 * Class PerangkatElektronik
 */
public class PerangkatElektronik extends Asset {

    private String merk;
    private String model;

    public PerangkatElektronik(int idAsset,
                               String nama,
                               String status,
                               String lokasi,
                               String merk,
                               String model) {

        super(idAsset, nama, status, lokasi);

        this.merk = merk;
        this.model = model;
    }

    @Override
    public String getInfo() {

        return "PerangkatElektronik{" +
                "idAsset=" + idAsset +
                ", nama='" + nama + '\'' +
                ", status='" + status + '\'' +
                ", lokasi='" + lokasi + '\'' +
                ", merk='" + merk + '\'' +
                ", model='" + model + '\'' +
                '}';
    }

    public String getMerk() {
        return merk;
    }

    public String getModel() {
        return model;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public void setModel(String model) {
        this.model = model;
    }
}