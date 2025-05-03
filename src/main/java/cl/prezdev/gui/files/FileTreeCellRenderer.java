package cl.prezdev.gui.files;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import org.springframework.stereotype.Component;

import cl.prezdev.service.UtilIconService;
import cl.prezdev.service.impl.UtilIconServiceImpl;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

import java.io.File;

@Component
@RequiredArgsConstructor
public class FileTreeCellRenderer extends DefaultTreeCellRenderer {

    private transient Icon folderIcon;
    private transient Icon fileIcon;
    private final transient UtilIconService utilIconService;

    @PostConstruct
    public void init() {
        folderIcon = utilIconService.load("/icons/folder.png");
        fileIcon = utilIconService.load("/icons/file.png");
    }

    @Override
    public java.awt.Component getTreeCellRendererComponent(
            JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {

        JLabel label = (JLabel) super.getTreeCellRendererComponent(
                tree, value, sel, expanded, leaf, row, hasFocus);

        if (value instanceof FileTreeNode node) {
            File file = node.getFile();
            label.setIcon(file.isDirectory() ? folderIcon : fileIcon);
        }

        return label;
    }
}

