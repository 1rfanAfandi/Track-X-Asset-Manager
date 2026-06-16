package ui.karyawan;

import asset.dao.AssetDAO;
import asset.model.Asset;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AssetListFrame extends JFrame {

    private JTable table;

    public AssetListFrame() {

        initializeUI();

        loadData();
    }

    private void initializeUI() {

        setTitle("Daftar Asset");

        setSize(900, 500);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE);

        table =
                new JTable();

        add(
                new JScrollPane(table),
                BorderLayout.CENTER);
    }

    private void loadData() {

        AssetDAO dao =
                new AssetDAO();

        List<Asset> assets =
                dao.selectAll();

        DefaultTableModel model =
                new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("Nama Asset");
        model.addColumn("Status");
        model.addColumn("Lokasi");
        model.addColumn("Jenis");

        for (Asset asset : assets) {

            model.addRow(
                    new Object[] {

                            asset.getIdAsset(),

                            asset.getNama(),

                            asset.getStatus(),

                            asset.getLokasi(),

                            asset.getClass()
                                    .getSimpleName()
                    });
        }

        table.setModel(model);
    }
}