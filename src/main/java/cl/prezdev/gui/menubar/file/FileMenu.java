package cl.prezdev.gui.menubar.file;

import javax.swing.JMenu;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class FileMenu extends JMenu {

    private final ExitMenuItem exitMenuItem;

    @PostConstruct
    private void init() {
        setText("Archivo");
        add(exitMenuItem);
    }
}
