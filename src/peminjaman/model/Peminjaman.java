package peminjaman.model;

import java.util.Date;

import asset.model.Asset;
import loginauth.model.Admin;
import loginauth.model.Karyawan;

public class Peminjaman {

    private int idPeminjaman;

    private Date tanggalPinjam;

    private Date tanggalKembali;

    private String statusPinjam;

    private Asset asset;

    private Karyawan peminjam;

    private Admin adminPencatat;

    public Peminjaman(
            int idPeminjaman,
            Date tanggalPinjam,
            Asset asset,
            Karyawan peminjam) {

        this.idPeminjaman = idPeminjaman;
        this.tanggalPinjam = tanggalPinjam;
        this.asset = asset;
        this.peminjam = peminjam;

        this.statusPinjam = "DIPINJAM";
    }

    public void kembalikanAsset(Date tanggalKembali) {

        this.tanggalKembali = tanggalKembali;

        this.statusPinjam = "DIKEMBALIKAN";

        asset.setStatus("TERSEDIA");
    }

    public double hitungDenda() {

        if (tanggalKembali == null) {
            return 0;
        }

        long selisihHari =
                (tanggalKembali.getTime()
                - tanggalPinjam.getTime())
                / (1000 * 60 * 60 * 24);

        if (selisihHari > 7) {

            return (selisihHari - 7) * 5000;
        }

        return 0;
    }

    public String getStatus() {
        return statusPinjam;
    }

    public void setAdminPencatat(Admin admin) {
        this.adminPencatat = admin;
    }

    public int getIdPeminjaman() {
        return idPeminjaman;
    }

    public Date getTanggalPinjam() {
        return tanggalPinjam;
    }

    public Date getTanggalKembali() {
        return tanggalKembali;
    }

    public Asset getAsset() {
        return asset;
    }

    public Karyawan getPeminjam() {
        return peminjam;
    }

    public Admin getAdminPencatat() {
        return adminPencatat;
    }

    @Override
    public String toString() {

        return "Peminjaman{" +
                "id=" + idPeminjaman +
                ", asset=" + asset.getNama() +
                ", peminjam=" + peminjam.getNama() +
                ", status='" + statusPinjam + '\'' +
                '}';
    }
}