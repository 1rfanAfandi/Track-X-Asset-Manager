package ui.admin;

import asset.dao.AssetDAO;
import asset.model.Ruangan;

import javax.swing.*;
import java.awt.*;

public class AssetFormFrame extends JFrame {

    private JTextField txtNama;
    private JTextField txtLokasi;
    private JTextField txtKapasitas;

    private JButton btnSimpan;

    public AssetFormFrame() {

        initializeUI();
    }

    private void initializeUI() {

        setTitle("Tambah Asset");

        setSize(400,300);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE);

        JPanel panel =
                new JPanel(
                        new GridLayout(
                                4,
                                2,
                                10,
                                10));

        panel.add(
                new JLabel("Nama"));

        txtNama =
                new JTextField();

        panel.add(txtNama);

        panel.add(
                new JLabel("Lokasi"));

        txtLokasi =
                new JTextField();

        panel.add(txtLokasi);

        panel.add(
                new JLabel("Kapasitas"));

        txtKapasitas =
                new JTextField();

        panel.add(txtKapasitas);

        btnSimpan =
                new JButton(
                        "Simpan");

        panel.add(
                new JLabel());

        panel.add(btnSimpan);

        add(panel);

        btnSimpan.addActionListener(
                e -> simpanAsset());
    }

    private void simpanAsset() {

        try {

            String nama =
                    txtNama.getText();

            String lokasi =
                    txtLokasi.getText();

            int kapasitas =
                    Integer.parseInt(
                            txtKapasitas.getText());

            Ruangan ruangan =
                    new Ruangan(
                            0,
                            nama,
                            "TERSEDIA",
                            lokasi,
                            kapasitas);

            AssetDAO dao =
                    new AssetDAO();

            dao.insert(ruangan);

            JOptionPane.showMessageDialog(
                    this,
                    "Asset berhasil disimpan");

            dispose();

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage());
        }
    }
}