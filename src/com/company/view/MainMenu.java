package com.company.view;

import com.company.Main;
import com.company.view.delete.*;

import javax.swing.*;

public class MainMenu extends JMenuBar {
    public MainMenu() {
        add(fileMenu());
        add(addMenu());
        add(listsMenu());
    }

    private JMenu fileMenu() {
        JMenu file = new JMenu("Файл");
        JMenuItem settings = new JMenu("Настройки");
        JMenuItem colors = new JMenuItem("Сменить цвет");

        colors.addActionListener(e -> Main.mainFrame.changeColor());

        settings.add(colors);
        file.add(settings);

        JMenuItem deleteAcc = new JMenuItem("Удалить текущего пользователя");

        file.add(deleteAcc);
        deleteAcc.addActionListener(e -> {
            Main.deleteAccountFrame = new DeleteAccountFrame();
            Main.deleteAccountFrame.setVisible(true);
        });
        file.addSeparator();
        JMenuItem exit = new JMenuItem("Выход");
        exit.addActionListener(e -> {
            Main.addCourseFrame.setVisible(false);
            Main.mainFrame.setVisible(false);
            Main.loginFrame.setVisible(true);
        });
        file.add(exit);
        return file;
    }

    private JMenu addMenu() {
        JMenu addMenu = new JMenu("Добавить");
        JMenuItem addCourse = new JMenuItem("Курс");
        addCourse.addActionListener(e -> Main.addCourseFrame.setVisible(true));
        addMenu.add(addCourse);
        return addMenu;
    }

    private JMenu listsMenu() {
        JMenu lists = new JMenu("Списки");
        JMenuItem students = new JMenuItem("Студенты");
        students.addActionListener(e -> Main.mainFrame.showStudents());
        JMenuItem courses = new JMenuItem("Курсы");
        courses.addActionListener(e -> Main.mainFrame.showCourses());
        JMenuItem teachers = new JMenuItem("Учителя");
        teachers.addActionListener(e -> Main.mainFrame.showTeachers());
        lists.add(teachers);
        lists.add(students);
        lists.add(courses);
        return lists;
    }
}