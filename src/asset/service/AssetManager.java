package asset.service;

import asset.dao.AssetDAO;
import asset.model.Asset;

import java.util.ArrayList;
import java.util.List;

public class AssetManager {

    private List<Asset> daftarAsset;
    private AssetDAO assetDAO;

    public AssetManager() {
        this.daftarAsset = new ArrayList<>();
        this.assetDAO = new AssetDAO();
    }

    public void tambahAsset(Asset asset) {

        assetDAO.insert(asset);

        daftarAsset.add(asset);
    }

    public void hapusAsset(int idAsset) {

        assetDAO.delete(idAsset);

        daftarAsset.removeIf(
                asset -> asset.getIdAsset() == idAsset
        );
    }

    public List<Asset> cariAsset(String keyword) {

        List<Asset> hasil = new ArrayList<>();

        List<Asset> semuaAsset =
                assetDAO.selectAll();

        for (Asset asset : semuaAsset) {

            if (asset.getNama()
                    .toLowerCase()
                    .contains(keyword.toLowerCase())) {

                hasil.add(asset);
            }
        }

        return hasil;
    }

    public List<Asset> getAssetTersedia() {

        List<Asset> tersedia =
                new ArrayList<>();

        List<Asset> semuaAsset =
                assetDAO.selectAll();

        for (Asset asset : semuaAsset) {

            if (asset.isAvailable()) {

                tersedia.add(asset);
            }
        }

        return tersedia;
    }

    public boolean pinjamAsset(int idAsset) {

        Asset asset =
                assetDAO.findById(idAsset);

        if (asset == null) {
            return false;
        }

        if (!asset.isAvailable()) {
            return false;
        }

        asset.setStatus("DIPINJAM");

        assetDAO.update(asset);

        return true;
    }

    public boolean kembalikanAsset(int idAsset) {

        Asset asset =
                assetDAO.findById(idAsset);

        if (asset == null) {
            return false;
        }

        asset.setStatus("TERSEDIA");

        assetDAO.update(asset);

        return true;
    }

    public List<Asset> getAllAsset() {

        return assetDAO.selectAll();
    }
}