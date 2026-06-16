package ui.login;

import javax.swing.*;

import loginauth.controller.LoginController;
import loginauth.model.Pengguna;
import loginauth.service.AuthServiceImpl;

import ui.admin.AdminDashboard;
import ui.karyawan.KaryawanDashboard;

import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    private LoginController controller;

    public LoginFrame() {

        controller =
                new LoginController(
                        new AuthServiceImpl());

        initializeUI();
    }

    private void initializeUI() {

        setTitle("Track X Asset Manager");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel =
                new JPanel(
                        new GridLayout(3, 2, 10, 10));

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        20,20,20,20));

        panel.add(new JLabel("Email"));

        txtEmail =
                new JTextField();

        panel.add(txtEmail);

        panel.add(new JLabel("Password"));

        txtPassword =
                new JPasswordField();

        panel.add(txtPassword);

        panel.add(new JLabel());

        btnLogin =
                new JButton("Login");

        panel.add(btnLogin);

        add(panel);

        btnLogin.addActionListener(e -> login());
    }

    private void login() {

        String email =
                txtEmail.getText();

        String password =
                String.valueOf(
                        txtPassword.getPassword());

        Pengguna user =
                controller.login(
                        email,
                        password);

        if (user == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Login gagal");

            return;
        }

        JOptionPane.showMessageDialog(
                this,
                "Login berhasil");

        dispose();

        if ("ADMIN"
                .equalsIgnoreCase(
                        user.getRole())) {

            new AdminDashboard(user)
                    .setVisible(true);

        } else {

            new KaryawanDashboard(user)
                    .setVisible(true);
        }
    }
}