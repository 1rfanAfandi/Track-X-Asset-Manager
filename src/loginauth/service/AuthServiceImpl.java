package loginauth.service;

import loginauth.database.PenggunaDAO;
import loginauth.model.Pengguna;
import loginauth.utils.AuthLogger;
import loginauth.utils.PasswordUtil;

public class AuthServiceImpl implements AuthService {

    private Pengguna currentUser;

    @Override
    public Pengguna login(String email, String password) {

        PenggunaDAO penggunaDAO = new PenggunaDAO();

        Pengguna pengguna = penggunaDAO.findByEmail(email);

        if (pengguna != null &&
                PasswordUtil.verifyPassword(
                        password,
                        pengguna.getPassword())) {

            currentUser = pengguna;

            AuthLogger.log(
                    "Login berhasil : " + email);

            return currentUser;
        }

        AuthLogger.log(
                "Login gagal : " + email);

        return null;
    }

    @Override
    public void logout() {

        if (currentUser != null) {

            AuthLogger.log(
                    "Logout : " +
                    currentUser.getEmail());
        }

        currentUser = null;
    }

    @Override
    public Pengguna getCurrentUser() {
        return currentUser;
    }
}