package cl.prezdev.gui.menubar.file;

import javax.swing.JMenu;

import org.springframework.stereotype.Component;

import cl.prezdev.gui.menubar.file.language.LanguageMenu;
import cl.prezdev.i18n.LocaleChangeListener;
import cl.prezdev.i18n.MessageService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class FileMenu extends JMenu implements LocaleChangeListener {

    private final LanguageMenu languageMenu;
    private final ExitMenuItem exitMenuItem;
    private final transient MessageService messageService;

    @PostConstruct
    private void init() {
        setText(messageService.getMessage("menu.file"));

        add(languageMenu);
        addSeparator();
        add(exitMenuItem);

        messageService.addLocaleChangeListener(this);
    }

    @Override
    public void onLocaleChange() {
        setText(messageService.getMessage("menu.file"));
    }
}
