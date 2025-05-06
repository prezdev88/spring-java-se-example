package cl.prezdev.gui.menubar.file.language.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import org.springframework.stereotype.Component;

import cl.prezdev.i18n.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class SpanishOptionListener implements ActionListener {

    private final MessageService messageService;

    @Override
    public void actionPerformed(ActionEvent e) {
        messageService.setLocale(new Locale("es"));
    }
}