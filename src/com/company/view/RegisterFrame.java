package com.company.view;

import javax.swing.*;
import java.awt.*;

public class RegisterFrame extends JFrame {
    public static RegisterPanel panel = new RegisterPanel();
    public RegisterFrame(){
        setTitle("Register to LMS");
        getContentPane().setBackground(Color.DARK_GRAY);
        setSize(380, 140);
        setLocation(795, 472);
        BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);

        add(panel);
        pack();
    }
}
