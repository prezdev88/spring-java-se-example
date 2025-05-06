package cl.prezdev.gui.menubar.help;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cl.prezdev.i18n.LocaleChangeListener;
import cl.prezdev.i18n.MessageService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AboutMenuItem extends JMenuItem implements LocaleChangeListener {

    @Value("${project.version}")
    private String version;

    private final transient MessageService messageService;

    @PostConstruct
    private void init() {
        setText(messageService.getMessage("menu.help.about"));
        addActionListener(e -> showAboutDialog());

        messageService.addLocaleChangeListener(this);
    }

    private void showAboutDialog() {
        String versionI18n = messageService.getMessage("version");
        JOptionPane.showMessageDialog(null, versionI18n + " " + version);
    }

    @Override
    public void onLocaleChange() {
        setText(messageService.getMessage("menu.help.about"));
    }

}
