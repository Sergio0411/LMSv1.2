package com.company.view;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    public static LoginPanel loginPanel = new LoginPanel();
    public LoginFrame() {
        setTitle("Login to LMS");
        getContentPane().setBackground(Color.DARK_GRAY);
        setSize(380, 140);
        setLocation(795, 472);
        setLayout(new FlowLayout());
        BoxLayout layout = new BoxLayout(loginPanel, BoxLayout.Y_AXIS);
        loginPanel.setLayout(layout);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(loginPanel);
        setVisible(true);
        pack();
    }
}