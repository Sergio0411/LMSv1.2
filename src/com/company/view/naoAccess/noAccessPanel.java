package com.company.view.naoAccess;

import com.company.Main;

import javax.swing.*;

public class noAccessPanel extends JPanel {
    public static JLabel noAccess = new JLabel("Нет доступа к этой функции");
    public static JLabel error = new JLabel("Это не ваш аккаунт");
    public noAccessPanel(){
        JButton OK = new JButton("OK");

        JPanel panel = new JPanel();
        JPanel buttons = new JPanel();

        OK.addActionListener(e -> {
            Main.noAccessFrame.setVisible(false);
        });


        add(panel);
        add(buttons);

        panel.add(noAccess);
        panel.add(error);
        buttons.add(OK);
        error.setVisible(false);
    }
}
