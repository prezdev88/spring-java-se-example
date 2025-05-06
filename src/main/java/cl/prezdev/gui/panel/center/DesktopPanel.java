package cl.prezdev.gui.panel.center;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JPanel;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class DesktopPanel extends JPanel {

    private JPanel draggedIcon;
    private Point initialClick;
    private Point selectionStart;
    private Rectangle selectionRectangle;

    @PostConstruct
    private void init() {
        setLayout(null);
        setBackground(new Color(40, 40, 40));

        // Listener para manejar la selección con el mouse
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                selectionStart = e.getPoint();
                selectionRectangle = new Rectangle();
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                selectIconsInRectangle();
                selectionStart = null;
                selectionRectangle = null;
                repaint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (selectionStart != null) {
                    int x = Math.min(selectionStart.x, e.getX());
                    int y = Math.min(selectionStart.y, e.getY());
                    int width = Math.abs(selectionStart.x - e.getX());
                    int height = Math.abs(selectionStart.y - e.getY());
                    selectionRectangle = new Rectangle(x, y, width, height);
                    repaint();
                }
            }
        });

        // Listener para organizar íconos automáticamente cuando el componente esté visible
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                organizeIcons();
            }
        });
    }

    public void resetPanel() {
        removeAll();
        revalidate();
        repaint();
    }

    public void addIcon(JPanel icon) {
        add(icon);
        icon.setBounds(0, 0, 120, 120);
        enableDrag(icon);
        revalidate();
        repaint();
    }

    private void enableDrag(JPanel icon) {
        icon.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                draggedIcon = icon;
                initialClick = e.getPoint();
                icon.getParent().setComponentZOrder(icon, 0);
            }
        });

        icon.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (draggedIcon != null) {
                    Point location = draggedIcon.getLocation();
                    int deltaX = e.getX() - initialClick.x;
                    int deltaY = e.getY() - initialClick.y;

                    // Mover todos los íconos seleccionados
                    for (int i = 0; i < getComponentCount(); i++) {
                        if (getComponent(i) instanceof JPanel) {
                            JPanel icon = (JPanel) getComponent(i);
                            if (icon.getBackground().equals(new Color(0, 120, 215))) { // Verificar si está seleccionado
                                Point iconLocation = icon.getLocation();
                                icon.setLocation(iconLocation.x + deltaX, iconLocation.y + deltaY);
                            }
                        }
                    }

                    // Actualizar la posición del ícono arrastrado
                    draggedIcon.setLocation(location.x + deltaX, location.y + deltaY);
                }
            }
        });

        icon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                draggedIcon = null; // Liberar el ícono arrastrado
            }
        });
    }

    public void organizeIcons() {
        int iconWidth = 120;
        int iconHeight = 120;
        int padding = 10;
        int x = padding;
        int y = padding;
        int panelWidth = getParent() != null ? getParent().getWidth() : 0;

        if (panelWidth == 0) {
            return;
        }

        int maxWidth = 0; // Para calcular el ancho total necesario
        int maxHeight = 0; // Para calcular el alto total necesario

        for (int i = 0; i < getComponentCount(); i++) {
            if (getComponent(i) instanceof JPanel) {
                JPanel icon = (JPanel) getComponent(i);
                icon.setBounds(x, y, iconWidth, iconHeight);

                // Calcular la posición del siguiente ícono
                x += iconWidth + padding;
                if (x + iconWidth > panelWidth) { // Si se excede el ancho del panel, pasar a la siguiente fila
                    x = padding;
                    y += iconHeight + padding;
                }

                // Actualizar el tamaño máximo necesario
                maxWidth = Math.max(maxWidth, x + iconWidth);
                maxHeight = Math.max(maxHeight, y + iconHeight);
            }
        }

        // Ajustar el tamaño del DesktopPanel para que se ajuste al contenido
        setPreferredSize(new java.awt.Dimension(maxWidth, maxHeight));
        revalidate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibujar el rectángulo de selección
        if (selectionRectangle != null) {
            g.setColor(new Color(0, 120, 215, 100)); // Color azul translúcido
            g.fillRect(selectionRectangle.x, selectionRectangle.y, selectionRectangle.width, selectionRectangle.height);
            g.setColor(new Color(0, 120, 215)); // Borde azul
            g.drawRect(selectionRectangle.x, selectionRectangle.y, selectionRectangle.width, selectionRectangle.height);
        }
    }

    private void selectIconsInRectangle() {
        if (selectionRectangle == null) {
            return;
        }

        for (int i = 0; i < getComponentCount(); i++) {
            if (getComponent(i) instanceof JPanel) {
                JPanel icon = (JPanel) getComponent(i);
                Rectangle iconBounds = icon.getBounds();
                if (selectionRectangle.intersects(iconBounds)) {
                    icon.setBackground(new Color(0, 120, 215)); // Cambiar color de fondo para indicar selección
                    icon.setOpaque(true);
                } else {
                    icon.setBackground(null); // Restaurar fondo si no está seleccionado
                    icon.setOpaque(false);
                }
            }
        }

        repaint();
    }
}
