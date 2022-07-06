package com.company.view.course;

import com.company.model.Teacher;
import com.company.repository.Repository;

import javax.swing.*;
import java.awt.*;

public class AddCourseFrame extends JFrame {

    public static AddCoursePanel panel;
    public AddCourseFrame(){
        addPanel();
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
    public void update(){
        remove(panel);
        addPanel();
    }
    public void addPanel(){
        String[] teachers = Teacher.getTeachers();
        panel = new AddCoursePanel(teachers);
        setLayout(new FlowLayout());
        BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);
        add(panel);
        pack();
    }
}
