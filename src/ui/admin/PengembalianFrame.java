package ui.admin;

import asset.dao.AssetDAO;
import peminjaman.dao.PeminjamanDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.sql.ResultSet;

public class PengembalianFrame extends JFrame {

    private JTable table;

    public PengembalianFrame() {

        initializeUI();

        loadData();
    }

    private void initializeUI() {

        setTitle("Pengembalian Asset");

        setSize(800,500);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE);

        table = new JTable();

        JButton btnKembalikan =
                new JButton(
                        "Kembalikan");

        add(
                new JScrollPane(table));

        add(
                btnKembalikan,
                java.awt.BorderLayout.SOUTH);

        btnKembalikan.addActionListener(
                e -> kembalikan());
    }

    private void loadData() {

        try {

            DefaultTableModel model =
                    new DefaultTableModel();

            model.addColumn(
                    "ID Peminjaman");

            model.addColumn(
                    "ID Asset");

            model.addColumn(
                    "Nama Asset");

            model.addColumn(
                    "Tanggal Pinjam");

            PeminjamanDAO dao =
                    new PeminjamanDAO();

            ResultSet rs =
                    dao.getAssetDipinjam();

            while (rs.next()) {

                model.addRow(
                        new Object[] {

                                rs.getInt(
                                        "id_peminjaman"),

                                rs.getInt(
                                        "id_asset"),

                                rs.getString(
                                        "nama"),

                                rs.getTimestamp(
                                        "tanggal_pinjam")
                        });
            }

            table.setModel(model);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    private void kembalikan() {

        int row =
                table.getSelectedRow();

        if (row < 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Pilih data");

            return;
        }

        try {

            int idPeminjaman =
                    (Integer)
                            table.getValueAt(
                                    row,
                                    0);

            int idAsset =
                    (Integer)
                            table.getValueAt(
                                    row,
                                    1);

            PeminjamanDAO dao =
                    new PeminjamanDAO();

            dao.prosesPengembalian(
                    idPeminjaman);

            AssetDAO assetDAO =
                    new AssetDAO();

            assetDAO.updateStatus(
                    idAsset,
                    "TERSEDIA");

            JOptionPane.showMessageDialog(
                    this,
                    "Asset berhasil dikembalikan");

            loadData();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}