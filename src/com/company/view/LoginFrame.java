package com.company.view;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    public static LoginPanel loginPanel = new LoginPanel();
    public LoginFrame() {
        setTitle("Login to LMS");
        getContentPane().setBackground(Color.DARK_GRAY);
        setSize(400, 200);
        setLocation(760, 440);
        setLayout(new FlowLayout());
        BoxLayout layout = new BoxLayout(loginPanel, BoxLayout.Y_AXIS);
        loginPanel.setLayout(layout);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(loginPanel);
        setVisible(true);
    }
}