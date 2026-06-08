package loginauth.service;

import loginauth.model.Pengguna;

public interface AuthService {

    Pengguna login(String email, String password);

    void logout();

    Pengguna getCurrentUser();
}