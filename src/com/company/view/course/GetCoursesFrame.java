package com.company.view.course;

import com.company.model.Course;
import com.company.model.Enrollment;
import com.company.model.Student;

import javax.swing.*;
import java.awt.*;

public class GetCoursesFrame extends JFrame {
    public GetCoursesPanel panel;

    public GetCoursesFrame(Student student, boolean toEnroll) {
        if (toEnroll) {
            setTitle("Зачислить студента: " + student.getName() + " " + student.getSurname());
        } else
            setTitle("Курсы студента: " + student.getName() + " " + student.getSurname());
        setSize(500, 500);
        setLocation(1920 / 2 - 250, 1080 / 2 - 250);
        setLayout(new FlowLayout());
        panel = new GetCoursesPanel(student, toEnroll);
        BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);

        add(panel);
        JButton button = new JButton();
        if (toEnroll) {
            button.setText("Записать");
            button.addActionListener(e -> {
                int rowIndex = panel.table.getSelectedRow();
                int id = Integer.parseInt(panel.table.getValueAt(rowIndex, 0).toString());
                panel.model.removeRow(rowIndex);
                new Enrollment(student, Course.getCourseById(id));
            });
        } else {
            button.setText("Отчислить");
            button.addActionListener(e -> {
                int rowIndex = panel.table.getSelectedRow();
                int courseId = Integer.parseInt(panel.table.getValueAt(rowIndex, 0).toString());
                panel.model.removeRow(rowIndex);
                Enrollment enrollment = Enrollment.getEnrollment(student, Course.getCourseById(courseId));
                Enrollment.remove(enrollment.getId());
            });
        }
        panel.add(button);
        pack();
        setVisible(true);
    }
}
