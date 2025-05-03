package cl.prezdev.gui.panel.left.files;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class FileSystemTreeModel implements TreeModel {

    private final FileTreeNode root;

    public FileSystemTreeModel() {
        this.root = new FileTreeNode(new File("/"));
    }

    @Override
    public Object getRoot() {
        return root;
    }

    @Override
    public Object getChild(Object parent, int index) {
        return ((FileTreeNode) parent).getChildren().get(index);
    }

    @Override
    public int getChildCount(Object parent) {
        return ((FileTreeNode) parent).getChildren().size();
    }

    @Override
    public boolean isLeaf(Object node) {
        return ((FileTreeNode) node).isLeaf();
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        return ((FileTreeNode) parent).getChildren().indexOf(child);
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {}

    @Override
    public void addTreeModelListener(TreeModelListener l) {}

    @Override
    public void removeTreeModelListener(TreeModelListener l) {}
}

