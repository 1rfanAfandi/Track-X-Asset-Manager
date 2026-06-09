/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author RegaliaXV
 */
public class Peminjaman {
    private int idPeminjaman;
    private String tanggalPinjam;
    private String statusPinjam;
    private String namaAsset;
    private String namaPeminjam;

    public Peminjaman(int idPeminjaman,
                      String tanggalPinjam,
                      String namaAsset,
                      String namaPeminjam) {
        this.idPeminjaman = idPeminjaman;
        this.tanggalPinjam = tanggalPinjam;
        this.namaAsset = namaAsset;
        this.namaPeminjam = namaPeminjam;
        this.statusPinjam = "Dipinjam";
    }

    public int getIdPeminjaman() {
        return idPeminjaman;
    }

    public String getTanggalPinjam() {
        return tanggalPinjam;
    }

    public String getNamaAsset() {
        return namaAsset;
    }

    public String getNamaPeminjam() {
        return namaPeminjam;
    }

    public String getStatusPinjam() {
        return statusPinjam;
    }

    public void tampilInfo() {
        System.out.println("ID Peminjaman : " + idPeminjaman);
        System.out.println("Tanggal Pinjam : " + tanggalPinjam);
        System.out.println("Nama Asset : " + namaAsset);
        System.out.println("Peminjam : " + namaPeminjam);
        System.out.println("Status : " + statusPinjam);
    }
}
