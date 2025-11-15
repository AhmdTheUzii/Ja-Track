package com.JaTrack.form;

import com.JaTrack.dao.BarangDAO;
import com.JaTrack.model.Barang;
import com.JaTrack.main.Form;
import com.formdev.flatlaf.FlatClientProperties;
import java.sql.Connection;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;

public class FormBarang extends Form {
    
    // Components
    private JTextField txtKode, txtNama, txtKategori, txtStok, txtHarga, txtSatuan, txtSearch;
    private JButton btnSave, btnUpdate, btnDelete, btnClear, btnSearch;
    private JTable table;
    private DefaultTableModel tableModel;
    
    private BarangDAO dao;
    private int selectedId = 0;
    
    public FormBarang() {
        init();
    }
    
    private void init() {
        setLayout(new MigLayout("fill, insets 10", "[grow]", "[]10[grow]"));
        
        // ✅ Panel Form Input
        JPanel panelForm = createFormPanel();
        add(panelForm, "wrap");
        
        // ✅ Panel Table
        JPanel panelTable = createTablePanel();
        add(panelTable, "grow");
        
        // ✅ Load data
        loadData();
    }
    
    private JPanel createFormPanel() {
        JPanel panel = new JPanel(new MigLayout("fill", "[]10[grow][]10[grow]", ""));
        panel.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        
        // Input fields
        txtKode = new JTextField();
        txtNama = new JTextField();
        txtKategori = new JTextField();
        txtStok = new JTextField();
        txtHarga = new JTextField();
        txtSatuan = new JTextField();
        
        // Buttons
        btnSave = new JButton("Simpan");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Hapus");
        btnClear = new JButton("Batal");
        
        // Add to panel
        panel.add(new JLabel("Kode Barang:"));
        panel.add(txtKode, "grow");
        panel.add(new JLabel("Nama Barang:"));
        panel.add(txtNama, "grow, wrap");
        
        panel.add(new JLabel("Kategori:"));
        panel.add(txtKategori, "grow");
        panel.add(new JLabel("Stok:"));
        panel.add(txtStok, "grow, wrap");
        
        panel.add(new JLabel("Harga:"));
        panel.add(txtHarga, "grow");
        panel.add(new JLabel("Satuan:"));
        panel.add(txtSatuan, "grow, wrap");
        
        panel.add(btnSave, "span 2, split 4, grow");
        panel.add(btnUpdate, "grow");
        panel.add(btnDelete, "grow");
        panel.add(btnClear, "grow");
        
        // Event listeners
        btnSave.addActionListener(e -> saveData());
        btnUpdate.addActionListener(e -> updateData());
        btnDelete.addActionListener(e -> deleteData());
        btnClear.addActionListener(e -> clearForm());
        
        return panel;
    }
    
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new MigLayout("fill, insets 10", "[grow]", "[]10[grow]"));
        panel.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        
        // Search
        txtSearch = new JTextField();
        btnSearch = new JButton("Cari");
        
        panel.add(new JLabel("Pencarian:"), "split 3");
        panel.add(txtSearch, "grow");
        panel.add(btnSearch, "wrap");
        
        // Table
        String[] columns = {"ID", "Kode", "Nama Barang", "Kategori", "Stok", "Harga", "Satuan"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        table = new JTable(tableModel);
        table.getColumnModel().getColumn(0).setMaxWidth(50);
        JScrollPane scroll = new JScrollPane(table);
        
        panel.add(scroll, "grow");
        
        // Event
        btnSearch.addActionListener(e -> searchData());
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableClick();
            }
        });
        
        return panel;
    }
    
    private void loadData() {
        tableModel.setRowCount(0);
        // TODO: Get connection dan load data
        // List<Barang> list = dao.getAll();
        // for (Barang b : list) {
        //     tableModel.addRow(new Object[]{...});
        // }
    }
    
    private void saveData() {
        // TODO: Implement save
        JOptionPane.showMessageDialog(this, "Data berhasil disimpan!");
        clearForm();
        loadData();
    }
    
    private void updateData() {
        // TODO: Implement update
        JOptionPane.showMessageDialog(this, "Data berhasil diupdate!");
        clearForm();
        loadData();
    }
    
    private void deleteData() {
        // TODO: Implement delete
        int confirm = JOptionPane.showConfirmDialog(this, "Hapus data ini?");
        if (confirm == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");
            clearForm();
            loadData();
        }
    }
    
    private void searchData() {
        // TODO: Implement search
    }
    
    private void clearForm() {
        txtKode.setText("");
        txtNama.setText("");
        txtKategori.setText("");
        txtStok.setText("");
        txtHarga.setText("");
        txtSatuan.setText("");
        selectedId = 0;
    }
    
    private void tableClick() {
        int row = table.getSelectedRow();
        if (row != -1) {
            selectedId = (int) table.getValueAt(row, 0);
            txtKode.setText(table.getValueAt(row, 1).toString());
            txtNama.setText(table.getValueAt(row, 2).toString());
            txtKategori.setText(table.getValueAt(row, 3).toString());
            txtStok.setText(table.getValueAt(row, 4).toString());
            txtHarga.setText(table.getValueAt(row, 5).toString());
            txtSatuan.setText(table.getValueAt(row, 6).toString());
        }
    }
}