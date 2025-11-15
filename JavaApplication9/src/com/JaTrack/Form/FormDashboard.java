/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.JaTrack.Form;

import com.JaTrack.main.Form;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author HP 14S
 */
public class FormDashboard extends Form {
    
    public FormDashboard() {  
        init();
    }
    
    private void init() {
        // ✅ Pakai new MigLayout() dan kurung kurawal
        setLayout(new MigLayout("insets 10, fill, wrap", "fill", "[]10[fill, grow]"));
        JLabel title = new JLabel("Ini dasahboar nya belum ada belakangan aja");
        add(title);
    }
}