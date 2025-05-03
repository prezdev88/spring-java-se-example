package cl.prezdev.service.impl;

import java.awt.Image;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.springframework.stereotype.Service;

import cl.prezdev.service.UtilIconService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UtilIconServiceImpl implements UtilIconService {
    @Override
    public Icon load(String path) {
        URL resource = getClass().getResource(path);
        if (resource == null) {
            log.error("No se encontró el ícono: {}", path);
            return null;
        }
        ImageIcon original = new ImageIcon(resource);
        Image scaled = original.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }
}
