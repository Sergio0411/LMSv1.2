package com.company.view;

import com.company.view.course.CourseListPanel;
import com.company.view.student.StudentListPanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    Color[] colors = new Color[]{Color.DARK_GRAY, Color.WHITE};
    private int colorIndex = 0;

    public static StudentListPanel studentsPanel = new StudentListPanel();
    public static CourseListPanel coursePanel = new CourseListPanel();

    public void showCourses() {
        remove(studentsPanel);
        add(coursePanel);
        pack();
    }

    public void showStudents() {
        remove(coursePanel);
        add(studentsPanel);
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
    }

    public void changeColor() {
        colorIndex = (colorIndex + 1) % colors.length;
        getContentPane().setBackground(colors[colorIndex]);
    }
}
