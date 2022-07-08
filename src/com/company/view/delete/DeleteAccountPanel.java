package com.company.view.delete;

import com.company.Main;
import com.company.model.Account;
import com.company.view.naoAccess.noAccessFrame;
import com.company.view.naoAccess.noAccessPanel;

import javax.swing.*;
import java.awt.*;

public class DeleteAccountPanel extends JPanel {
    public DeleteAccountPanel(){
        JPanel login = new JPanel();
        JPanel password = new JPanel();
        JTextField logText = new JTextField(20);
        JPasswordField passText = new JPasswordField(20);
        JLabel log = new JLabel("         Login: ");
        JLabel pass = new JLabel("Password: ");
        setBackground(Color.GRAY);
        JButton delete = new JButton("Удалить");
        JPanel buttons = new JPanel();
        delete.addActionListener(e -> {
            int id = Account.getIdByUser(logText.getText(), passText.getText());
            if (id == -8942736) {
                Main.noAccessFrame = new noAccessFrame();
                Main.noAccessFrame.setVisible(true);
                noAccessPanel.error.setVisible(true);
                noAccessPanel.noAccess.setVisible(false);
            }else {
                Account.deleteUser(id);
                Account.delete(id);
                logText.setText("");
                passText.setText("");
                Main.deleteAccountFrame.setVisible(false);
                Main.loginFrame.setVisible(true);
                Main.mainFrame.setVisible(false);
            }
        });

        add(login);
        add(password);
        login.add(log);
        login.add(logText);
        password.add(pass);
        password.add(passText);
        add(buttons);
        buttons.add(delete);
    }
}
