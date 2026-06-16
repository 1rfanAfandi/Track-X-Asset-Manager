package ui.admin;

import report.service.ReportGenerator;

import javax.swing.*;
import java.awt.*;

public class ReportFrame extends JFrame {

    public ReportFrame() {

        initializeUI();
    }

    private void initializeUI() {

        setTitle(
                "Report Generator");

        setSize(
                400,
                200);

        setLocationRelativeTo(
                null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE);

        JButton btnExport =
                new JButton(
                        "Export Asset CSV");

        add(
                btnExport,
                BorderLayout.CENTER);

        btnExport.addActionListener(
                e -> exportCSV());
    }

    private void exportCSV() {

        JFileChooser chooser =
                new JFileChooser();

        int result =
                chooser.showSaveDialog(
                        this);

        if (result ==
                JFileChooser.APPROVE_OPTION) {

            String path =
                    chooser
                            .getSelectedFile()
                            .getAbsolutePath();

            ReportGenerator report =
                    new ReportGenerator();

            report.exportAssetCSV(
                    path + ".csv");

            JOptionPane.showMessageDialog(
                    this,
                    "Report berhasil dibuat");
        }
    }
}