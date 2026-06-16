package ui.karyawan;

import asset.dao.AssetDAO;
import asset.model.Asset;

import javax.swing.*;
import java.util.List;

public class AssetListFrame extends JFrame {

    public AssetListFrame() {

        setTitle("Daftar Asset");

        setSize(700,500);

        setLocationRelativeTo(null);

        JTextArea area =
                new JTextArea();

        JScrollPane scroll =
                new JScrollPane(area);

        add(scroll);

        AssetDAO dao =
                new AssetDAO();

        List<Asset> assets =
                dao.selectAll();

        for (Asset asset : assets) {

            area.append(
                    asset.getInfo()
                            + "\n\n");
        }
    }
}