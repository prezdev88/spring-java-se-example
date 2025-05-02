package cl.prezdev.gui.files;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

import java.net.URL;
import java.awt.Image;
import java.io.File;

@Component
public class FileTreeCellRenderer extends DefaultTreeCellRenderer {

    private transient Icon folderIcon;
    private transient Icon fileIcon;

    @PostConstruct
    public void init() {
        folderIcon = loadIcon("/icons/folder.png");
        fileIcon = loadIcon("/icons/file.png");
    }

    private Icon loadIcon(String path) {
        URL resource = getClass().getResource(path);
        if (resource == null) {
            System.err.println("No se encontró el ícono: " + path);
            return null;
        }
        ImageIcon original = new ImageIcon(resource);
        Image scaled = original.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
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

