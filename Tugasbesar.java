/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tugasbesar;
import model.Peminjaman;
import service.PeminjamanService;
/**
 *
 * @author RegaliaXV
 */
public class Tugasbesar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PeminjamanService service =
                new PeminjamanService();
        Peminjaman p1 =
                new Peminjaman(
                        1,
      /*tanggalpinjam*/ "08-06-2026",
      /*namaasset*/     "Laptop Lenovo",
      /*namapeminjam*/  "Ahmad");
        service.pinjamAsset(p1);
        service.tampilSemuaPeminjaman();
    }    
}
