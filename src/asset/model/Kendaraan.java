package asset.model;

/**
 * Class Kendaraan
 */
public class Kendaraan extends Asset {

    private String platNomor;
    private String jenis;

    public Kendaraan(int idAsset,
                     String nama,
                     String status,
                     String lokasi,
                     String platNomor,
                     String jenis) {

        super(idAsset, nama, status, lokasi);

        this.platNomor = platNomor;
        this.jenis = jenis;
    }

    @Override
    public String getInfo() {

        return "Kendaraan{" +
                "idAsset=" + idAsset +
                ", nama='" + nama + '\'' +
                ", status='" + status + '\'' +
                ", lokasi='" + lokasi + '\'' +
                ", platNomor='" + platNomor + '\'' +
                ", jenis='" + jenis + '\'' +
                '}';
    }

    public String getPlatNomor() {
        return platNomor;
    }

    public String getJenis() {
        return jenis;
    }

    public void setPlatNomor(String platNomor) {
        this.platNomor = platNomor;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }
}