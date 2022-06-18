package com.company.view;

import com.company.model.Login;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    public LoginPanel(){
        JTextField login = new JTextField(30);
        JPasswordField password = new JPasswordField(30);
        login.setText("login");
        password.setText("password");
        setBackground(Color.GRAY);
        JButton addButton = new JButton("Войти");
        addButton.addActionListener(e -> {
            if (!login.getText().isEmpty() && !password.getText().isEmpty()) {
                Login.saveLog(login.getText(), password.getText());
            }
        });

        add(login);
        add(password);
        add(addButton);
    }
}
