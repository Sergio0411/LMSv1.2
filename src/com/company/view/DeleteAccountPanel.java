package com.company.view;

import com.company.Main;
import com.company.model.Account;

import javax.swing.*;
import java.awt.*;

public class DeleteAccountPanel extends JPanel {
    public DeleteAccountPanel(){
        int id = Account.toDelete();
        JPanel login = new JPanel();
        JPanel password = new JPanel();
        JTextField logText = new JTextField(20);
        JPasswordField passText = new JPasswordField(20);
        JLabel log = new JLabel("         Login: ");
        JLabel pass = new JLabel("Password: ");
        setBackground(Color.GRAY);
        JButton entrance = new JButton("Удалить");
        JPanel buttons = new JPanel();
        entrance.addActionListener(e -> {
            Account.delete(id);
            logText.setText("");
            passText.setText("");
            Main.mainFrame.setVisible(false);
            Main.loginFrame.setVisible(true);
            Main.deleteAccountFrame.setVisible(false);
        });

        add(login);
        add(password);
        login.add(log);
        login.add(logText);
        password.add(pass);
        password.add(passText);
        add(buttons);
        buttons.add(entrance);
    }
}
