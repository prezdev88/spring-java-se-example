package cl.prezdev.gui.panel.top;

import javax.swing.JTextField;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class PathTextField extends JTextField {
    @PostConstruct
    private void init() {
        setEditable(false);
        setText("/home/prezdev");
    }      
}
