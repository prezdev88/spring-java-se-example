package cl.prezdev.gui;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

import java.awt.FlowLayout;

@Component
@AllArgsConstructor
public class MainWindow extends JFrame {

    private final MainSplitPane mainSplitPane;

    @PostConstruct
    public void init(){
        super.setTitle("Ventana Swing con botón");

        setLayout(new FlowLayout());

        setSize(300, 100);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(mainSplitPane);
    }
}
