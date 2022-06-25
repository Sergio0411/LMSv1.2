package com.company.view.course;

import com.company.Main;
import com.company.model.Course;

import javax.swing.*;
import java.awt.*;

public class AddCoursePanel extends JPanel {
    public AddCoursePanel(){

        setBackground(Color.GRAY);
        JTextField titleTF = new JTextField(10);
        JTextField descriptionTF = new JTextField(10);
        JTextField teacherTF = new JTextField(10);

        JLabel titleL = new JLabel("  Название");
        JLabel descriptionL = new JLabel(" Описание");
        JLabel teacherL = new JLabel("    \t Учитель");

        JPanel titleP = new JPanel();
        JPanel descriptionP = new JPanel();
        JPanel teacherP = new JPanel();

        JButton addButton2 = new JButton("Добавить курс");
        addButton2.addActionListener(e -> {
            if (!titleTF.getText().isEmpty()) {
                new Course(titleTF.getText(), descriptionTF.getText(), teacherTF.getText());
                titleTF.setText("");
                descriptionTF.setText("");
                teacherTF.setText("");
                Main.addStudentFrame.setVisible(false);
            }
        });



        titleP.add(titleL);
        titleP.add(titleTF);
        descriptionP.add(descriptionL);
        descriptionP.add(descriptionTF);
        teacherP.add(teacherL);
        teacherP.add(teacherTF);

        add(titleP);
        add(descriptionP);
        add(teacherP);
        add(addButton2);
    }
}
