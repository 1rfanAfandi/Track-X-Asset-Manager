package monitoring.service;

import asset.dao.AssetDAO;
import asset.model.Asset;

import java.util.List;

public class MonitoringService {

private AssetDAO assetDAO;

public MonitoringService() {

    assetDAO = new AssetDAO();
}

public List<Asset> getStatusSemuaAset() {

    return assetDAO.selectAll();
}

public List<Asset> filterByStatus(
        String status) {

    return assetDAO.selectByStatus(status);
}

public List<Asset> getAssetTersedia() {

    return assetDAO.selectByStatus(
            "TERSEDIA");
}

public List<Asset> getAssetDipinjam() {

    return assetDAO.selectByStatus(
            "DIPINJAM");
}

// ==========================
// Tambahan untuk Monitoring
// ==========================

public int getTotalAsset() {

    return assetDAO.countAll();
}

public int getTotalTersedia() {

    return assetDAO.countByStatus(
            "TERSEDIA");
}

public int getTotalDipinjam() {

    return assetDAO.countByStatus(
            "DIPINJAM");
}

}
