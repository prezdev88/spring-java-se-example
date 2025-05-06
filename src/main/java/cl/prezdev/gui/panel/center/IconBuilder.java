package cl.prezdev.gui.panel.center;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.springframework.stereotype.Component;

import cl.prezdev.service.IconService;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class IconBuilder {

    private final IconService iconService;

    public JPanel createIcon(String text, String iconPropertyKey) {
        JPanel iconPanel = new JPanel();
        iconPanel.setLayout(new BorderLayout());
        iconPanel.setOpaque(false);

        Icon icon = iconService.load(iconPropertyKey);
        if (icon instanceof ImageIcon imageIcon) {
            icon = new ImageIcon(imageIcon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));
        }

        JLabel iconLabel = new JLabel(icon);
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel textLabel = new JLabel(text, SwingConstants.CENTER);
        textLabel.setForeground(Color.WHITE);

        iconPanel.add(iconLabel, BorderLayout.CENTER);
        iconPanel.add(textLabel, BorderLayout.SOUTH);

        iconPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        iconPanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(iconPanel, "Icon clicked: " + text);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // This method is intentionally left empty as no action is required on mouse press.
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // This method is intentionally left empty as no action is required on mouse release.
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                iconPanel.setBackground(Color.LIGHT_GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                iconPanel.setBackground(null);
            }
        });

        return iconPanel;
    }
}
