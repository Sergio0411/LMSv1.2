package com.company.view.login;

import com.company.Main;
import com.company.model.Account;

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
        JButton entrance = new JButton("Войти");
        JButton register = new JButton("Регистрация");
        JButton recover = new JButton("Восстановить");
        JPanel buttons = new JPanel();
        entrance.addActionListener(e -> {
            Account.saveLog(logText.getText(), passText.getText());
            logText.setText("");
            passText.setText("");
            Main.recoverFrame.setVisible(false);
            Main.registerFrame.setVisible(false);
        });
        register.addActionListener(e -> Main.registerFrame.setVisible(true));
        recover.addActionListener(e -> Main.recoverFrame.setVisible(true));

        add(login);
        add(password);
        login.add(log);
        login.add(logText);
        password.add(pass);
        password.add(passText);
        add(buttons);
        buttons.add(entrance);
        buttons.add(register);
        buttons.add(recover);
    }
}
