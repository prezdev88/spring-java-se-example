package cl.prezdev.gui.menubar.file.language;

import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;

import org.springframework.stereotype.Component;

import cl.prezdev.i18n.MessageService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class EnglishOption extends JCheckBoxMenuItem {

    private final transient MessageService messageService;
    private final transient ActionListener englishOptionListener;

    @PostConstruct
    private void init() {
        setText(messageService.getMessage("menu.file.language.english"));
        setSelected(true);
        addActionListener(englishOptionListener);
    }
}
