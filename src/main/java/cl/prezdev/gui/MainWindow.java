package cl.prezdev.gui;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

import java.awt.BorderLayout;

@Component
@AllArgsConstructor
public class MainWindow extends JFrame {

    private final MainSplitPane mainSplitPane;
    private final AppMenuBar appMenuBar;
    private final TopPanel topPanel;

    @PostConstruct
    public void init(){
       setTitle("Ventana Swing con JTextField");
        setLayout(new BorderLayout());

        add(topPanel, BorderLayout.NORTH);                // Colocar el panel en la parte superior
        add(mainSplitPane, BorderLayout.CENTER);      

        // Establecer el contenido principal
        add(mainSplitPane, BorderLayout.CENTER); // Parte central

        setSize(800, 600); // Ajusta el tamaño según necesites
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true); // Asegura que la ventana se muestre
        setJMenuBar(appMenuBar);
    }
}
