package com.company.view;

import javax.swing.*;
import java.awt.*;

public class DeleteAccountFrame extends JFrame {
    public DeleteAccountPanel panel = new DeleteAccountPanel();
    public int id;
    public DeleteAccountFrame(int id){
        this.id = id;
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
