package cl.prezdev.gui.menubar.help;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import org.springframework.stereotype.Component;

import cl.prezdev.i18n.MessageService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AboutMenuItem extends JMenuItem {

    private final transient MessageService messageService;

    @PostConstruct
    private void init() {
        setText(messageService.getMessage("menu.help.about"));
        addActionListener(e -> showAboutDialog());
    }

    private void showAboutDialog() {
        String version = messageService.getMessage("version");
        JOptionPane.showMessageDialog(null, version + " 1.0");
    }

}
