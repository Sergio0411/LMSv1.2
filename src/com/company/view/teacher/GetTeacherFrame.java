package com.company.view.teacher;

import com.company.model.Course;
import com.company.view.student.GetStudentPanel;

import javax.swing.*;
import java.awt.*;

public class GetTeacherFrame extends JFrame {
    public static GetTeacherPanel panel;
    public GetTeacherFrame(Course course, boolean toEnroll){
        setTitle("Студенты на курсе: " + course.getTitle());
        setSize(500, 500);
        setLocation(1920 / 2 - 250, 1080 / 2 - 250);
        setLayout(new FlowLayout());
        panel = new GetTeacherPanel(course, toEnroll);
        BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);

        add(panel);
        JButton button = new JButton();
    }
}
