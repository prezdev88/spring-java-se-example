package cl.prezdev.service.impl;

import java.awt.Image;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.springframework.stereotype.Service;

import cl.prezdev.config.IconsConfig;
import cl.prezdev.service.IconService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class IconServiceImpl implements IconService {

    private final IconsConfig iconsConfig;

    @Override
    public Icon load(String propertyKey) {
        String iconPath = iconsConfig.getIconPath(propertyKey);
        URL resource = getClass().getResource(iconPath);
        if (resource == null) {
            log.error("No se encontró el ícono: {}", iconPath);
            return null;
        }
        ImageIcon original = new ImageIcon(resource);
        Image scaled = original.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }
}
