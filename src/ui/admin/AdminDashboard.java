package ui.admin;

import javax.swing.*;
import java.awt.*;

import loginauth.model.Pengguna;

public class AdminDashboard extends JFrame {

private Pengguna admin;

public AdminDashboard(Pengguna admin) {

    this.admin = admin;

    initializeUI();
}

private void initializeUI() {

    setTitle("Admin Dashboard");

    setSize(600, 450);

    setLocationRelativeTo(null);

    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    JPanel panel =
            new JPanel(
                    new GridLayout(6, 1, 10, 10));

    JLabel lblTitle =
            new JLabel(
                    "Selamat Datang "
                            + admin.getNama(),
                    SwingConstants.CENTER);

    JButton btnAsset =
            new JButton(
                    "Kelola Asset");

    JButton btnMonitoring =
            new JButton(
                    "Monitoring Asset");

    JButton btnReport =
            new JButton(
                    "Generate Report");

    JButton btnPengembalian =
            new JButton(
                    "Pengembalian Asset");

    JButton btnLogout =
            new JButton(
                    "Logout");

    panel.add(lblTitle);
    panel.add(btnAsset);
    panel.add(btnMonitoring);
    panel.add(btnReport);
    panel.add(btnPengembalian);
    panel.add(btnLogout);

    add(panel);

    // Kelola Asset
    btnAsset.addActionListener(
            e -> {

                AssetManagementFrame frame =
                        new AssetManagementFrame();

                frame.setVisible(true);
            });

    // Monitoring
    btnMonitoring.addActionListener(
            e -> {

                MonitoringFrame frame =
                        new MonitoringFrame();

                frame.setVisible(true);
            });

    // Report
    btnReport.addActionListener(
            e -> {

                ReportFrame frame =
                        new ReportFrame();

                frame.setVisible(true);
            });

    // Pengembalian Asset
    btnPengembalian.addActionListener(
            e -> {

                PengembalianFrame frame =
                        new PengembalianFrame();

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
