package com.company.view;

import com.company.model.Login;
import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    public LoginPanel(){
        JPanel login = new JPanel();
        JPanel password = new JPanel();
        JTextField logText = new JTextField(20);
        JPasswordField passText = new JPasswordField(20);
        JLabel log = new JLabel("         Login: ");
        JLabel pass = new JLabel("Password: ");
        setBackground(Color.GRAY);
        JButton addButton = new JButton("Войти");
        addButton.addActionListener(e -> Login.saveLog(logText.getText(), passText.getText()));

        add(login);
        add(password);
        login.add(log);
        login.add(logText);
        password.add(pass);
        password.add(passText);
        add(addButton);
    }
}
