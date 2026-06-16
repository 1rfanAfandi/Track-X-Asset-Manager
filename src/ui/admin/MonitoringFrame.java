package ui.admin;

import asset.model.Asset;
import monitoring.service.MonitoringService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Objects;

public class MonitoringFrame extends JFrame {

private JTable table;

private JLabel lblTotal;

private JLabel lblTersedia;

private JLabel lblDipinjam;

private JComboBox<String> cmbFilter;

private MonitoringService service;

public MonitoringFrame() {

    service = new MonitoringService();

    initializeUI();

    loadData();
}

private void initializeUI() {

    setTitle("Monitoring Asset");

    setSize(900, 500);

    setLocationRelativeTo(null);

    setDefaultCloseOperation(
            JFrame.DISPOSE_ON_CLOSE);

    setLayout(
            new BorderLayout(10, 10));

    JPanel topPanel =
            new JPanel(
                    new GridLayout(
                            2,
                            2,
                            10,
                            10));

    lblTotal =
            new JLabel();

    lblTersedia =
            new JLabel();

    lblDipinjam =
            new JLabel();

    cmbFilter =
            new JComboBox<>(
                    new String[] {
                            "SEMUA",
                            "TERSEDIA",
                            "DIPINJAM"
                    });

    topPanel.add(lblTotal);
    topPanel.add(lblTersedia);
    topPanel.add(lblDipinjam);
    topPanel.add(cmbFilter);

    table =
            new JTable();

    table.setFillsViewportHeight(true);

    add(
            topPanel,
            BorderLayout.NORTH);

    add(
            new JScrollPane(table),
            BorderLayout.CENTER);

    cmbFilter.addActionListener(
            e -> loadData());
}

private void loadData() {

    lblTotal.setText(
            "Total Asset : "
                    + service.getTotalAsset());

    lblTersedia.setText(
            "Asset Tersedia : "
                    + service.getTotalTersedia());

    lblDipinjam.setText(
            "Asset Dipinjam : "
                    + service.getTotalDipinjam());

    String filter =
            Objects.toString(
                    cmbFilter.getSelectedItem(),
                    "SEMUA");

    List<Asset> assets;

    if (filter.equals("SEMUA")) {

        assets =
                service.getStatusSemuaAset();

    } else {

        assets =
                service.filterByStatus(
                        filter);
    }

    DefaultTableModel model =
            new DefaultTableModel();

    model.addColumn("ID");
    model.addColumn("Nama Asset");
    model.addColumn("Status");
    model.addColumn("Lokasi");

    for (Asset asset : assets) {

        model.addRow(
                new Object[] {

                        asset.getIdAsset(),

                        asset.getNama(),

                        asset.getStatus(),

                        asset.getLokasi()
                });
    }

    table.setModel(model);
}

}
