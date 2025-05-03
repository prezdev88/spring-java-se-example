package cl.prezdev.gui.menubar.help;

import javax.swing.JMenu;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class HelpMenu extends JMenu {

    private final AboutMenuItem aboutMenuItem;

    @PostConstruct
    private void init() {
        setText("Ayuda");
        add(aboutMenuItem);
    }

}
