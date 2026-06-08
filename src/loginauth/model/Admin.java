package loginauth.model;

/**
 * Class Admin
 * Inheritance dari Pengguna
 */
public class Admin extends Pengguna {

    private String level;
    private String departemen;

    public Admin(int id,
                 String nama,
                 String email,
                 String password,
                 String level,
                 String departemen) {

        super(id, nama, email, password);

        this.level = level;
        this.departemen = departemen;
    }

    @Override
    public String getRole() {
        return "ADMIN";
    }

    @Override
    public String getDashboardInfo() {
        return "Dashboard Admin";
    }

    public String getLevel() {
        return level;
    }

    public String getDepartemen() {
        return departemen;
    }

    public void kelolaAset() {
        System.out.println("Admin mengelola aset");
    }
}