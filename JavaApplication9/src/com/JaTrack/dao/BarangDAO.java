package com.JaTrack.dao;

import com.JaTrack.model.Barang;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class BarangDAO {
    
    private Connection conn;
    
    public BarangDAO(Connection conn) {
        this.conn = conn;
    }
    
    // CREATE - Tambah barang
    public boolean insert(Barang barang) {
        String sql = "INSERT INTO barang (kode_barang, nama_barang, kategori, stok, harga, satuan) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, barang.getKodeBarang());
            ps.setString(2, barang.getNamaBarang());
            ps.setString(3, barang.getKategori());
            ps.setInt(4, barang.getStok());
            ps.setDouble(5, barang.getHarga());
            ps.setString(6, barang.getSatuan());
            
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error Insert: " + e.getMessage());
            return false;
        }
    }
    
    // READ - Ambil semua barang
    public List<Barang> getAll() {
        List<Barang> list = new ArrayList<>();
        String sql = "SELECT * FROM barang ORDER BY id_barang DESC";
        
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Barang barang = new Barang();
                barang.setIdBarang(rs.getInt("id_barang"));
                barang.setKodeBarang(rs.getString("kode_barang"));
                barang.setNamaBarang(rs.getString("nama_barang"));
                barang.setKategori(rs.getString("kategori"));
                barang.setStok(rs.getInt("stok"));
                barang.setHarga(rs.getDouble("harga"));
                barang.setSatuan(rs.getString("satuan"));
                list.add(barang);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error Get All: " + e.getMessage());
        }
        return list;
    }
    
    // UPDATE - Update barang
    public boolean update(Barang barang) {
        String sql = "UPDATE barang SET kode_barang=?, nama_barang=?, kategori=?, stok=?, harga=?, satuan=? WHERE id_barang=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, barang.getKodeBarang());
            ps.setString(2, barang.getNamaBarang());
            ps.setString(3, barang.getKategori());
            ps.setInt(4, barang.getStok());
            ps.setDouble(5, barang.getHarga());
            ps.setString(6, barang.getSatuan());
            ps.setInt(7, barang.getIdBarang());
            
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error Update: " + e.getMessage());
            return false;
        }
    }
    
    // DELETE - Hapus barang
    public boolean delete(int idBarang) {
        String sql = "DELETE FROM barang WHERE id_barang=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idBarang);
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error Delete: " + e.getMessage());
            return false;
        }
    }
    
    // SEARCH - Cari barang
    public List<Barang> search(String keyword) {
        List<Barang> list = new ArrayList<>();
        String sql = "SELECT * FROM barang WHERE kode_barang LIKE ? OR nama_barang LIKE ? OR kategori LIKE ?";
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            String key = "%" + keyword + "%";
            ps.setString(1, key);
            ps.setString(2, key);
            ps.setString(3, key);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Barang barang = new Barang();
                barang.setIdBarang(rs.getInt("id_barang"));
                barang.setKodeBarang(rs.getString("kode_barang"));
                barang.setNamaBarang(rs.getString("nama_barang"));
                barang.setKategori(rs.getString("kategori"));
                barang.setStok(rs.getInt("stok"));
                barang.setHarga(rs.getDouble("harga"));
                barang.setSatuan(rs.getString("satuan"));
                list.add(barang);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error Search: " + e.getMessage());
        }
        return list;
    }
}