<%@ page import="model.*" %>
<%
    Asset asset = (Asset) request.getAttribute("asset");
    boolean isEdit = (asset != null);
    String tipe = isEdit ? asset.getClass().getSimpleName() : "";
%>
<!DOCTYPE html>
<html>
<head>
    <title>Track X - <%= isEdit ? "Edit Aset" : "Tambah Aset" %></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        :root {
            --primary: #667eea;
            --secondary: #764ba2;
            --success: #10b981;
            --warning: #f59e0b;
            --danger: #ef4444;
            --gray-50: #f8fafc;
            --gray-200: #e2e8f0;
            --gray-700: #334155;
        }
        
        * { margin: 0; padding: 0; box-sizing: border-box; }
        
        body {
            background: linear-gradient(135deg, var(--primary) 0%, var(--secondary) 100%);
            min-height: 100vh;
            padding: 32px;
            font-family: 'Segoe UI', system-ui, sans-serif;
        }
        
        .container { max-width: 800px; margin: 0 auto; }
        
        .card {
            background: white;
            border-radius: 32px;
            box-shadow: 0 25px 50px -12px rgba(0,0,0,0.25);
            overflow: hidden;
        }
        
        .card-header {
            background: linear-gradient(135deg, var(--primary) 0%, var(--secondary) 100%);
            padding: 28px 32px;
            color: white;
        }
        
        .brand-icon {
            background: rgba(255,255,255,0.2);
            width: 52px;
            height: 52px;
            border-radius: 16px;
            display: inline-flex;
            align-items: center;
            justify-content: center;
            font-size: 26px;
            margin-right: 16px;
        }
        
        .card-header h3 {
            margin: 0;
            font-weight: 700;
            font-size: 24px;
        }
        
        .card-header small {
            opacity: 0.8;
            font-size: 12px;
            letter-spacing: 1px;
        }
        
        .card-body { padding: 32px; }
        
        .form-group { margin-bottom: 24px; }
        
        .form-label {
            font-weight: 600;
            color: var(--gray-700);
            margin-bottom: 8px;
            font-size: 13px;
            text-transform: uppercase;
            letter-spacing: 0.5px;
            display: block;
        }
        
        .form-label i {
            margin-right: 8px;
            color: var(--primary);
            width: 20px;
        }
        
        .form-control, .form-select {
            width: 100%;
            padding: 12px 16px;
            border: 2px solid var(--gray-200);
            border-radius: 16px;
            font-size: 14px;
            transition: all 0.3s ease;
            background: var(--gray-50);
        }
        
        .form-control:focus, .form-select:focus {
            outline: none;
            border-color: var(--primary);
            box-shadow: 0 0 0 4px rgba(102, 126, 234, 0.1);
            background: white;
        }
        
        .dynamic-fields {
            background: var(--gray-50);
            border-radius: 20px;
            padding: 20px;
            margin-top: 8px;
            border: 1px solid var(--gray-200);
        }
        
        .dynamic-fields h6 {
            font-size: 13px;
            font-weight: 700;
            text-transform: uppercase;
            letter-spacing: 1px;
            color: var(--primary);
            margin-bottom: 20px;
            padding-bottom: 10px;
            border-bottom: 1px solid var(--gray-200);
        }
        
        .btn-submit {
            background: linear-gradient(135deg, var(--primary) 0%, var(--secondary) 100%);
            border: none;
            padding: 14px 28px;
            font-weight: 600;
            font-size: 15px;
            border-radius: 40px;
            transition: all 0.3s ease;
            width: 100%;
        }
        
        .btn-submit:hover {
            transform: translateY(-2px);
            box-shadow: 0 10px 20px -5px rgba(102, 126, 234, 0.4);
        }
        
        .btn-secondary {
            background: white;
            border: 2px solid var(--gray-200);
            color: #64748b;
            padding: 12px 24px;
            font-weight: 600;
            border-radius: 40px;
            transition: all 0.2s ease;
            text-decoration: none;
            display: inline-block;
            text-align: center;
        }
        
        .btn-secondary:hover {
            border-color: var(--primary);
            color: var(--primary);
        }
        
        hr { margin: 24px 0; border: none; border-top: 1px solid var(--gray-200); }
        .required::after { content: "*"; color: var(--danger); margin-left: 4px; }
        
        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(10px); }
            to { opacity: 1; transform: translateY(0); }
        }
        
        .dynamic-fields { animation: fadeIn 0.3s ease; }
    </style>
    <script>
        function showFields() {
            var tipe = document.getElementById("tipe").value;
            document.getElementById("ruanganFields").style.display = "none";
            document.getElementById("elektronikFields").style.display = "none";
            document.getElementById("kendaraanFields").style.display = "none";
            document.getElementById("mesinFields").style.display = "none";
            
            if (tipe === "Ruangan") {
                document.getElementById("ruanganFields").style.display = "block";
            } else if (tipe === "PerangkatElektronik") {
                document.getElementById("elektronikFields").style.display = "block";
            } else if (tipe === "Kendaraan") {
                document.getElementById("kendaraanFields").style.display = "block";
            } else if (tipe === "Mesin") {
                document.getElementById("mesinFields").style.display = "block";
            }
        }
        
        document.addEventListener('DOMContentLoaded', function() { showFields(); });
    </script>
</head>
<body>
    <div class="container">
        <div class="card">
            <div class="card-header">
                <div class="d-flex align-items-center">
                    <div class="brand-icon"><i class="fas fa-chart-line"></i></div>
                    <div>
                        <h3><i class="fas fa-<%= isEdit ? "edit" : "plus-circle" %> me-2"></i><%= isEdit ? "Edit Aset" : "Tambah Aset Baru" %></h3>
                        <small>TRACK X ASSET MANAGEMENT SYSTEM</small>
                    </div>
                </div>
            </div>
            
            <div class="card-body">
                <form action="<%= request.getContextPath() %>/AssetServlet" method="post">
                    <input type="hidden" name="action" value="<%= isEdit ? "update" : "tambah" %>">
                    <% if(isEdit) { %>
                        <input type="hidden" name="idAsset" value="<%= asset.getIdAsset() %>">
                    <% } %>
                    
                    <!-- Tipe Aset - Tanpa Emoji -->
                    <div class="form-group">
                        <label class="form-label required"><i class="fas fa-tag"></i> Tipe Aset</label>
                        <select name="tipe" id="tipe" class="form-select" <%= isEdit ? "disabled" : "" %> onchange="showFields()" required>
                            <option value="">-- Pilih Tipe Aset --</option>
                            <option value="Ruangan" <%= "Ruangan".equals(tipe) ? "selected" : "" %>>Ruangan</option>
                            <option value="PerangkatElektronik" <%= "PerangkatElektronik".equals(tipe) ? "selected" : "" %>>Perangkat Elektronik</option>
                            <option value="Kendaraan" <%= "Kendaraan".equals(tipe) ? "selected" : "" %>>Kendaraan</option>
                            <option value="Mesin" <%= "Mesin".equals(tipe) ? "selected" : "" %>>Mesin</option>
                        </select>
                        <% if(isEdit) { %>
                            <input type="hidden" name="tipe" value="<%= tipe %>">
                        <% } %>
                    </div>
                    
                    <!-- Nama Aset -->
                    <div class="form-group">
                        <label class="form-label required"><i class="fas fa-box"></i> Nama Aset</label>
                        <input type="text" name="nama" class="form-control" 
                               value="<%= isEdit ? asset.getNama() : "" %>" 
                               placeholder="Contoh: Ruang Rapat Utama, Proyektor Epson, Toyota Avanza"
                               required>
                    </div>
                    
                    <div class="row">
                        <!-- Status -->
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="form-label required"><i class="fas fa-circle"></i> Status</label>
                                <select name="status" class="form-select" required>
                                    <option value="Tersedia" <%= isEdit && "Tersedia".equals(asset.getStatus()) ? "selected" : "" %>>Tersedia</option>
                                    <option value="Dipinjam" <%= isEdit && "Dipinjam".equals(asset.getStatus()) ? "selected" : "" %>>Dipinjam</option>
                                    <option value="Rusak" <%= isEdit && "Rusak".equals(asset.getStatus()) ? "selected" : "" %>>Rusak</option>
                                    <option value="Dalam Perbaikan" <%= isEdit && "Dalam Perbaikan".equals(asset.getStatus()) ? "selected" : "" %>>Dalam Perbaikan</option>
                                </select>
                            </div>
                        </div>
                        
                        <!-- Lokasi -->
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="form-label required"><i class="fas fa-location-dot"></i> Lokasi</label>
                                <input type="text" name="lokasi" class="form-control" 
                                       value="<%= isEdit ? asset.getLokasi() : "" %>" 
                                       placeholder="Contoh: Lantai 3, Gudang B, Lab 1"
                                       required>
                            </div>
                        </div>
                    </div>
                    
                    <!-- Field Ruangan -->
                    <div id="ruanganFields" class="dynamic-fields" style="display: none;">
                        <h6><i class="fas fa-building me-2"></i>Spesifikasi Ruangan</h6>
                        <div class="form-group">
                            <label class="form-label"><i class="fas fa-users"></i> Kapasitas (orang)</label>
                            <input type="number" name="kapasitas" class="form-control" 
                                   value="<%= isEdit && asset instanceof Ruangan ? ((Ruangan)asset).getKapasitas() : "" %>"
                                   placeholder="Jumlah maksimal orang">
                        </div>
                    </div>
                    
                    <!-- Field Perangkat Elektronik -->
                    <div id="elektronikFields" class="dynamic-fields" style="display: none;">
                        <h6><i class="fas fa-microchip me-2"></i>Spesifikasi Perangkat</h6>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="form-label"><i class="fas fa-trademark"></i> Merk</label>
                                    <input type="text" name="merk" class="form-control" 
                                           value="<%= isEdit && asset instanceof PerangkatElektronik ? ((PerangkatElektronik)asset).getMerk() : "" %>"
                                           placeholder="Contoh: Apple, Epson, Sony">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="form-label"><i class="fas fa-barcode"></i> Model</label>
                                    <input type="text" name="model" class="form-control" 
                                           value="<%= isEdit && asset instanceof PerangkatElektronik ? ((PerangkatElektronik)asset).getModel() : "" %>"
                                           placeholder="Contoh: MacBook Pro M3, XB-100">
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <!-- Field Kendaraan -->
                    <div id="kendaraanFields" class="dynamic-fields" style="display: none;">
                        <h6><i class="fas fa-car me-2"></i>Spesifikasi Kendaraan</h6>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="form-label"><i class="fas fa-id-card"></i> Plat Nomor</label>
                                    <input type="text" name="platNomor" class="form-control" 
                                           value="<%= isEdit && asset instanceof Kendaraan ? ((Kendaraan)asset).getPlatNomor() : "" %>"
                                           placeholder="Contoh: B 1234 CD">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="form-label"><i class="fas fa-car-side"></i> Jenis Kendaraan</label>
                                    <select name="jenis" class="form-select">
                                        <option value="Mobil" <%= isEdit && asset instanceof Kendaraan && "Mobil".equals(((Kendaraan)asset).getJenis()) ? "selected" : "" %>>Mobil</option>
                                        <option value="Motor" <%= isEdit && asset instanceof Kendaraan && "Motor".equals(((Kendaraan)asset).getJenis()) ? "selected" : "" %>>Motor</option>
                                        <option value="Truk" <%= isEdit && asset instanceof Kendaraan && "Truk".equals(((Kendaraan)asset).getJenis()) ? "selected" : "" %>>Truk</option>
                                        <option value="Bus" <%= isEdit && asset instanceof Kendaraan && "Bus".equals(((Kendaraan)asset).getJenis()) ? "selected" : "" %>>Bus</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <!-- Field Mesin -->
                    <div id="mesinFields" class="dynamic-fields" style="display: none;">
                        <h6><i class="fas fa-industry me-2"></i>Spesifikasi Mesin</h6>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="form-label"><i class="fas fa-bolt"></i> Daya (kW)</label>
                                    <input type="number" step="0.1" name="dayaKW" class="form-control" 
                                           value="<%= isEdit && asset instanceof Mesin ? ((Mesin)asset).getDayaKW() : "" %>"
                                           placeholder="Contoh: 5.5, 10.0">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="form-label"><i class="fas fa-cogs"></i> Tipe Mesin</label>
                                    <input type="text" name="tipeMesin" class="form-control" 
                                           value="<%= isEdit && asset instanceof Mesin ? ((Mesin)asset).getTipeMesin() : "" %>"
                                           placeholder="Contoh: Diesel, Electric, Hydraulic">
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <hr>
                    
                    <div class="d-grid gap-3">
                        <button type="submit" class="btn-submit text-white">
                            <i class="fas fa-<%= isEdit ? "save" : "check-circle" %> me-2"></i>
                            <%= isEdit ? "Simpan Perubahan" : "Tambah Aset" %>
                        </button>
                        <a href="<%= request.getContextPath() %>/AssetServlet" class="btn-secondary">
                            <i class="fas fa-arrow-left me-2"></i> Kembali ke Dashboard
                        </a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>