package com.company.view.student;

import com.company.Main;
import com.company.model.Student;

import javax.swing.*;
import java.awt.*;

public class AddStudentPanel extends JPanel {
    public AddStudentPanel() {

        JTextField nameTF = new JTextField(10);
        JTextField surnameTF = new JTextField(10);
        JTextField emailTF = new JTextField(10);
        JTextField phoneTF = new JTextField(10);

        JLabel nameL = new JLabel("          Имя");
        JLabel surnameL = new JLabel("Фамилия");
        JLabel emailL = new JLabel("       Почта");
        JLabel phoneL = new JLabel(" Телефон");

        JPanel nameP = new JPanel();
        JPanel surnameP = new JPanel();
        JPanel emailP = new JPanel();
        JPanel phoneP = new JPanel();

        setBackground(Color.GRAY);
        JButton addButton = new JButton("Добавить");
        addButton.addActionListener(e -> {
            if (!nameTF.getText().isEmpty() && !surnameTF.getText().isEmpty()) {
                new Student(nameTF.getText(), surnameTF.getText(), emailTF.getText(), phoneTF.getText());
                nameTF.setText("");
                surnameTF.setText("");
                emailTF.setText("");
                phoneTF.setText("");
                Main.addStudentFrame.setVisible(false);
            }
        });

        nameP.add(nameL);
        nameP.add(nameTF);
        surnameP.add(surnameL);
        surnameP.add(surnameTF);
        emailP.add(emailL);
        emailP.add(emailTF);
        phoneP.add(phoneL);
        phoneP.add(phoneTF);

        add(nameP);
        add(surnameP);
        add(emailP);
        add(phoneP);
        add(addButton);
    }
}
