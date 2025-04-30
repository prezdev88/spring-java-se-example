package cl.prezdev.gui;

import javax.swing.JButton;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ShowMessageButton extends JButton {

    private final transient ShowMessageListener showMessageListener;

    @PostConstruct
    public void init() {
        super.setText("Haz clic aquí");
        addActionListener(showMessageListener);
    }
}
