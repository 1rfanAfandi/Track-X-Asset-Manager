package peminjaman.service;

import asset.model.Asset;
import peminjaman.dao.PeminjamanDAO;
import peminjaman.model.Peminjaman;

import java.util.ArrayList;
import java.util.List;

public class PeminjamanService {

    private List<Peminjaman> daftarPeminjaman;

    public PeminjamanService() {

        daftarPeminjaman =
                new ArrayList<>();
    }

    public boolean cekKetersediaan(
            Asset asset) {

        return asset.isAvailable();
    }

    public void buatPeminjaman(
            Peminjaman peminjaman) {

        if (cekKetersediaan(
                peminjaman.getAsset())) {

            peminjaman
                    .getAsset()
                    .setStatus("DIPINJAM");

            daftarPeminjaman
                    .add(peminjaman);
        }
    }

    public void updateStatusPeminjaman(
            int idPeminjaman,
            String status) {

        for (Peminjaman p
                : daftarPeminjaman) {

            if (p.getIdPeminjaman()
                    == idPeminjaman) {

                if ("DIKEMBALIKAN"
                        .equals(status)) {

                    p.getAsset()
                            .setStatus(
                                    "TERSEDIA");
                }

                break;
            }
        }
    }

    public List<Peminjaman>
    getDaftarPeminjaman() {

        return daftarPeminjaman;
    }
    public void simpanPeminjaman(
        Peminjaman peminjaman) {

    PeminjamanDAO dao =
            new PeminjamanDAO();

    dao.insert(
            peminjaman);

    peminjaman.getAsset()
            .setStatus(
                    "DIPINJAM");
}
}