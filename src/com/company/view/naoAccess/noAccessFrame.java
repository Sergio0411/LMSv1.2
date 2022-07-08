package com.company.view.naoAccess;

import javax.swing.*;
import java.awt.*;

public class noAccessFrame extends JFrame {
    public static noAccessPanel panel = new noAccessPanel();
    public noAccessFrame(){
        setTitle("ERROR");
        setLocation(810, 440);
        add(panel);
        setBackground(Color.darkGray);
        setLayout(new FlowLayout());
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        pack();
    }
}
