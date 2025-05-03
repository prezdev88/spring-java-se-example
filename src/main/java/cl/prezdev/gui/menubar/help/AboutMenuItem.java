package cl.prezdev.gui.menubar.help;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class AboutMenuItem extends JMenuItem {

    @PostConstruct
    private void init() {
        setText("Acerca de");
        addActionListener(e -> showAboutDialog());
    }

    private void showAboutDialog() {
        JOptionPane.showMessageDialog(null, "Versión 1.0");
    }

}
