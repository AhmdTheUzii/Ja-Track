package com.JaTrack.main;

import com.JaTrack.Form.FormDashboard;
import com.JaTrack.auth.FormLogin;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import raven.modal.Drawer;
import raven.modal.demo.utils.UndoRedo;


public class FormManager {
    
    
    public static final UndoRedo<Form> FORMS = new UndoRedo<>();
    private static MainForm mainForm;
    private static JFrame frame;
    private static FormLogin formLogin;
    private static String idUser;
    
            
    public static void install(JFrame f) {
        frame = f;
        logout();
    }
    
    public static void showForm(Form form) {
        if(form != FORMS.getCurrent()) {
            FORMS.add(form);
            form.formCheck();
            form.formOpen();
            mainForm.setForm(form);
        }
    }
    
    public static void undo(){
        if(FORMS.isUndoAble()) {
            Form form = FORMS.undo();
            form.formCheck();
            form.formOpen();
            mainForm.setForm(form);
            Drawer.setSelectedItemClass(form.getClass());
        }
    }
    
    public static void redo(){
         if(FORMS.isRedoAble()) {
            Form form = FORMS.redo();
            form.formCheck();
            form.formOpen();
            mainForm.setForm(form);
            Drawer.setSelectedItemClass(form.getClass());
        }
    }
    
    public static void login() {
        idUser = "";
        Drawer.installDrawer(frame, new Menu());
        Drawer.setVisible(true);
        
        frame.getContentPane().removeAll();
        frame.getContentPane().add(getMainForm());
        Drawer.setSelectedItemClass(FormDashboard.class);
        frame.revalidate();
        frame.repaint();
}
    
    public static void logout(){
    if(idUser == null) {
        Drawer.installDrawer(frame, new Menu());
    }
     Drawer.setVisible(false);
    frame.getContentPane().removeAll();
    frame.getRootPane().getGlassPane().setVisible(false);
    mainForm = null;
    formLogin = null;
    FormLogin login = getLogin();
    login.formCheck();
    frame.getContentPane().add(login);
    frame.revalidate();
    frame.repaint();
    }
    
    public static JFrame getFrame() {
        return frame;
    }
    
    private static  MainForm getMainForm() {
        if(mainForm == null) {
            mainForm = new MainForm();
        }
        return mainForm;
    }
    
    private static FormLogin getLogin() {
        if(formLogin == null) {
            formLogin = new FormLogin();
        }
        return formLogin;
    }
}
