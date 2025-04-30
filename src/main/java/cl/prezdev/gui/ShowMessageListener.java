package cl.prezdev.gui;

import javax.swing.JOptionPane;

import org.springframework.stereotype.Component;

import cl.prezdev.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Slf4j
@Component
@AllArgsConstructor
public class ShowMessageListener implements ActionListener {

    private final UserService userService;

    @Override
    public void actionPerformed(ActionEvent e) {
        log.info("¡Hola! Este es un mensaje.");
        JOptionPane.showMessageDialog(null, userService.getUser(100));
    }
}