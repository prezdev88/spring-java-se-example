package cl.prezdev.gui;

import java.awt.Font;
import java.util.Enumeration;

import javax.swing.UIDefaults;
import javax.swing.UIManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UI {

    private UI() {
        throw new UnsupportedOperationException("Utility class");
    }

    private static final float SCALE = 1.5f;

    public static void init() {
        UI.applyScale();
		UI.applyDarkTheme();
        UI.enableFontAntialiasing();
    }

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

    public static void applyDarkTheme() {
        try {
            UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatDarculaLaf());
        } catch (Exception e) {
            log.error("Error al configurar FlatLaf: ", e);
        }
    }

    private static void enableFontAntialiasing() {
		System.setProperty("awt.useSystemAAFontSettings", "on");
    }
}
