package com.JaTrack.main;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.SwingUtilities;

public class AllForms {

    private static AllForms instance;
    private final Map<Class<? extends Form>, Form> formsMap;

    private static AllForms getInstance() {
        if (instance == null) {
            instance = new AllForms();
        }
        return instance;
    }

    public AllForms() {
        formsMap = new HashMap<>();
    }

    public static Form getForm(Class<? extends Form> cls) {
        // Cek apakah form sudah pernah dibuat
        if (getInstance().formsMap.containsKey(cls)) {
            return getInstance().formsMap.get(cls);
        }

        try {
            // Buat instance form baru menggunakan refleksi
            Form form = cls.getDeclaredConstructor().newInstance();
            // Simpan instance form ke dalam map agar tidak dibuat lagi nanti
            getInstance().formsMap.put(cls, form);
            return form;

        } catch (NoSuchMethodException | InvocationTargetException |
                 InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static void formInit(Form form) {
        SwingUtilities.invokeLater(() -> form.formInit());
    }
}
