package com.company.view.delete;

import javax.swing.*;
import java.awt.*;

public class DeleteAccountFrame extends JFrame {
    public DeleteAccountPanel panel = new DeleteAccountPanel();
    public DeleteAccountFrame(){
        setTitle("Delete Account to LMS");
        getContentPane().setBackground(Color.DARK_GRAY);
        setSize(380, 140);
        setLocation(795, 472);
        BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);

        add(panel);
        pack();
    }
}
