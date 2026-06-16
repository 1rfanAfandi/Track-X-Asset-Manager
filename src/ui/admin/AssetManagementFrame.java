package ui.admin;

import asset.dao.AssetDAO;
import asset.model.Asset;
import ui.components.AssetTableModel;
import ui.admin.AssetFormFrame;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AssetManagementFrame
        extends JFrame {

    private JTable table;

    private AssetDAO assetDAO;

    public AssetManagementFrame() {

        assetDAO =
                new AssetDAO();

        initializeUI();

        loadData();
    }

    private void initializeUI() {

        setTitle(
                "Manajemen Asset");

        setSize(
                900,
                500);

        setLocationRelativeTo(
                null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE);

        table =
                new JTable();

        JScrollPane scrollPane =
                new JScrollPane(table);

        JButton btnRefresh =
                new JButton("Refresh");

        JButton btnTambah =
                new JButton("Tambah");

        JButton btnHapus =
                new JButton("Hapus");

        JPanel panelButton =
                new JPanel();

        panelButton.add(btnTambah);
        panelButton.add(btnHapus);
        panelButton.add(btnRefresh);

        add(
                scrollPane,
                BorderLayout.CENTER);

        add(
                panelButton,
                BorderLayout.SOUTH);

        btnRefresh.addActionListener(
                e -> loadData());

        btnTambah.addActionListener(
                e -> tambahAsset());

        btnHapus.addActionListener(
                e -> hapusAsset());
    }

    private void loadData() {

        List<Asset> assets =
                assetDAO.selectAll();

        table.setModel(
                new AssetTableModel(
                        assets));
    }

    private void tambahAsset() {

        AssetFormFrame form =
                new AssetFormFrame();

        form.setVisible(true);
    }

    private void hapusAsset() {

        int row =
                table.getSelectedRow();

        if (row < 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Pilih asset terlebih dahulu");

            return;
        }

        int idAsset =
                (Integer) table.getValueAt(
                        row,
                        0);

        assetDAO.delete(idAsset);

        loadData();

        JOptionPane.showMessageDialog(
                this,
                "Asset berhasil dihapus");
    }
}