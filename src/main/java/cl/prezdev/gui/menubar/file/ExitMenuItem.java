package cl.prezdev.gui.menubar.file;

import javax.swing.JMenuItem;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class ExitMenuItem extends JMenuItem {
    @PostConstruct
    private void init() {
        setText("Salir");
        addActionListener(e -> System.exit(0));
    }
}
