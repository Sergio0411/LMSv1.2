package com.company.view;

import com.company.Main;
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

        JMenuItem exit = new JMenuItem("Выход");
        exit.addActionListener(e -> System.exit(0));
        file.add(exit);
        return file;
    }

    private JMenu addMenu() {
        JMenu addMenu = new JMenu("Добавить");
        JMenuItem addStudent = new JMenuItem("Студент");
        addStudent.addActionListener(e -> Main.addStudentFrame.setVisible(true));
        JMenuItem addCourse = new JMenuItem("Курс");
        addCourse.addActionListener(e -> Main.addCourseFrame.setVisible(true));
        addMenu.add(addStudent);
        addMenu.add(addCourse);
        return addMenu;
    }

    private JMenu listsMenu() {
        JMenu lists = new JMenu("Списки");
        JMenuItem students = new JMenuItem("Студенты");
        students.addActionListener(e -> Main.mainFrame.showStudents());
        JMenuItem courses = new JMenuItem("Курсы");
        courses.addActionListener(e -> Main.mainFrame.showCourses());
        lists.add(students);
        lists.add(courses);
        return lists;
    }
}