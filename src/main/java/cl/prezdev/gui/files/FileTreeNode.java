package cl.prezdev.gui.files;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class FileTreeNode {

    private final File file;

    public FileTreeNode(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public String toString() {
        return file.getName().isEmpty() ? file.getPath() : file.getName();
    }

    public boolean isLeaf() {
        return file.isFile();
    }

    public List<FileTreeNode> getChildren() {
        File[] files = file.listFiles();
    
        if (files != null) {
            return Arrays.stream(files)
                    .filter(f -> f.canRead()) // ← evita colapsos por falta de permisos
                    .sorted((f1, f2) -> {
                        if (f1.isDirectory() && !f2.isDirectory()) return -1;
                        if (!f1.isDirectory() && f2.isDirectory()) return 1;
                        return f1.getName().compareToIgnoreCase(f2.getName());
                    })
                    .map(FileTreeNode::new)
                    .toList();
        }
        return List.of();
    }
    
    
}
