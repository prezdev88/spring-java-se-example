package cl.prezdev.gui.menubar.file.language;

import javax.swing.JMenu;
import javax.swing.ButtonGroup;

import org.springframework.stereotype.Component;

import cl.prezdev.i18n.LocaleChangeListener;
import cl.prezdev.i18n.MessageService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class LanguageMenu extends JMenu implements LocaleChangeListener {

    private final EnglishOption englishOption;
    private final SpanishOption spanishOption;
    private final transient MessageService messageService;

    @PostConstruct
    private void init() {
        setText(messageService.getMessage("menu.file.language"));

        ButtonGroup languageGroup = new ButtonGroup();

        languageGroup.add(englishOption);
        languageGroup.add(spanishOption);

        add(englishOption);
        add(spanishOption);

        messageService.addLocaleChangeListener(this);
    }

    @Override
    public void onLocaleChange() {
        setText(messageService.getMessage("menu.file.language"));
    }
}
