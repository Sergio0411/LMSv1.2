package com.company.view.course;

import com.company.Main;
import com.company.model.Course;
import com.company.model.Enrollment;
import com.company.model.Teacher;

import javax.swing.*;
import java.awt.*;

public class AddCoursePanel extends JPanel {
    public AddCoursePanel(String[] teachers){

        setBackground(Color.GRAY);
        JTextField titleTF = new JTextField(10);
        JTextField descriptionTF = new JTextField(10);
        JComboBox<String> teacherCB = new JComboBox<>(teachers);


        JLabel titleL = new JLabel("  Название");
        JLabel descriptionL = new JLabel(" Описание");
        JLabel teacherL = new JLabel("    \t Учитель");

        JPanel titleP = new JPanel();
        JPanel descriptionP = new JPanel();
        JPanel teacherP = new JPanel();
        JPanel buttons = new JPanel();

        JButton addButton = new JButton("Добавить курс");
        JButton updateButton = new JButton("Обновить");

        addButton.addActionListener(e -> {
            if (!titleTF.getText().isEmpty()) {
                new Course(titleTF.getText(), descriptionTF.getText(), teachers[teacherCB.getSelectedIndex()]);
                titleTF.setText("");
                descriptionTF.setText("");
                Main.addCourseFrame.setVisible(false);
                new Enrollment(Teacher.getTeacherById(teacherCB.getSelectedIndex() - 1), Main.course);
            }
        });

        updateButton.addActionListener(e -> {
            Main.addCourseFrame.update();
        });

        titleP.add(titleL);
        titleP.add(titleTF);
        descriptionP.add(descriptionL);
        descriptionP.add(descriptionTF);
        teacherP.add(teacherL);
        teacherP.add(teacherCB);
        buttons.add(updateButton);
        buttons.add(addButton);

        add(titleP);
        add(descriptionP);
        add(teacherP);
        add(buttons);
    }
}
