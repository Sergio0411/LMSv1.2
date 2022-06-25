package com.company.view.student;

import javax.swing.*;
import java.awt.*;

public class AddStudentFrame extends JFrame {
    public JPanel panel = new AddStudentPanel();
    public AddStudentFrame(){
        setTitle("Добавление студента");
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
