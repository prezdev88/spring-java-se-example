package cl.prezdev.gui.panel;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import org.springframework.stereotype.Component;

import cl.prezdev.gui.panel.center.DesktopPanel;
import cl.prezdev.gui.panel.center.IconPanel;
import cl.prezdev.gui.panel.left.files.FileInfoPanel;
import cl.prezdev.gui.panel.left.files.FileTree;
import cl.prezdev.gui.panel.left.files.FileTreeNode;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class MainSplitPane extends JSplitPane {

    private final FileTree fileTree;
    private final FileInfoPanel fileInfoPanel;
    private final DesktopPanel desktopPanel;
    private final transient IconPanel iconPanel;

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

        desktopPanel.addIcon(iconPanel.createIcon("icono1", "file-explorer.folder"));
        desktopPanel.addIcon(iconPanel.createIcon("icono2", "file-explorer.folder"));
        desktopPanel.addIcon(iconPanel.createIcon("icono3", "file-explorer.file"));

        JScrollPane textScroll = new JScrollPane(desktopPanel);

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