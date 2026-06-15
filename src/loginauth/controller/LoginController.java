package loginauth.controller;
import loginauth.model.Pengguna;
import loginauth.service.AuthService;

public class LoginController {

    private AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    public Pengguna login(String email, String password) {
        return authService.login(email, password);
    }

    public void logout() {
        authService.logout();
    }
}