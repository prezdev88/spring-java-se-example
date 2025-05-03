package cl.prezdev;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import org.springframework.stereotype.Component;

import cl.prezdev.gui.MenuBar;
import cl.prezdev.gui.PathTextField;
import cl.prezdev.gui.ToolBar;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class TopPanel extends JPanel {

    private final ToolBar toolBar;
    private final PathTextField pathTextField;
   
    @PostConstruct
    private void init() {
        setLayout(new BorderLayout());

        add(toolBar, BorderLayout.WEST);         
        add(pathTextField, BorderLayout.CENTER); 
    }
}
