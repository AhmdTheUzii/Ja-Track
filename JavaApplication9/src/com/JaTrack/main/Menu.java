package com.JaTrack.main;

import com.JaTrack.Form.FormDashboard;
import com.JaTrack.form.FormBarang;
import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Insets;
import javax.swing.UIManager;
import raven.extras.AvatarIcon;
import raven.modal.drawer.DrawerPanel;
import raven.modal.drawer.data.Item;
import raven.modal.drawer.data.MenuItem;
import raven.modal.drawer.menu.MenuAction;
import raven.modal.drawer.menu.MenuEvent;
import raven.modal.drawer.menu.MenuOption;
import raven.modal.drawer.renderer.DrawerStraightDotLineStyle;
import raven.modal.drawer.simple.SimpleDrawerBuilder;
import raven.modal.drawer.simple.footer.SimpleFooterData;
import raven.modal.drawer.simple.header.SimpleHeaderData;
import raven.modal.option.Option;

public class Menu extends SimpleDrawerBuilder{
    
    private final int SHADOW_SIZE = 0;
    
    public Menu() {
        super(createSimpleMenuOption());
    }

    @Override
    public SimpleHeaderData getSimpleHeaderData() {
        AvatarIcon icon = new AvatarIcon(getClass().getResource("/com/JaTrack/img/profil.jpeg"), 50, 50, 3.5f);
        icon.setType(AvatarIcon.Type.MASK_SQUIRCLE);
        icon.setBorder(2, 2);
        
        changeAvatarIconBorderColor(icon);
        
        UIManager.addPropertyChangeListener((evt) -> {
            if(evt.getPropertyName().equals("lookAndFeel")) {
                changeAvatarIconBorderColor(icon);
            }
        });
        
        return new SimpleHeaderData()
                .setIcon(icon)
                .setTitle("Administrator")
                .setDescription("@Jackson");
    }
    
    private void changeAvatarIconBorderColor(AvatarIcon icon) {
        icon.setBorderColor(new AvatarIcon.BorderColor(UIManager.getColor("Component.accentColor"), 0.7f));
    }
    
    @Override
    public SimpleFooterData getSimpleFooterData() {
        return new SimpleFooterData()
                .setDescription("Version " + Main.APP_VERSION); // ✅ Fixed: added space
    }
    
    public static MenuOption createSimpleMenuOption() {
    String role = "Admin";
    MenuOption simpleMenuOption = new MenuOption();
    MenuItem[] adminMenu = new MenuItem[] {
        new Item.Label("MAIN"),
        new Item("Dashboard", "com/JaTrack/icon/dashboard.svg", FormDashboard.class),
        new Item.Label("MASTER DATA"),
        new Item("Barang", "com/JaTrack/icon/manage.svg")
            .subMenu("Data Barang", FormBarang.class),
        new Item("Kategori", "com/JaTrack/icon/kategori.svg")
            .subMenu("Data Kategori" ),
        new Item("Distributor", "com/JaTrack/icon/distributor.svg")
            .subMenu("Data Distributor"),
        new Item.Label("PRODUKSI"),
        new Item("Produksi", "com/JaTrack/icon/produksi.svg")
            .subMenu("Data Produksi"),
        new Item.Label("TRANSAKSI"),
        new Item("Transaksi", "com/JaTrack/icon/transaksi.svg")
            .subMenu("Data Transaksi"),
        new Item.Label("LAPORAN"),
        new Item("Laporan", "com/JaTrack/icon/report.svg")
            .subMenu("Laporan Barang")
            .subMenu("Laporan Transaksi")
            .subMenu("Laporan Produksi")
            .subMenu("Laporan Distributor"),
        new Item.Label("LAINNYA"),
        new Item("About", "com/JaTrack/icon/about.svg"),
        new Item("Logout", "com/JaTrack/icon/logout.svg")
    };
        
        // ✅ Fixed: Set menu style dan mode
        
        simpleMenuOption.getMenuStyle().setDrawerLineStyleRenderer(new DrawerStraightDotLineStyle());
        simpleMenuOption.setMenuItemAutoSelectionMode(MenuOption.MenuItemAutoSelectionMode.SELECT_SUB_MENU_LEVEL);
        
        // ✅ Fixed: Event handler yang proper
        simpleMenuOption.addMenuEvent(new MenuEvent() {
            @Override
            public void selected(MenuAction action, int[] ints) {
                String itemName = action.getItem().getName();
                Class<?> itemClass = action.getItem().getItemClass();

                // Handle form dengan class
                if (itemClass != null) {
                    handleFormAction(itemClass, action);
                    return;
                }

                // Handle menu khusus tanpa class
                if (role.equalsIgnoreCase("Admin")) {
                    switch (itemName) {
                        case "Logout":
                            action.consume();
                            FormManager.logout();
                            break;

                        case "About":
                            // misalnya nanti lo mau munculin form about
                            // FormManager.showForm(FormAbout.class);
                            break;

                        case "Barang":
                            // buka form barang
                            // FormManager.showForm(FormBarang.class);
                            break;

                        // tambahin case lain kalau perlu
                    }
                }
            }
            
            // ✅ Fixed: Method ini sekarang di dalam anonymous class MenuEvent
            private void handleFormAction(Class<?> itemClass, MenuAction action) {
                if (itemClass == null || !Form.class.isAssignableFrom(itemClass)) { // ✅ Fixed typo: isAssignableFrom
                    action.consume();
                    return;
                }
                @SuppressWarnings("unchecked")
                Class<? extends Form> formClass = (Class<? extends Form>) itemClass;
                FormManager.showForm(AllForms.getForm(formClass));
            }
        });
        
        // ✅ Fixed: Set menus dan base icon path
        simpleMenuOption.setMenus(adminMenu);
        return simpleMenuOption;
    }

    @Override
    public int getDrawerWidth() {
        return 270 + SHADOW_SIZE;
    }

    @Override
    public int getDrawerCompactWidth() {
        return 80 + SHADOW_SIZE;
    }

    @Override
    public int getOpenDrawerAt() {
       return 1000;
    }

    @Override
    public Option getOption() {
        Option option = super.getOption();
        option.setOpacity(0.3f);
        option.getBorderOption()
                .setShadowSize(new Insets(0, 0, 0, SHADOW_SIZE));
        return option;
    }

    @Override
    public boolean openDrawerAtScale() {
        return false;
    }
    
    @Override
    public void build(DrawerPanel drawerPanel) { // ✅ Added DrawerPanel type (adjust if different)
        drawerPanel.putClientProperty(FlatClientProperties.STYLE, getDrawerBackgroundStyle());
    }
    
    public static String getDrawerBackgroundStyle() {
        return "" 
                + "[light]background:tint(@background,50%);" // ✅ Fixed typo: "thit" -> "tint"
                + "[dark]background:tint(@background,10%);";
    }
}