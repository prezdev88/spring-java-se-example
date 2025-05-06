package cl.prezdev.gui.panel;

import java.awt.BorderLayout;
import java.io.File;
import java.util.Arrays;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import org.springframework.stereotype.Component;

import cl.prezdev.gui.panel.center.DesktopPanel;
import cl.prezdev.gui.panel.center.IconBuilder;
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
    private final transient IconBuilder iconPanel;

    @PostConstruct
    public void init() {
        setOrientation(JSplitPane.HORIZONTAL_SPLIT);

        fileTree.addTreeSelectionListener(e -> {
            Object node = fileTree.getLastSelectedPathComponent();
            if (node instanceof FileTreeNode ftn) {
                File selectedFile = ftn.getFile();
                fileInfoPanel.setFile(selectedFile);

                // Limpiar los íconos existentes en el DesktopPanel
                desktopPanel.resetPanel();

                // Si es un directorio, agregar íconos para sus hijos
                if (selectedFile.isDirectory()) {
                    File[] files = selectedFile.listFiles();
                    if (files != null) {
                        // Ordenar los archivos: carpetas primero, luego archivos, y por nombre
                        Arrays.sort(files, (f1, f2) -> {
                            if (f1.isDirectory() && !f2.isDirectory()) return -1;
                            if (!f1.isDirectory() && f2.isDirectory()) return 1;
                            return f1.getName().compareToIgnoreCase(f2.getName());
                        });

                        // Crear y agregar íconos al DesktopPanel
                        for (File file : files) {
                            String iconKey = file.isDirectory() ? "file-explorer.folder" : "file-explorer.file";
                            desktopPanel.addIcon(iconPanel.createIcon(file.getName(), iconKey));
                        }
                    }
                }

                desktopPanel.organizeIcons();
            }
        });

        JScrollPane treeScroll = new JScrollPane(fileTree);

        desktopPanel.addIcon(iconPanel.createIcon("icono1", "file-explorer.folder"));
        desktopPanel.addIcon(iconPanel.createIcon("icono2", "file-explorer.folder"));
        desktopPanel.addIcon(iconPanel.createIcon("icono3", "file-explorer.file"));

        JScrollPane desktopScrollPane = new JScrollPane(desktopPanel);

        // Ajustar la velocidad del scroll
        desktopScrollPane.getVerticalScrollBar().setUnitIncrement(20); // Incremento vertical
        desktopScrollPane.getHorizontalScrollBar().setUnitIncrement(20); // Incremento horizontal

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(fileInfoPanel);

        JSplitPane rightSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, desktopScrollPane, bottomPanel);
        rightSplit.setResizeWeight(0.7); // 70% arriba, 30% abajo

        // Configuración del SplitPane principal
        setLeftComponent(treeScroll);
        setRightComponent(rightSplit);
        setResizeWeight(0.3); // 30% árbol, 70% contenido
        setContinuousLayout(true);
    }
}