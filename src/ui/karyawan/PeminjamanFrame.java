package ui.karyawan;

import asset.dao.AssetDAO;
import asset.model.Asset;

import loginauth.model.Karyawan;
import loginauth.model.Pengguna;

import peminjaman.dao.PeminjamanDAO;
import peminjaman.model.Peminjaman;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.List;

public class PeminjamanFrame extends JFrame {

    private JTable table;

    private Pengguna user;

    private AssetDAO assetDAO;

    public PeminjamanFrame(
            Pengguna user) {

        this.user = user;

        assetDAO =
                new AssetDAO();

        initializeUI();

        loadData();
    }

    private void initializeUI() {

        setTitle(
                "Peminjaman Asset");

        setSize(
                800,
                500);

        setLocationRelativeTo(
                null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE);

        table =
                new JTable();

        JScrollPane scroll =
                new JScrollPane(table);

        JButton btnPinjam =
                new JButton(
                        "Pinjam Asset");

        add(
                scroll,
                BorderLayout.CENTER);

        add(
                btnPinjam,
                BorderLayout.SOUTH);

        btnPinjam.addActionListener(
                e -> pinjamAsset());
    }

    private void loadData() {

        List<Asset> assets =
                assetDAO.selectAll();

        String[] cols = {
                "ID",
                "Nama",
                "Status"
        };

        Object[][] data =
                new Object[
                        assets.size()][3];

        for (int i = 0;
             i < assets.size();
             i++) {

            Asset asset =
                    assets.get(i);

            data[i][0] =
                    asset.getIdAsset();

            data[i][1] =
                    asset.getNama();

            data[i][2] =
                    asset.getStatus();
        }

        table.setModel(
                new javax.swing.table
                        .DefaultTableModel(
                        data,
                        cols));
    }

    private void pinjamAsset() {

        int row =
                table.getSelectedRow();

        if (row < 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Pilih asset");

            return;
        }

        try {

            int idAsset =
                    (Integer)
                            table.getValueAt(
                                    row,
                                    0);

            Asset asset =
                    assetDAO.findById(
                            idAsset);

            if (!asset.isAvailable()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Asset sedang dipinjam");

                return;
            }

            Karyawan karyawan =
                    (Karyawan) user;

            Peminjaman peminjaman =
                    new Peminjaman(
                            0,
                            new Date(),
                            asset,
                            karyawan);

            PeminjamanDAO dao =
                    new PeminjamanDAO();

            dao.insert(
                    peminjaman);

            assetDAO.updateStatus(
                    idAsset,
                    "DIPINJAM");

            JOptionPane.showMessageDialog(
                    this,
                    "Peminjaman berhasil");

            loadData();

        } catch (Exception ex) {

            ex.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage());
        }
    }
}