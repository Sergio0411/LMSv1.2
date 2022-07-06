package com.company.view;

import com.company.view.student.StudentListPanel;
import com.company.view.course.CourseListPanel;
import com.company.view.teacher.TeacherListPanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    Color[] colors = new Color[]{Color.DARK_GRAY, Color.WHITE};
    private int colorIndex = 0;

    public static StudentListPanel studentsPanel = new StudentListPanel();
    public static CourseListPanel coursePanel = new CourseListPanel();
    public static TeacherListPanel teacherPanel = new TeacherListPanel();

    public void showCourses() {
        remove(studentsPanel);
        remove(teacherPanel);
        add(coursePanel);
        pack();
    }

    public void showStudents() {
        remove(coursePanel);
        remove(teacherPanel);
        add(studentsPanel);
        pack();
    }

    public void showTeachers() {
        remove(coursePanel);
        remove(studentsPanel);
        add(teacherPanel);
        pack();
    }

    public MainFrame() {
        setTitle("LMS");
        setJMenuBar(new MainMenu());
        getContentPane().setBackground(Color.DARK_GRAY);
        setSize(500, 510);
        setLocation(710, 290);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(studentsPanel);
        pack();
    }

    public void changeColor() {
        colorIndex = (colorIndex + 1) % colors.length;
        getContentPane().setBackground(colors[colorIndex]);
    }
}
