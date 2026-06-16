package asset.model;

/**
 * Class Ruangan
 */
public class Ruangan extends Asset {

    private int kapasitas;

    public Ruangan(int idAsset,
                   String nama,
                   String status,
                   String lokasi,
                   int kapasitas) {

        super(idAsset, nama, status, lokasi);

        this.kapasitas = kapasitas;
    }

    @Override
    public String getInfo() {

        return "Ruangan{" +
                "idAsset=" + idAsset +
                ", nama='" + nama + '\'' +
                ", status='" + status + '\'' +
                ", lokasi='" + lokasi + '\'' +
                ", kapasitas=" + kapasitas +
                '}';
    }

    public int getKapasitas() {
        return kapasitas;
    }

    public void setKapasitas(int kapasitas) {
        this.kapasitas = kapasitas;
    }
}