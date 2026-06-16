package report.service;

import asset.dao.AssetDAO;
import asset.model.Asset;

import java.io.FileWriter;
import java.util.List;

public class ReportGenerator {

    public void exportAssetCSV(
            String filePath) {

        try {

            AssetDAO dao =
                    new AssetDAO();

            List<Asset> assets =
                    dao.selectAll();

            FileWriter writer =
                    new FileWriter(
                            filePath);

            writer.write(
                    "ID,Nama,Status,Lokasi\n");

            for (Asset asset : assets) {

                writer.write(

                        asset.getIdAsset()
                        + ","

                        + asset.getNama()
                        + ","

                        + asset.getStatus()
                        + ","

                        + asset.getLokasi()

                        + "\n");
            }

            writer.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}