package loginauth.test;

import loginauth.controller.LoginController;
import loginauth.model.Pengguna;
import loginauth.service.AuthService;
import loginauth.service.AuthServiceImpl;
import loginauth.utils.PasswordUtil;

public class LoginTest {

    public static void main(String[] args) {

        System.out.println("===== TEST HASH PASSWORD =====");

        String hash =
                PasswordUtil.hashPassword(
                        "admin123");

        System.out.println("Hash Password:");
        System.out.println(hash);

        System.out.println();

        System.out.println("===== TEST LOGIN =====");

        AuthService authService =
                new AuthServiceImpl();

        LoginController controller =
                new LoginController(authService);

        Pengguna user =
                controller.login(
                        "admin@mail.com",
                        "admin123");

        if (user != null) {

            System.out.println(
                    "Login Berhasil");

            System.out.println(
                    "Nama : " +
                    user.getNama());

            System.out.println(
                    "Role : " +
                    user.getRole());

            System.out.println(
                    user.getDashboardInfo());

        } else {

            System.out.println(
                    "Login Gagal");
        }
    }
}