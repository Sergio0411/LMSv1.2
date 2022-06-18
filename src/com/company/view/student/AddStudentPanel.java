package com.company.view.student;

import com.company.Main;
import com.company.model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddStudentPanel extends JPanel {
    public AddStudentPanel() {

        JTextField nameTF = new JTextField(10);
        JTextField surnameTF = new JTextField(10);
        setBackground(Color.GRAY);
        JButton addButton = new JButton("Добавить");
        addButton.addActionListener(e -> {
            if (!nameTF.getText().isEmpty() && !surnameTF.getText().isEmpty()) {
                new Student(nameTF.getText(), surnameTF.getText());
                nameTF.setText("");
                surnameTF.setText("");
                Main.addStudentFrame.setVisible(false);
            }
        });

        add(nameTF);
        add(surnameTF);
        add(addButton);
    }
}
