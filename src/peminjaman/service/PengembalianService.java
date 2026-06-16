package peminjaman.service;

import java.util.Date;

import peminjaman.model.Peminjaman;

public class PengembalianService {

    public double hitungDenda(
            Peminjaman peminjaman,
            Date tanggalKembali) {

        peminjaman
                .kembalikanAsset(
                        tanggalKembali);

        return peminjaman
                .hitungDenda();
    }

    public void prosesPengembalian(
            Peminjaman peminjaman,
            Date tanggalKembali) {

        peminjaman
                .kembalikanAsset(
                        tanggalKembali);
    }
}