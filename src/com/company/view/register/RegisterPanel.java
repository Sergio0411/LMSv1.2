package com.company.view.register;

import com.company.Main;
import com.company.model.Account;
import com.company.model.Student;
import com.company.model.Teacher;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class RegisterPanel extends JPanel {
    public RegisterPanel(){
        JPanel error = new JPanel();
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

        JLabel errorText = new JLabel("Проверьте правильность написания логина и пароля и повторите попытку");
        JLabel log = new JLabel("         Login: ");
        JLabel pass1 = new JLabel("Password: ");
        JLabel pass2 = new JLabel("Password: ");
        JLabel EM = new JLabel("         Email: ");
        JLabel Phone = new JLabel("        Phone: ");
        JLabel nameL = new JLabel("         Name: ");
        JLabel surnameL = new JLabel("  Surname: ");

        JButton button = new JButton("Зарегистрироваться");
        JButton OK = new JButton("OK");
        setBackground(Color.GRAY);
        JPanel buttons = new JPanel();

        add(error);
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

        error.add(errorText);

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
        buttons.add(OK);

        error.setVisible(false);
        OK.setVisible(false);

        button.addActionListener(e -> {
            if (!(logText.getText().isEmpty() &&
                    pass1Text.getText().isEmpty() &&
                    pass2Text.getText().isEmpty() &&
                    EMText.getText().isEmpty() &&
                    PhoneText.getText().isEmpty() &&
                    nameText.getText().isEmpty() &&
                    surnameText.getText().isEmpty()) &&
                    Objects.equals(pass1Text.getText(),
                    pass2Text.getText()) &&
                    (teacher.isSelected() || student.isSelected())) {
                if (teacher.isSelected()){
                    new Teacher(nameText.getText(), surnameText.getText(), EMText.getText(), PhoneText.getText());
                    int id = Teacher.getId();
                    new Account(logText.getText(), pass1Text.getText(), "teacher", id);
                }
                if (student.isSelected()){
                    new Student(nameText.getText(), surnameText.getText(), EMText.getText(), PhoneText.getText());
                    int id = Student.getId();
                    new Account(logText.getText(), pass1Text.getText(), "student", id);
                }
                Main.registerFrame.setVisible(false);
                logText.setText("");
                pass1Text.setText("");
                pass2Text.setText("");
                EMText.setText("");
                PhoneText.setText("");
                nameText.setText("");
                surnameText.setText("");
                bg.clearSelection();
            }else {
                error.setVisible(true);
                OK.setVisible(true);
                login.setVisible(false);
                password1.setVisible(false);
                password2.setVisible(false);
                email.setVisible(false);
                phone.setVisible(false);
                name.setVisible(false);
                surname.setVisible(false);
                userSelection.setVisible(false);
                button.setVisible(false);
                Main.registerFrame.pack();
            }
        });
        OK.addActionListener(e -> {
            error.setVisible(false);
            OK.setVisible(false);
            login.setVisible(true);
            password1.setVisible(true);
            password2.setVisible(true);
            email.setVisible(true);
            phone.setVisible(true);
            name.setVisible(true);
            surname.setVisible(true);
            userSelection.setVisible(true);
            button.setVisible(true);
            Main.registerFrame.pack();
        });
    }
}
