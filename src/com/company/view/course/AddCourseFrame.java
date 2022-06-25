package com.company.view.course;

import javax.swing.*;
import java.awt.*;

public class AddCourseFrame extends JFrame {
    public JPanel panel = new AddCoursePanel();
    public AddCourseFrame(){
        setTitle("Добавление курса");
        getContentPane().setBackground(Color.DARK_GRAY);
        setLocation(810, 440);
        setLayout(new FlowLayout());
        BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);
        add(panel);
        setVisible(false);
        pack();
    }
}
