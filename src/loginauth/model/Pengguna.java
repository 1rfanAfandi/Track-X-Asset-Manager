package loginauth.model;

/**
 * Class abstrak sebagai parent dari Admin dan Karyawan
 */
public abstract class Pengguna {

    protected int id;
    protected String nama;
    protected String email;
    protected String password;

    public Pengguna(int id, String nama, String email, String password) {
        this.id = id;
        this.nama = nama;
        this.email = email;
        this.password = password;
    }

    // Abstract Method
    public abstract String getRole();

    public abstract String getDashboardInfo();

    // Getter
    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    // Setter
    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}