package com.company.view;

import com.company.Main;
import com.company.model.Account;
import com.company.model.Student;
import com.company.model.Teacher;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class RegisterPanel extends JPanel {
    public RegisterPanel(){
        JPanel login = new JPanel();
        JPanel password1 = new JPanel();
        JPanel password2 = new JPanel();
        JPanel email = new JPanel();
        JPanel phone = new JPanel();
        JPanel name = new JPanel();
        JPanel surname = new JPanel();
        JPanel userSelection = new JPanel();
        JTextField logText = new JTextField(20);
        JPasswordField pass1Text = new JPasswordField(20);
        JPasswordField pass2Text = new JPasswordField(20);
        JTextField EMText = new JTextField(20);
        JTextField PhoneText = new JTextField(20);
        JTextField nameText = new JTextField(20);
        JTextField surnameText = new JTextField(20);
        JRadioButtonMenuItem student = new JRadioButtonMenuItem("Студент");
        JRadioButtonMenuItem teacher = new JRadioButtonMenuItem("Учитель");
        ButtonGroup bg = new ButtonGroup();
        bg.add(student);
        bg.add(teacher);



        JLabel log = new JLabel("         Login: ");
        JLabel pass1 = new JLabel("Password: ");
        JLabel pass2 = new JLabel("Password: ");
        JLabel EM = new JLabel("         Email: ");
        JLabel Phone = new JLabel("        Phone: ");
        JLabel nameL = new JLabel("         Name: ");
        JLabel surnameL = new JLabel("  Surname: ");

        JButton button = new JButton("Зарегистрироваться");
        setBackground(Color.GRAY);
        JPanel buttons = new JPanel();


        add(login);
        add(password1);
        add(password2);
        add(email);
        add(phone);
        add(name);
        add(surname);
        add(userSelection);
        add(buttons);

        userSelection.add(student);
        userSelection.add(teacher);

        login.add(log);
        login.add(logText);

        email.add(EM);
        email.add(EMText);

        phone.add(Phone);
        phone.add(PhoneText);

        password1.add(pass1);
        password1.add(pass1Text);
        password2.add(pass2);
        password2.add(pass2Text);

        name.add(nameL);
        name.add(nameText);
        surname.add(surnameL);
        surname.add(surnameText);

        buttons.add(button);

        button.addActionListener(e -> {
            Main.registerFrame.setVisible(false);
            if (!(logText.getText().isEmpty() &&
                    pass1Text.getText().isEmpty() &&
                    pass2Text.getText().isEmpty() &&
                    EMText.getText().isEmpty() &&
                    PhoneText.getText().isEmpty() &&
                    nameText.getText().isEmpty() &&
                    surnameText.getText().isEmpty()) && Objects.equals(pass1Text.getText(), pass2Text.getText())) {
                new Account(logText.getText(), pass1Text.getText());
                if (teacher.isSelected()){
                    new Teacher(nameText.getText(), surnameText.getText(), EMText.getText(), PhoneText.getText());
                }
                if (student.isSelected()){
                    new Student(nameText.getText(), surnameText.getText(), EMText.getText(), PhoneText.getText());
                }
            }
        });
    }
}
