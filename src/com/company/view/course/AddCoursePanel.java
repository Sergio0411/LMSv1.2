package com.company.view.course;

import com.company.Main;
import com.company.model.Course;
import com.company.view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCoursePanel extends JPanel {
    public AddCoursePanel(){

        setBackground(Color.GRAY);
        // Поля для ввода
        JTextField title = new JTextField(10);
        JTextField description = new JTextField(10);
        // Кнопка
        JButton addButton2 = new JButton("Добавить курс");
        // Если нажата кнопка:
        addButton2.addActionListener(e -> {
            if (!title.getText().isEmpty()) {
                new Course(title.getText(), description.getText());
                title.setText("");
                description.setText("");
                Main.addStudentFrame.setVisible(false);
            }
        });



        add(title);
        add(description);
        add(addButton2);
    }
}
