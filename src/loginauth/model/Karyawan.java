package loginauth.model;

/**
 * Class Karyawan
 * Inheritance dari Pengguna
 */
public class Karyawan extends Pengguna {

    private String jabatan;
    private String noTelp;

    public Karyawan(int id,
                    String nama,
                    String email,
                    String password,
                    String jabatan,
                    String noTelp) {

        super(id, nama, email, password);

        this.jabatan = jabatan;
        this.noTelp = noTelp;
    }

    @Override
    public String getRole() {
        return "KARYAWAN";
    }

    @Override
    public String getDashboardInfo() {
        return "Dashboard Karyawan | Jabatan: " + jabatan;
    }

    // Getter
    public String getJabatan() {
        return jabatan;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void pinjamAset() {
        System.out.println("Karyawan meminjam aset");
    }

    @Override
    public String toString() {
        return "Karyawan{" +
                "id=" + id +
                ", nama='" + nama + '\'' +
                ", jabatan='" + jabatan + '\'' +
                ", noTelp='" + noTelp + '\'' +
                '}';
    }
}