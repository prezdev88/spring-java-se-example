package cl.prezdev.gui.panel.top;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

import org.springframework.stereotype.Component;

import cl.prezdev.service.UtilIconService;
import cl.prezdev.service.impl.UtilIconServiceImpl;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ToolBar extends JToolBar {

    private final transient UtilIconService utilIconService;

    @PostConstruct
    private void init() {
        JButton backButton = new JButton(utilIconService.load("/icons/back.png"));
        backButton.setToolTipText("Atrás");

        JButton forwardButton = new JButton(utilIconService.load("/icons/forward.png"));
        forwardButton.setToolTipText("Adelante");

        JButton upButton = new JButton(utilIconService.load("/icons/up.png"));
        upButton.setToolTipText("Subir un nivel");

        JButton homeButton = new JButton(utilIconService.load("/icons/home.png"));
        homeButton.setToolTipText("Ir a inicio");

        add(backButton);
        add(forwardButton);
        add(upButton);
        add(homeButton);
    }
}
