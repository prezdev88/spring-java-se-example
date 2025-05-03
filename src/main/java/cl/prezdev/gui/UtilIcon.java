package cl.prezdev.gui;

import java.awt.Image;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.springframework.stereotype.Service;

@Service
public class UtilIcon {
    public Icon loadIcon(String path) {
        URL resource = getClass().getResource(path);
        if (resource == null) {
            System.err.println("No se encontró el ícono: " + path);
            return null;
        }
        ImageIcon original = new ImageIcon(resource);
        Image scaled = original.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }
}
