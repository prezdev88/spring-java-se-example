package cl.prezdev.gui;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

import org.springframework.stereotype.Component;

import cl.prezdev.gui.files.FileInfoPanel;
import cl.prezdev.gui.files.FileTree;
import cl.prezdev.gui.files.FileTreeNode;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class MainSplitPane extends JSplitPane {

    private final FileTree fileTree;
    private final FileInfoPanel fileInfoPanel;

    @PostConstruct
    public void init() {
        setOrientation(JSplitPane.HORIZONTAL_SPLIT);

        fileTree.addTreeSelectionListener(e -> {
            Object node = fileTree.getLastSelectedPathComponent();
            if (node instanceof FileTreeNode ftn) {
                fileInfoPanel.setFile(ftn.getFile());
            }
        });

        JScrollPane treeScroll = new JScrollPane(fileTree);

        // Panel derecho: otro SplitPane vertical
        JTextArea textArea = new JTextArea("Área superior");
        JScrollPane textScroll = new JScrollPane(textArea);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(fileInfoPanel);

        JSplitPane rightSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, textScroll, bottomPanel);
        rightSplit.setResizeWeight(0.7); // 70% arriba, 30% abajo

        // Configuración del SplitPane principal
        setLeftComponent(treeScroll);
        setRightComponent(rightSplit);
        setResizeWeight(0.3); // 30% árbol, 70% contenido
        setContinuousLayout(true);
    }
}