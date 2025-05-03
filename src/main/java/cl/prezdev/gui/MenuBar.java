package cl.prezdev.gui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class MenuBar extends JMenuBar {
    @PostConstruct
    private void init() {
        JMenu fileMenu = new JMenu("Archivo");
        JMenuItem exitItem = new JMenuItem("Salir");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);

        JMenu helpMenu = new JMenu("Ayuda");
        JMenuItem acercaDeItem = new JMenuItem("Acerca de");
        acercaDeItem.addActionListener(e -> JOptionPane.showMessageDialog(this, "Versión 1.0"));
        helpMenu.add(acercaDeItem);

        // Agregar menús a la barra
        add(fileMenu);
        add(helpMenu);
    }
}
