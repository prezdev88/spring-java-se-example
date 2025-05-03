package cl.prezdev.gui.menubar;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import org.springframework.stereotype.Component;

import cl.prezdev.gui.menubar.file.FileMenu;
import cl.prezdev.gui.menubar.help.HelpMenu;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AppMenuBar extends JMenuBar {

    private final FileMenu fileMenu;
    private final HelpMenu helpMenu;

    @PostConstruct
    private void init() {
        add(fileMenu);
        add(helpMenu);
    }
}
