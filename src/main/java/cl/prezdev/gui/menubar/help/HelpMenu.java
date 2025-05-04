package cl.prezdev.gui.menubar.help;

import javax.swing.JMenu;

import org.springframework.stereotype.Component;

import cl.prezdev.i18n.MessageService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class HelpMenu extends JMenu {

    private final AboutMenuItem aboutMenuItem;
    private final transient MessageService messageService;

    @PostConstruct
    private void init() {
        setText(messageService.getMessage("menu.help"));
        add(aboutMenuItem);
    }

}
