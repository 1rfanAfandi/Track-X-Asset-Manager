package ui.karyawan;

import peminjaman.dao.PeminjamanDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.sql.ResultSet;

public class RiwayatFrame extends JFrame {

    private JTable table;

    public RiwayatFrame() {

        initializeUI();

        loadData();
    }

    private void initializeUI() {

        setTitle(
                "Riwayat Peminjaman");

        setSize(
                800,
                500);

        setLocationRelativeTo(
                null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE);

        table =
                new JTable();

        add(
                new JScrollPane(table));
    }

    private void loadData() {

        try {

            DefaultTableModel model =
                    new DefaultTableModel();

            model.addColumn(
                    "ID");

            model.addColumn(
                    "Nama Asset");

            model.addColumn(
                    "Tanggal Pinjam");

            model.addColumn(
                    "Status");

            PeminjamanDAO dao =
                    new PeminjamanDAO();

            ResultSet rs =
                    dao.getAllRiwayat();

            while (rs.next()) {

                model.addRow(
                        new Object[] {

                                rs.getInt(
                                        "id_peminjaman"),

                                rs.getString(
                                        "nama"),

                                rs.getTimestamp(
                                        "tanggal_pinjam"),

                                rs.getString(
                                        "status_pinjam")
                        });
            }

            table.setModel(model);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}