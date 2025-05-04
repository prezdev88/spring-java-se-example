package cl.prezdev.gui.panel.top;

import javax.swing.JButton;
import javax.swing.JToolBar;

import org.springframework.stereotype.Component;

import cl.prezdev.i18n.MessageService;
import cl.prezdev.service.IconService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ToolBar extends JToolBar {

    private final transient IconService utilIconService;
    private final transient MessageService messageService;

    @PostConstruct
    private void init() {
        JButton backButton = new JButton(utilIconService.load("toolbar.back"));
        backButton.setToolTipText(messageService.getMessage("toolbar.back"));

        JButton forwardButton = new JButton(utilIconService.load("toolbar.forward"));
        forwardButton.setToolTipText(messageService.getMessage("toolbar.forward"));

        JButton upButton = new JButton(utilIconService.load("toolbar.up"));
        upButton.setToolTipText(messageService.getMessage("toolbar.up"));

        JButton homeButton = new JButton(utilIconService.load("toolbar.home"));
        homeButton.setToolTipText(messageService.getMessage("toolbar.home"));

        add(backButton);
        add(forwardButton);
        add(upButton);
        add(homeButton);
    }
}
