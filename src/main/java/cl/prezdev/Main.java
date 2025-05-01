package cl.prezdev;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import cl.prezdev.gui.MainWindow;
import cl.prezdev.gui.UI;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@AllArgsConstructor
public class Main {

    private final MainWindow mainWindow;

	public static void main(String[] args) {
		UI.init();
		SpringApplication.run(Main.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
    public void onAppReady() {
        SwingUtilities.invokeLater(() -> mainWindow.setVisible(true));
    }
}
