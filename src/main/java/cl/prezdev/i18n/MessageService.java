package cl.prezdev.i18n;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MessageService {

    private final MessageSource messageSource;
    private Locale currentLocale = Locale.getDefault();

    public MessageService(MessageSource messageSource) {
        this.messageSource = messageSource;
        setLocale(Locale.ENGLISH);
    }

    public String getMessage(String key, Object... args) {
        return messageSource.getMessage(key, args, currentLocale);
    }

    public void setLocale(Locale locale) {
        this.currentLocale = locale;
    }

    public Locale getCurrentLocale() {
        return currentLocale;
    }
}