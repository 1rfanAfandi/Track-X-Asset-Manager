package asset.model;

/**
 * Class Mesin
 */
public class Mesin extends Asset {

    private double dayaKW;
    private String tipeMesin;

    public Mesin(int idAsset,
                 String nama,
                 String status,
                 String lokasi,
                 double dayaKW,
                 String tipeMesin) {

        super(idAsset, nama, status, lokasi);

        this.dayaKW = dayaKW;
        this.tipeMesin = tipeMesin;
    }

    @Override
    public String getInfo() {

        return "Mesin{" +
                "idAsset=" + idAsset +
                ", nama='" + nama + '\'' +
                ", status='" + status + '\'' +
                ", lokasi='" + lokasi + '\'' +
                ", dayaKW=" + dayaKW +
                ", tipeMesin='" + tipeMesin + '\'' +
                '}';
    }

    public double getDayaKW() {
        return dayaKW;
    }

    public String getTipeMesin() {
        return tipeMesin;
    }

    public void setDayaKW(double dayaKW) {
        this.dayaKW = dayaKW;
    }

    public void setTipeMesin(String tipeMesin) {
        this.tipeMesin = tipeMesin;
    }
}