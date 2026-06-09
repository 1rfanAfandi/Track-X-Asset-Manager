package controller;

import dao.AssetDAO;
import model.*;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/AssetServlet")
public class AssetServlet extends HttpServlet {
    
    // Buat objek AssetDAO untuk mengakses database
    private AssetDAO assetDAO = new AssetDAO();
    
    // ============ METHOD doGet ============
    // Dipanggil saat user buka URL atau klik link
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Ambil parameter "action" dari URL
        String action = request.getParameter("action");
        
        try {
            // KASUS 1: HAPUS ASET
            if ("hapus".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                assetDAO.delete(id);
                response.sendRedirect("AssetServlet");
            }
            // KASUS 2: FORM TAMBAH ASET
            else if ("tambahForm".equals(action)) {
                request.getRequestDispatcher("admin/formAset.jsp").forward(request, response);
            }
            // KASUS 3: FORM EDIT ASET
            else if ("editForm".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                Asset asset = assetDAO.selectById(id);
                request.setAttribute("asset", asset);
                request.getRequestDispatcher("admin/formAset.jsp").forward(request, response);
            }
            // KASUS 4: DEFAULT - LIHAT SEMUA ASET
            else {
                List<Asset> daftarAset = assetDAO.selectAll();
                request.setAttribute("daftarAset", daftarAset);
                request.getRequestDispatcher("admin/dashboard.jsp").forward(request, response);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().print("Error: " + e.getMessage());
        }
    }
    
    // ============ METHOD doPost ============
    // Dipanggil saat user submit form (POST)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Ambil parameter "action" dari form
        String action = request.getParameter("action");
        
        try {
            // KASUS 1: TAMBAH ASET
            if ("tambah".equals(action)) {
                String tipe = request.getParameter("tipe");
                Asset asset = createAssetFromForm(request, tipe);
                assetDAO.insert(asset);
                response.sendRedirect("AssetServlet");
            }
            // KASUS 2: UPDATE ASET
            else if ("update".equals(action)) {
                int id = Integer.parseInt(request.getParameter("idAsset"));
                String tipe = request.getParameter("tipe");
                Asset asset = createAssetFromForm(request, tipe);
                asset.setIdAsset(id);
                assetDAO.update(asset);
                response.sendRedirect("AssetServlet");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().print("Error: " + e.getMessage());
        }
    }
    
    // ============ METHOD BANTUAN ============
    // Method untuk membuat objek Asset dari data form
    private Asset createAssetFromForm(HttpServletRequest request, String tipe) {
        
        // Ambil data dari form (semua aset punya ini)
        String nama = request.getParameter("nama");
        String status = request.getParameter("status");
        String lokasi = request.getParameter("lokasi");
        
        // BEDAKAN berdasarkan tipe aset
        if ("Ruangan".equals(tipe)) {
            int kapasitas = Integer.parseInt(request.getParameter("kapasitas"));
            return new Ruangan(0, nama, status, lokasi, kapasitas);
        }
        else if ("PerangkatElektronik".equals(tipe)) {
            String merk = request.getParameter("merk");
            String model = request.getParameter("model");
            return new PerangkatElektronik(0, nama, status, lokasi, merk, model);
        }
        else if ("Kendaraan".equals(tipe)) {
            String platNomor = request.getParameter("platNomor");
            String jenis = request.getParameter("jenis");
            return new Kendaraan(0, nama, status, lokasi, platNomor, jenis);
        }
        else if ("Mesin".equals(tipe)) {
            double dayaKW = Double.parseDouble(request.getParameter("dayaKW"));
            String tipeMesin = request.getParameter("tipeMesin");
            return new Mesin(0, nama, status, lokasi, dayaKW, tipeMesin);
        }
        
        return null;
    }
}