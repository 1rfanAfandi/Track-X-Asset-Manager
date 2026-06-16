package ui.karyawan;

import javax.swing.*;
import java.awt.*;

import loginauth.model.Pengguna;

public class KaryawanDashboard extends JFrame {

    private Pengguna user;

    public KaryawanDashboard(Pengguna user) {

        this.user = user;

        initializeUI();
    }

    private void initializeUI() {

        setTitle("Karyawan Dashboard");

        setSize(600, 400);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel =
                new JPanel(
                        new GridLayout(
                                5,
                                1,
                                10,
                                10));

        JLabel lblTitle =
                new JLabel(
                        "Selamat Datang "
                                + user.getNama(),
                        SwingConstants.CENTER);

        JButton btnAsset =
                new JButton(
                        "Lihat Asset");

        JButton btnPinjam =
                new JButton(
                        "Pinjam Asset");

        JButton btnRiwayat =
                new JButton(
                        "Riwayat");

        JButton btnLogout =
                new JButton(
                        "Logout");

        panel.add(lblTitle);
        panel.add(btnAsset);
        panel.add(btnPinjam);
        panel.add(btnRiwayat);
        panel.add(btnLogout);

        add(panel);

        // Lihat Asset
        btnAsset.addActionListener(
                e -> {

                    AssetListFrame frame =
                            new AssetListFrame();

                    frame.setVisible(true);
                });

        // Pinjam Asset
        btnPinjam.addActionListener(
                e -> {

                    PeminjamanFrame frame =
                            new PeminjamanFrame(
                                    user);

                    frame.setVisible(true);
                });

        // Riwayat
        btnRiwayat.addActionListener(
                e -> {

                    RiwayatFrame frame =
                            new RiwayatFrame();

                    frame.setVisible(true);
                });

        // Logout
        btnLogout.addActionListener(
                e -> {

                    int confirm =
                            JOptionPane.showConfirmDialog(
                                    this,
                                    "Yakin ingin logout?",
                                    "Logout",
                                    JOptionPane.YES_NO_OPTION);

                    if (confirm ==
                            JOptionPane.YES_OPTION) {

                        dispose();

                        System.exit(0);
                    }
                });
    }
}