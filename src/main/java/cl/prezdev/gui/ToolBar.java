package cl.prezdev.gui;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ToolBar extends JToolBar {

    private final UtilIcon utilIcon;

    @PostConstruct
    private void init() {
        JButton backButton = new JButton(utilIcon.loadIcon("/icons/back.png"));
        backButton.setToolTipText("Atrás");

        JButton forwardButton = new JButton(utilIcon.loadIcon("/icons/forward.png"));
        forwardButton.setToolTipText("Adelante");

        JButton upButton = new JButton(utilIcon.loadIcon("/icons/up.png"));
        upButton.setToolTipText("Subir un nivel");

        JButton homeButton = new JButton(utilIcon.loadIcon("/icons/home.png"));
        homeButton.setToolTipText("Ir a inicio");

        add(backButton);
        add(forwardButton);
        add(upButton);
        add(homeButton);
    }
}
