package cl.prezdev.gui.menubar.file.language;

import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;

import org.springframework.stereotype.Component;

import cl.prezdev.i18n.MessageService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class SpanishOption extends JCheckBoxMenuItem {

    private final transient MessageService messageService;
    private final transient ActionListener spanishOptionListener;

    @PostConstruct
    private void init() {
        setText(messageService.getMessage("menu.file.language.spanish"));
        setSelected(false);
        addActionListener(spanishOptionListener);
    }
}