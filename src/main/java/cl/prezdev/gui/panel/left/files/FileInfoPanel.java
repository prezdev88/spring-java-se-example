package cl.prezdev.gui.panel.left.files;

import javax.swing.*;

import org.springframework.stereotype.Component;

import cl.prezdev.i18n.MessageService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.io.File;
import java.text.SimpleDateFormat;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileInfoPanel extends JPanel {

    private JLabel nameLabel;
    private JLabel pathLabel;
    private JLabel sizeLabel;
    private JLabel typeLabel;
    private JLabel modifiedLabel;
    private JLabel permissionsLabel;

    private SimpleDateFormat dateFormat;
    private final transient MessageService messageService;

    @PostConstruct
    public void init () {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        nameLabel = new JLabel();
        pathLabel = new JLabel();
        sizeLabel = new JLabel();
        typeLabel = new JLabel();
        modifiedLabel = new JLabel();
        permissionsLabel = new JLabel();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createTitledBorder(messageService.getMessage("file.info.title")));
        setPreferredSize(new Dimension(300, 200));

        addRow(messageService.getMessage("file.info.name"), nameLabel);
        addRow(messageService.getMessage("file.info.path"), pathLabel);
        addRow(messageService.getMessage("file.info.size"), sizeLabel);
        addRow(messageService.getMessage("file.info.type"), typeLabel);
        addRow(messageService.getMessage("file.info.modified"), modifiedLabel);
        addRow(messageService.getMessage("file.info.permissions"), permissionsLabel);

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
        typeLabel.setText(file.isDirectory() ? messageService.getMessage("file.info.type.directory") : messageService.getMessage("file.info.type.file"));
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
                    if (label instanceof JLabel jlabel && jlabel.getText() != null && !jlabel.getText().endsWith(":")) {
                        jlabel.setText("-");
                    }
                }
            }
        }
    }
}
