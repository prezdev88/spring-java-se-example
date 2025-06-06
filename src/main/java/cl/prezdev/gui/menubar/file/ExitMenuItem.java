package cl.prezdev.gui.menubar.file;

import javax.swing.JMenuItem;

import org.springframework.stereotype.Component;

import cl.prezdev.i18n.LocaleChangeListener;
import cl.prezdev.i18n.MessageService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ExitMenuItem extends JMenuItem implements LocaleChangeListener {

    private final transient MessageService messageService;

    @PostConstruct
    private void init() {
        setText(messageService.getMessage("menu.file.exit"));
        addActionListener(e -> System.exit(0));
        messageService.addLocaleChangeListener(this);
    }

    @Override
    public void onLocaleChange() {
        setText(messageService.getMessage("menu.file.exit"));
    }
}
