package com.company.view.recover;

import com.company.Main;
import com.company.model.Account;
import com.company.repository.Repository;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RecoverPanel extends JPanel{
    public RecoverPanel(){
        JPanel error = new JPanel();
        JPanel noAcc = new JPanel();
        JPanel login = new JPanel();
        JPanel password1 = new JPanel();
        JPanel password2 = new JPanel();
        JTextField logText = new JTextField(20);
        JPasswordField passText1 = new JPasswordField(20);
        JPasswordField passText2 = new JPasswordField(20);
        JLabel log = new JLabel("         Login: ");
        JLabel pass1 = new JLabel("Password: ");
        JLabel pass2 = new JLabel("Password: ");
        JLabel errorText = new JLabel("Проверьте правильность написания логина и пароля и повторите попытку");
        JLabel noAccText = new JLabel("Такого аккаунта не существует");
        setBackground(Color.GRAY);
        JButton recover = new JButton("Восстановить");
        JButton OK = new JButton("OK");
        JPanel buttons = new JPanel();

        recover.addActionListener(e -> {
            boolean accountAvailability = Account.checkingAccountAvailability(logText.getText().hashCode());
            if (accountAvailability) {
                if (passText1.getText().equals(passText2.getText()) && !logText.getText().isEmpty() && !passText1.getText().isEmpty()) {
                    Account.update(logText.getText(), passText1.getText());
                    Main.recoverFrame.setVisible(false);
                    logText.setText("");
                    passText1.setText("");
                    passText2.setText("");
                } else {
                    Main.recoverFrame.setTitle("Не удалось восстановить пароль");
                    noAcc.setVisible(false);
                    login.setVisible(false);
                    password1.setVisible(false);
                    password2.setVisible(false);
                    recover.setVisible(false);
                    error.setVisible(true);
                    OK.setVisible(true);
                    Main.recoverFrame.pack();
                }
            }else {
                Main.recoverFrame.setTitle("Не удалось восстановить пароль");
                noAcc.setVisible(true);
                OK.setVisible(true);
                login.setVisible(false);
                password1.setVisible(false);
                password2.setVisible(false);
                recover.setVisible(false);
                error.setVisible(false);
                Main.recoverFrame.pack();
            }
        });
        OK.addActionListener(e ->{
            Main.recoverFrame.setTitle("Восстановление пароля");
            login.setVisible(true);
            password1.setVisible(true);
            password2.setVisible(true);
            recover.setVisible(true);
            error.setVisible(false);
            OK.setVisible(false);
            noAcc.setVisible(false);
            Main.recoverFrame.pack();
        });

        add(error);
        add(noAcc);
        add(login);
        add(password1);
        add(password2);

        error.add(errorText);
        noAcc.add(noAccText);
        login.add(log);
        login.add(logText);
        password1.add(pass1);
        password1.add(passText1);
        password2.add(pass2);
        password2.add(passText2);
        add(buttons);
        buttons.add(OK);
        buttons.add(recover);
        noAcc.setVisible(false);
        error.setVisible(false);
        OK.setVisible(false);
    }
}
