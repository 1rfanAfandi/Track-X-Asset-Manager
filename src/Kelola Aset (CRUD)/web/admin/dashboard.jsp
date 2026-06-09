<%@ page import="java.util.*, model.*" %>
<%
    List<Asset> daftarAset = (List<Asset>) request.getAttribute("daftarAset");
    String filterStatus = request.getParameter("status");
    
    if (daftarAset == null) {
        response.sendRedirect(request.getContextPath() + "/AssetServlet");
        return;
    }
    
    // Filter aset berdasarkan status
    List<Asset> asetFiltered = new ArrayList<>();
    if (filterStatus == null || filterStatus.isEmpty() || "Semua".equals(filterStatus)) {
        asetFiltered = daftarAset;
    } else {
        for (Asset a : daftarAset) {
            if (a.getStatus().equals(filterStatus)) {
                asetFiltered.add(a);
            }
        }
    }
    
    // Hitung statistik
    int total = daftarAset.size();
    int tersedia = 0, dipinjam = 0, rusak = 0, perbaikan = 0;
    for (Asset a : daftarAset) {
        if (a.getStatus().equals("Tersedia")) tersedia++;
        else if (a.getStatus().equals("Dipinjam")) dipinjam++;
        else if (a.getStatus().equals("Rusak")) rusak++;
        else if (a.getStatus().equals("Dalam Perbaikan")) perbaikan++;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Track X - Dashboard Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        :root {
            --primary: #667eea;
            --secondary: #764ba2;
            --success: #10b981;
            --warning: #f59e0b;
            --danger: #ef4444;
            --info: #8b5cf6;
        }
        
        * { margin: 0; padding: 0; box-sizing: border-box; }
        
        body {
            background: linear-gradient(135deg, var(--primary) 0%, var(--secondary) 100%);
            min-height: 100vh;
            padding: 24px;
            font-family: 'Segoe UI', system-ui, sans-serif;
        }
        
        .container {
            background: rgba(255, 255, 255, 0.98);
            border-radius: 32px;
            padding: 32px;
            box-shadow: 0 25px 50px -12px rgba(0,0,0,0.25);
        }
        
        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 32px;
            padding-bottom: 24px;
            border-bottom: 2px solid #f1f5f9;
            flex-wrap: wrap;
            gap: 16px;
        }
        
        .logo-wrapper {
            display: flex;
            align-items: center;
            gap: 16px;
        }
        
        .brand-icon {
            background: linear-gradient(135deg, var(--primary) 0%, var(--secondary) 100%);
            width: 56px;
            height: 56px;
            border-radius: 18px;
            display: flex;
            align-items: center;
            justify-content: center;
            color: white;
            font-size: 28px;
        }
        
        .logo-section h1 {
            color: #1e293b;
            font-weight: 700;
            margin: 0;
            font-size: 26px;
        }
        
        .logo-section .tagline {
            color: var(--secondary);
            font-size: 11px;
            letter-spacing: 1.5px;
            margin-top: 4px;
            text-transform: uppercase;
        }
        
        .btn-add {
            background: linear-gradient(135deg, var(--primary) 0%, var(--secondary) 100%);
            border: none;
            padding: 12px 24px;
            font-weight: 600;
            border-radius: 16px;
            transition: all 0.3s ease;
        }
        
        .btn-add:hover {
            transform: translateY(-2px);
            box-shadow: 0 10px 20px -5px rgba(102, 126, 234, 0.4);
        }
        
        /* Filter Tabs */
        .filter-tabs {
            display: flex;
            gap: 12px;
            margin-bottom: 32px;
            flex-wrap: wrap;
            border-bottom: 1px solid #e2e8f0;
            padding-bottom: 16px;
        }
        
        .filter-btn {
            padding: 8px 24px;
            border-radius: 40px;
            font-weight: 600;
            font-size: 14px;
            transition: all 0.3s ease;
            text-decoration: none;
            display: inline-block;
        }
        
        .filter-btn-all {
            background: #f1f5f9;
            color: #475569;
        }
        
        .filter-btn-all:hover, .filter-btn-all.active {
            background: linear-gradient(135deg, var(--primary) 0%, var(--secondary) 100%);
            color: white;
        }
        
        .filter-btn-tersedia {
            background: #d1fae5;
            color: #065f46;
        }
        
        .filter-btn-tersedia:hover, .filter-btn-tersedia.active {
            background: var(--success);
            color: white;
        }
        
        .filter-btn-dipinjam {
            background: #fed7aa;
            color: #92400e;
        }
        
        .filter-btn-dipinjam:hover, .filter-btn-dipinjam.active {
            background: var(--warning);
            color: white;
        }
        
        .filter-btn-rusak {
            background: #fee2e2;
            color: #991b1b;
        }
        
        .filter-btn-rusak:hover, .filter-btn-rusak.active {
            background: var(--danger);
            color: white;
        }
        
        .filter-btn-perbaikan {
            background: #e9d5ff;
            color: #5b21b6;
        }
        
        .filter-btn-perbaikan:hover, .filter-btn-perbaikan.active {
            background: var(--info);
            color: white;
        }
        
        /* Stat Cards */
        .stat-card {
            background: white;
            border-radius: 20px;
            padding: 20px;
            text-align: center;
            box-shadow: 0 4px 6px -1px rgba(0,0,0,0.05);
            border: 1px solid #f1f5f9;
            transition: all 0.3s ease;
            cursor: pointer;
        }
        
        .stat-card:hover {
            transform: translateY(-4px);
            box-shadow: 0 20px 25px -5px rgba(0,0,0,0.1);
        }
        
        .stat-number {
            font-size: 36px;
            font-weight: 800;
            color: var(--primary);
            line-height: 1.2;
        }
        
        .stat-label {
            color: #64748b;
            font-size: 13px;
            font-weight: 500;
            margin-top: 8px;
        }
        
        /* Table */
        .table {
            border-radius: 20px;
            overflow: hidden;
        }
        
        .table thead th {
            background: linear-gradient(135deg, var(--primary) 0%, var(--secondary) 100%);
            color: white;
            font-weight: 600;
            border: none;
            padding: 16px;
            font-size: 13px;
            text-transform: uppercase;
            letter-spacing: 0.5px;
        }
        
        .table tbody td {
            padding: 14px 16px;
            vertical-align: middle;
            border-bottom: 1px solid #f1f5f9;
        }
        
        .badge {
            padding: 6px 14px;
            border-radius: 30px;
            font-size: 12px;
            font-weight: 600;
        }
        
        .badge-tersedia { background: #d1fae5; color: #065f46; }
        .badge-dipinjam { background: #fed7aa; color: #92400e; }
        .badge-rusak { background: #fee2e2; color: #991b1b; }
        .badge-perbaikan { background: #e9d5ff; color: #5b21b6; }
        
        .btn-sm {
            padding: 6px 14px;
            border-radius: 12px;
            font-size: 12px;
            font-weight: 500;
            transition: all 0.2s ease;
        }
        
        .btn-edit {
            background: var(--warning);
            color: white;
            margin-right: 6px;
            border: none;
        }
        
        .btn-delete {
            background: var(--danger);
            color: white;
            border: none;
        }
        
        .btn-sm:hover {
            transform: scale(1.02);
            filter: brightness(1.05);
        }
        
        .result-info {
            margin-bottom: 20px;
            color: #64748b;
            font-size: 14px;
        }
        
        footer {
            margin-top: 32px;
            text-align: center;
            padding-top: 24px;
            border-top: 1px solid #f1f5f9;
            color: #94a3b8;
            font-size: 12px;
        }
        
        a { text-decoration: none; }
    </style>
</head>
<body>
    <div class="container">
        <!-- Header -->
        <div class="header">
            <div class="logo-wrapper">
                <div class="brand-icon">
                    <i class="fas fa-chart-line"></i>
                </div>
                <div class="logo-section">
                    <h1><i class="fas fa-cubes"></i> TRACK X</h1>
                    <div class="tagline">ASSET MANAGEMENT SYSTEM</div>
                </div>
            </div>
            <a href="<%= request.getContextPath() %>/AssetServlet?action=tambahForm" class="btn btn-add text-white">
                <i class="fas fa-plus-circle me-2"></i>Tambah Aset Baru
            </a>
        </div>
        
        <!-- Statistics Cards (Bisa diklik untuk filter) -->
        <div class="row g-4 mb-4">
            <div class="col-md-3">
                <a href="?status=Semua" class="text-decoration-none">
                    <div class="stat-card">
                        <i class="fas fa-database fa-2x" style="color: var(--primary);"></i>
                        <div class="stat-number mt-2"><%= total %></div>
                        <div class="stat-label">Semua Aset</div>
                    </div>
                </a>
            </div>
            <div class="col-md-3">
                <a href="?status=Tersedia" class="text-decoration-none">
                    <div class="stat-card">
                        <i class="fas fa-check-circle fa-2x" style="color: var(--success);"></i>
                        <div class="stat-number mt-2"><%= tersedia %></div>
                        <div class="stat-label">Tersedia</div>
                    </div>
                </a>
            </div>
            <div class="col-md-3">
                <a href="?status=Dipinjam" class="text-decoration-none">
                    <div class="stat-card">
                        <i class="fas fa-clock fa-2x" style="color: var(--warning);"></i>
                        <div class="stat-number mt-2"><%= dipinjam %></div>
                        <div class="stat-label">Dipinjam</div>
                    </div>
                </a>
            </div>
            <div class="col-md-3">
                <a href="?status=Rusak" class="text-decoration-none">
                    <div class="stat-card">
                        <i class="fas fa-tools fa-2x" style="color: var(--danger);"></i>
                        <div class="stat-number mt-2"><%= rusak + perbaikan %></div>
                        <div class="stat-label">Rusak / Perbaikan</div>
                    </div>
                </a>
            </div>
        </div>
        
        <!-- Filter Tabs -->
        <div class="filter-tabs">
            <a href="?status=Semua" class="filter-btn filter-btn-all <%= (filterStatus == null || filterStatus.isEmpty() || "Semua".equals(filterStatus)) ? "active" : "" %>">
                <i class="fas fa-list me-1"></i> Semua
            </a>
            <a href="?status=Tersedia" class="filter-btn filter-btn-tersedia <%= "Tersedia".equals(filterStatus) ? "active" : "" %>">
                <i class="fas fa-check-circle me-1"></i> Tersedia (<%= tersedia %>)
            </a>
            <a href="?status=Dipinjam" class="filter-btn filter-btn-dipinjam <%= "Dipinjam".equals(filterStatus) ? "active" : "" %>">
                <i class="fas fa-clock me-1"></i> Dipinjam (<%= dipinjam %>)
            </a>
            <a href="?status=Rusak" class="filter-btn filter-btn-rusak <%= "Rusak".equals(filterStatus) ? "active" : "" %>">
                <i class="fas fa-exclamation-triangle me-1"></i> Rusak (<%= rusak %>)
            </a>
            <a href="?status=Dalam Perbaikan" class="filter-btn filter-btn-perbaikan <%= "Dalam Perbaikan".equals(filterStatus) ? "active" : "" %>">
                <i class="fas fa-wrench me-1"></i> Perbaikan (<%= perbaikan %>)
            </a>
        </div>
        
        <!-- Result Info -->
        <div class="result-info">
            <i class="fas fa-chart-simple me-1"></i> Menampilkan <strong><%= asetFiltered.size() %></strong> dari <strong><%= total %></strong> aset
            <% if(filterStatus != null && !filterStatus.isEmpty() && !"Semua".equals(filterStatus)) { %>
                <span class="ms-2">
                    <a href="?status=Semua" class="text-decoration-none small">
                        <i class="fas fa-times-circle"></i> Hapus filter
                    </a>
                </span>
            <% } %>
        </div>
        
        <!-- Asset Table -->
        <div class="table-responsive">
            <table class="table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Tipe</th>
                        <th>Nama Aset</th>
                        <th>Status</th>
                        <th>Lokasi</th>
                        <th>Detail</th>
                        <th>Aksi</th>
                    </tr>
                </thead>
                <tbody>
                    <% if(asetFiltered.isEmpty()) { %>
                        <tr>
                            <td colspan="7" class="text-center text-muted py-4">
                                <i class="fas fa-folder-open fa-2x mb-2 d-block"></i>
                                Tidak ada aset dengan status ini.
                            </td>
                        </tr>
                    <% } else { %>
                        <% for(Asset a : asetFiltered) { 
                            String statusClass = "";
                            if(a.getStatus().equals("Tersedia")) statusClass = "badge-tersedia";
                            else if(a.getStatus().equals("Dipinjam")) statusClass = "badge-dipinjam";
                            else if(a.getStatus().equals("Rusak")) statusClass = "badge-rusak";
                            else statusClass = "badge-perbaikan";
                        %>
                        <tr>
                            <td><%= a.getIdAsset() %></td>
                            <td><span class="badge bg-secondary"><%= a.getClass().getSimpleName() %></span></td>
                            <td><strong><%= a.getNama() %></strong></td>
                            <td><span class="badge <%= statusClass %>"><%= a.getStatus() %></span></td>
                            <td><i class="fas fa-map-marker-alt me-1"></i> <%= a.getLokasi() %></td>
                            <td><small><%= a.getInfo() %></small></td>
                            <td>
                                <a href="<%= request.getContextPath() %>/AssetServlet?action=editForm&id=<%= a.getIdAsset() %>" 
                                   class="btn btn-edit btn-sm">
                                    <i class="fas fa-edit"></i> Edit
                                </a>
                                <a href="<%= request.getContextPath() %>/AssetServlet?action=hapus&id=<%= a.getIdAsset() %>" 
                                   class="btn btn-delete btn-sm" 
                                   onclick="return confirm('Yakin hapus <%= a.getNama() %>?')">
                                    <i class="fas fa-trash"></i> Hapus
                                </a>
                            </td>
                        </tr>
                        <% } %>
                    <% } %>
                </tbody>
            </table>
        </div>
        
        <footer>
            <i class="fas fa-chart-line me-1"></i> TRACK X | Asset Management System
        </footer>
    </div>
</body>
</html>