package com.JaTrack.main;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.BorderLayout;
import java.awt.Component;
import java.text.*;
import java.util.Date;
import javax.swing.*;
import net.miginfocom.swing.*;
import raven.modal.*;
import raven.modal.demo.icons.*;
import javax.swing.Timer;

public class MainForm extends JPanel {

    private JPanel mainPanel;
    private JButton buttonUndo;
    private JButton buttonRedo;

    public MainForm() {
        init();
    }

    private void init() {
        setLayout(new MigLayout("fillx, wrap, insets 0, gap 0", "[fill]", "[fill, grow][fill]"));
        add(createHeader());
        add(createMain());
        add(new JSeparator(), "height 2!");
        add(createFooter());     
    }

    private JPanel createHeader() {
        JPanel panel = new JPanel(new MigLayout("insets 3", "[]push[]push", "[fill]"));

        JToolBar toolBar = new JToolBar();
        JButton buttonDrawer = new JButton(new FlatSVGIcon("raven/modal/demo/icons/menu.svg", 0.5f));
        buttonUndo = new JButton(new FlatSVGIcon("raven/modal/demo/icons/undo.svg", 0.5f));
        buttonRedo = new JButton(new FlatSVGIcon("raven/modal/demo/icons/redo.svg", 0.5f));

        buttonDrawer.addActionListener(e -> {
            if (Drawer.isOpen()) {
                Drawer.showDrawer();
            } else {
                Drawer.toggleMenuOpenMode();
            }
        });

        buttonUndo.addActionListener((e) -> FormManager.undo());
        buttonRedo.addActionListener((e) -> FormManager.redo());
        
        toolBar.add(buttonDrawer);
        toolBar.add(buttonUndo);
        toolBar.add(buttonRedo);
        
        panel.add(toolBar);
        
        return panel;
    }
    
    
    private JPanel createFooter() {
    JPanel panel = new JPanel(new MigLayout(
            "insets 1 n 1 n, al trailing center, gapx 10",  // layout constraint
            "[]push[][]",                                  // column constraint
            "[fill, 30!]"                                  // row constraint (tinggi 30)
    ));

    panel.putClientProperty(
        FlatClientProperties.STYLE,
        "[light]background:lighten(@background,5%);"
        + "[dark]background:darken(@background,5%);"
    );

    JLabel lbAppVersion = new JLabel("Ja-Track v" + Main.APP_VERSION);
    lbAppVersion.putClientProperty(FlatClientProperties.STYLE, "foreground:$Label.disableForeground");
    lbAppVersion.setIcon(new SVGIconUIColor("raven/modal/demo/icons/git.svg", 1f, "Label.disableForeground"));
    panel.add(lbAppVersion);
    


    String javaVendor = System.getProperty("java.vendor");
    if (javaVendor.equals("Oracle Corporation")) {
        javaVendor = "";
    }

    String java = javaVendor + " v" + System.getProperty("java.version").trim();
    String st = "Running on: Java %s";

    JLabel lbJava = new JLabel(String.format(st, java));
    lbJava.putClientProperty(
        FlatClientProperties.STYLE,
        "foreground:$Label.disabledForeground"
    );
    lbJava.setIcon(new FlatSVGIcon("raven/modal/demo/icons/java.svg", 1f));

    panel.add(lbJava);
    panel.add(new JSeparator(JSeparator.VERTICAL));
    
    JLabel lbDate = new JLabel();
    lbDate.putClientProperty(FlatClientProperties.STYLE, "foreground:$Label.disabledForeground");

    Timer timer = new Timer(1000, (e) -> {  // 1000 ms biar update tiap detik, bukan tiap 1 ms
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss"); 
    lbDate.setText(df.format(new Date()));
    });
    panel.add(lbDate);
    timer.start();

    return panel;

    
    }
    
    private Component createMain() {
        mainPanel = new JPanel(new BorderLayout());
        return mainPanel;
    }
    
    public void setForm(Form form) {
        mainPanel.removeAll();
        mainPanel.add(form);
        mainPanel.repaint();
        mainPanel.revalidate();
        
        buttonUndo.setEnabled(FormManager.FORMS.isUndoAble());
        buttonRedo.setEnabled(FormManager.FORMS.isRedoAble());
        
        if(mainPanel.getComponentOrientation().isLeftToRight() != form.getComponentOrientation().isLeftToRight()) {
            applyComponentOrientation(mainPanel.getComponentOrientation());
        }
    }
}
