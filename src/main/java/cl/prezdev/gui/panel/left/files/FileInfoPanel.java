package cl.prezdev.gui.panel.left.files;

import javax.swing.*;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.io.File;
import java.text.SimpleDateFormat;

@Slf4j
@Component
public class FileInfoPanel extends JPanel {

    private final JLabel nameLabel = new JLabel();
    private final JLabel pathLabel = new JLabel();
    private final JLabel sizeLabel = new JLabel();
    private final JLabel typeLabel = new JLabel();
    private final JLabel modifiedLabel = new JLabel();
    private final JLabel permissionsLabel = new JLabel();

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @PostConstruct
    public void init () {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createTitledBorder("Información del archivo"));
        setPreferredSize(new Dimension(300, 200));

        addRow("Nombre:", nameLabel);
        addRow("Ruta:", pathLabel);
        addRow("Tamaño:", sizeLabel);
        addRow("Tipo:", typeLabel);
        addRow("Modificado:", modifiedLabel);
        addRow("Permisos:", permissionsLabel);

        setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
    }

    private void addRow(String title, JLabel valueLabel) {
        JPanel row = new JPanel();
        row.setLayout(new BoxLayout(row, BoxLayout.X_AXIS));
    
        JLabel titleLabel = new JLabel(title);
        titleLabel.setPreferredSize(new Dimension(80, 25));
    
        valueLabel.setText("-");
        valueLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
        valueLabel.setPreferredSize(new Dimension(0, 25));
        valueLabel.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
    
        row.add(titleLabel);
        row.add(Box.createRigidArea(new Dimension(5, 0)));
        row.add(valueLabel);
    
        row.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        add(row);
    }
    

    public void setFile(File file) {
        if (file == null || !file.exists()) {
            clear();
            return;
        }

        nameLabel.setText(file.getName());
        pathLabel.setText(file.getAbsolutePath());
        sizeLabel.setText(file.isFile() ? file.length() + " bytes" : "-");
        typeLabel.setText(file.isDirectory() ? "Directorio" : "Archivo");
        modifiedLabel.setText(dateFormat.format(file.lastModified()));

        StringBuilder perms = new StringBuilder();
        perms.append(file.canRead() ? "r" : "-");
        perms.append(file.canWrite() ? "w" : "-");
        perms.append(file.canExecute() ? "x" : "-");
        permissionsLabel.setText(perms.toString());

        log.info("File selected: {}", file.getAbsolutePath());
    }

    public void clear() {
        for (java.awt.Component comp : getComponents()) {
            if (comp instanceof JPanel row) {
                for (java.awt.Component label : row.getComponents()) {
                    if (label instanceof JLabel && ((JLabel) label).getText() != null && !((JLabel) label).getText().endsWith(":")) {
                        ((JLabel) label).setText("-");
                    }
                }
            }
        }
    }
}
