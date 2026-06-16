package ui.components;

import asset.model.Asset;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class AssetTableModel
        extends AbstractTableModel {

    private final String[] columns = {
            "ID",
            "Nama",
            "Status",
            "Lokasi"
    };

    private List<Asset> assets;

    public AssetTableModel(
            List<Asset> assets) {

        this.assets = assets;
    }

    @Override
    public int getRowCount() {
        return assets.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(
            int column) {

        return columns[column];
    }

    @Override
    public Object getValueAt(
            int rowIndex,
            int columnIndex) {

        Asset asset =
                assets.get(rowIndex);

        switch (columnIndex) {

            case 0:
                return asset.getIdAsset();

            case 1:
                return asset.getNama();

            case 2:
                return asset.getStatus();

            case 3:
                return asset.getLokasi();

            default:
                return "";
        }
    }
}