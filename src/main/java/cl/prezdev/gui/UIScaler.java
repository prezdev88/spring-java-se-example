package cl.prezdev.gui;

import java.awt.Font;
import java.util.Enumeration;

import javax.swing.UIDefaults;
import javax.swing.UIManager;

public class UIScaler {

    private static final float SCALE = 2.5f;

    public static void applyScale() {
        UIDefaults defaults = UIManager.getLookAndFeelDefaults();
        Enumeration<Object> keys = defaults.keys();

        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = defaults.get(key);
            if (value instanceof Font font) {
                Font newFont = font.deriveFont(font.getSize2D() * SCALE);
                UIManager.put(key, newFont);
            }
        }
    }
}
