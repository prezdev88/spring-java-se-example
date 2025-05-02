package cl.prezdev.gui.files;

import javax.swing.*;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class FileTree extends JTree {

    private final transient FileSystemTreeModel fileSystemTreeModel;
    private final transient FileTreeCellRenderer fileTreeCellRenderer;

    @PostConstruct
    public void init() {
        setModel(fileSystemTreeModel);
        setCellRenderer(fileTreeCellRenderer);
        setRootVisible(false);
        setShowsRootHandles(true);
    }
}

