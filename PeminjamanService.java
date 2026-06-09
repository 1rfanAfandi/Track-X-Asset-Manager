/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import model.Peminjaman;
import java.util.ArrayList;
/**
 *
 * @author RegaliaXV
 */
public class PeminjamanService {
    private ArrayList<Peminjaman> daftarPeminjaman;
    
    public PeminjamanService() {
        daftarPeminjaman = new ArrayList<>();
    }
    public void pinjamAsset(Peminjaman peminjaman) {
        daftarPeminjaman.add(peminjaman);
        System.out.println("Peminjaman berhasil disimpan");
    }
    public void tampilSemuaPeminjaman() {
        for(Peminjaman p : daftarPeminjaman) {
            p.tampilInfo();
            System.out.println("------------------");
        }
    }
}
