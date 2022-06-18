package com.company.view.course;

import javax.swing.*;

public class AddCourseFrame extends JFrame {
    public AddCourseFrame(){
        setTitle("Добавление курса");
        setSize(300, 200);
        setLocation(810, 440);
        add(new AddCoursePanel());
        setVisible(false);
    }
}
