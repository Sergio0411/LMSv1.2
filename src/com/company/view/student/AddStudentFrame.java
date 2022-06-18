package com.company.view.student;

import javax.swing.*;

public class AddStudentFrame extends JFrame {
    public AddStudentFrame(){
        setTitle("Добавление студента");
        setSize(300, 200);
        setLocation(810, 440);
        add(new AddStudentPanel());
        setVisible(false);
    }
}
