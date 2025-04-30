package cl.prezdev.gui;

import javax.swing.JFrame;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

import java.awt.FlowLayout;

@Component
@AllArgsConstructor
public class MainWindow extends JFrame {

    private final ShowMessageButton showMessageButton;

    @PostConstruct
    public void init(){
        super.setTitle("Ventana Swing con botón");

        setLayout(new FlowLayout());

        add(showMessageButton);

        setSize(300, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
