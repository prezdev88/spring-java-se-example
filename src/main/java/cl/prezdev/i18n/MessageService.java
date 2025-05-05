package cl.prezdev.i18n;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageSource messageSource;
    private Locale currentLocale = Locale.getDefault();
    private List<LocaleChangeListener> listeners;

    @PostConstruct
    private void init() {
        listeners = new ArrayList<>();
        setLocale(Locale.ENGLISH);
    }

    public void addLocaleChangeListener(LocaleChangeListener listener) {
        listeners.add(listener);
    }

    public String getMessage(String key, Object... args) {
        return messageSource.getMessage(key, args, currentLocale);
    }

    public void setLocale(Locale locale) {
        this.currentLocale = locale;
        notifyListeners();
    }

    private void notifyListeners() {
        for (LocaleChangeListener listener : listeners) {
            listener.onLocaleChange();
        }
    }

    public Locale getCurrentLocale() {
        return currentLocale;
    }
}