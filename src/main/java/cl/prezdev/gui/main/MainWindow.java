package cl.prezdev.gui.main;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import org.springframework.stereotype.Component;

import cl.prezdev.gui.menubar.AppMenuBar;
import cl.prezdev.gui.panel.MainSplitPane;
import cl.prezdev.gui.panel.top.TopPanel;
import cl.prezdev.i18n.LocaleChangeListener;
import cl.prezdev.i18n.MessageService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

import java.awt.BorderLayout;

@Component
@AllArgsConstructor
public class MainWindow extends JFrame implements LocaleChangeListener {

    private final MainSplitPane mainSplitPane;
    private final AppMenuBar appMenuBar;
    private final TopPanel topPanel;
    private final transient MessageService messageService;

    @PostConstruct
    public void init(){
        setTitle();
        setLayout(new BorderLayout());

        setJMenuBar(appMenuBar);
        add(topPanel, BorderLayout.NORTH);
        add(mainSplitPane, BorderLayout.CENTER);      

        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void onLocaleChange() {
        setTitle();
    }

    private void setTitle() {
        setTitle(messageService.getMessage("app.title"));
    }
}
