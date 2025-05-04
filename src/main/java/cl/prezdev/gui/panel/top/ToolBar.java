package cl.prezdev.gui.panel.top;

import javax.swing.JButton;
import javax.swing.JToolBar;

import org.springframework.stereotype.Component;

import cl.prezdev.service.UtilIconService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ToolBar extends JToolBar {

    private final transient UtilIconService utilIconService;

    @PostConstruct
    private void init() {
        JButton backButton = new JButton(utilIconService.load("toolbar.back"));
        backButton.setToolTipText("Atrás");

        JButton forwardButton = new JButton(utilIconService.load("toolbar.forward"));
        forwardButton.setToolTipText("Adelante");

        JButton upButton = new JButton(utilIconService.load("toolbar.up"));
        upButton.setToolTipText("Subir un nivel");

        JButton homeButton = new JButton(utilIconService.load("toolbar.home"));
        homeButton.setToolTipText("Ir a inicio");

        add(backButton);
        add(forwardButton);
        add(upButton);
        add(homeButton);
    }
}
