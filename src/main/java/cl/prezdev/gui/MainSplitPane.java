package cl.prezdev.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

import org.springframework.stereotype.Component;

import cl.prezdev.gui.files.FileTree;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class MainSplitPane extends JSplitPane {

    private final FileTree fileTree;

    @PostConstruct
    public void init() {
        setOrientation(JSplitPane.HORIZONTAL_SPLIT);

        JScrollPane treeScroll = new JScrollPane(fileTree);

        // Panel derecho: otro SplitPane vertical
        JTextArea textArea = new JTextArea("Área superior");
        JScrollPane textScroll = new JScrollPane(textArea);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(new JLabel("Panel inferior"));

        JSplitPane rightSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, textScroll, bottomPanel);
        rightSplit.setResizeWeight(0.7); // 70% arriba, 30% abajo

        // Configuración del SplitPane principal
        setLeftComponent(treeScroll);
        setRightComponent(rightSplit);
        setResizeWeight(0.3); // 30% árbol, 70% contenido
        setContinuousLayout(true);
    }
}