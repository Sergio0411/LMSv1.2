package com.company.view;

import javax.swing.*;
import java.awt.*;

public class RecoverFrame extends JFrame {
    public static RecoverPanel panel = new RecoverPanel();
    public RecoverFrame(){
        setTitle("Восстановление пароля");
        getContentPane().setBackground(Color.DARK_GRAY);
        setSize(330, 136);
        setLocation(795, 472);
        setLayout(new FlowLayout());
        BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);

        add(panel);
        setVisible(false);
        pack();
    }
}
