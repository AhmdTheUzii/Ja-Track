package com.JaTrack.auth;


import com.JaTrack.main.Form;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;
import raven.modal.demo.system.FormManager;

public class FormLogin extends Form{
    private JLabel imageLogo;
    private JPanel mainPanel;
    private JPanel panelForm;
    
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    
    public FormLogin() {
        init();
    }

    private void init() {
         setLayout(new MigLayout("fill, insets 20", "[center]","[center]"));

        mainPanel = new JPanel(new MigLayout("insets 50", "[][]","[fill][grow]"));
        mainPanel.putClientProperty(
            FlatClientProperties.STYLE,
            "arc:20;" +
            "[light]background:rgb(255,255,255);" +
            "[dark]background:darken($Panel.background,5%);"
        );
        
        JPanel panelLogo = new JPanel(new MigLayout("wrap", "300", ""));
        panelLogo.putClientProperty(
            FlatClientProperties.STYLE, "" +
            "[light]background:rgb(255,255,255);" +
            "[dark]background:darken($Panel.background,5%);"
        );
        
        imageLogo = new JLabel();
        imageLogo.setIcon(new FlatSVGIcon("com/JaTrack/icon/LogoJaws.svg", 150, 55));
        
        JLabel lbTitleLogo = new JLabel("JA-Track");
        lbTitleLogo.putClientProperty(
            FlatClientProperties.STYLE, "" +
            "[light]foreground:rgb(0,0,0);" +
            "font:bold italic +14"
        );
        
        JLabel lbDetail = new JLabel("System Mengelola Data Barang");
        lbDetail.putClientProperty(
            FlatClientProperties.STYLE, "" +
            "[light]foreground:rgb(51,51,51);" +
            "font:bold 16"
        );
        
        JLabel lbCreated = new JLabel("Created By Ogi Dan Manteman");
        lbCreated.putClientProperty(
            FlatClientProperties.STYLE, "" +
            "[light]foreground:rgb(140,140,140);" +
            "font:12"
        );
        
        panelLogo.add(imageLogo, "align center,gapy 50, gap 25px 0px");
        panelLogo.add(lbTitleLogo, "align center, gap 25px 0px");
        panelLogo.add(lbDetail, "align center, gap 25px 0px");
        panelLogo.add(lbCreated, "align center, gap 25px 0px");
        
        panelForm = new JPanel(new MigLayout("wrap, insets 20", "fill, 200:250"));
        panelForm.putClientProperty(
            FlatClientProperties.STYLE, "arc:20;" +
            "[light]background:rgb(4,0,54);" +
            "[dark]background:darken($Panel.background,5%);"
        );
        
        JLabel lbTitleForm = new JLabel("Login", JLabel.CENTER);
        lbTitleForm.putClientProperty(
            FlatClientProperties.STYLE, "" +
            "[light]foreground:rgb(255,255,255);" +
            "font:bold +10"
        );
        
         JLabel lbDescription = new JLabel("Please sign in to access your dashboard", JLabel.CENTER);
        lbDescription.putClientProperty(
            FlatClientProperties.STYLE, "" +
            "[light]foreground:rgb(255,255,255);"
        );
        
        JLabel lbUsername = new JLabel("Username");
        lbUsername.putClientProperty(
            FlatClientProperties.STYLE, "" +
            "[light]foreground:rgb(255,255,255);"
        );
        
        txtUsername = new JTextField();
        txtUsername.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Username");
        txtUsername.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
        txtUsername.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, 
        new FlatSVGIcon("com/JaTrack/icon/username.svg", 20, 20));
        txtUsername.putClientProperty(FlatClientProperties.STYLE, "arc:10");
                
        JLabel lbPassword = new JLabel("Password");
        lbPassword.putClientProperty(
            FlatClientProperties.STYLE, "" +
            "[light]foreground:rgb(255,255,255);"
        );
        
        txtPassword = new JPasswordField();
        txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Password");
        txtPassword.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
        txtPassword.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, 
        new FlatSVGIcon("com/JaTrack/icon/password.svg", 20, 20));
        txtPassword.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:10;"
                + "showRevealButton:true;"
                + "showCapsLock:true");

        btnLogin = new JButton("Login");
        btnLogin.putClientProperty(
            FlatClientProperties.STYLE, "" +
            "[light]background:rgb(255,255,255);" +
            "[light]foreground:rgb(4,0,54);" +
            "arc:10;" +
            "borderWidth:0;" +
            "focusWidth:0;" +
            "innerFocusWidth:0;" +
            "font:bold 16;"
        );
        
        panelForm.add(lbTitleForm);
        panelForm.add(lbDescription);
        panelForm.add(lbUsername, "gapy 8");
        panelForm.add(txtUsername,"hmin 30");
        panelForm.add(lbPassword,"gapy 8");
        panelForm.add(txtPassword,"hmin 30");
        panelForm.add(btnLogin,"hmin 30, gapy 15 15");
        
        mainPanel.add(panelForm);
        mainPanel.add(panelLogo);
        
        add(mainPanel);
        
        btnLogin.addActionListener((e) -> {
            com.JaTrack.main.FormManager.login(); // ✅ FULLY QUALIFIED NAME
        });
    }
}
