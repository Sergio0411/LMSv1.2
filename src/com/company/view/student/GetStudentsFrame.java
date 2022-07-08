package com.company.view.student;

import com.company.Main;
import com.company.model.Course;
import com.company.model.Enrollment;
import com.company.model.Student;
import com.company.model.saveUser;
import com.company.view.naoAccess.noAccessPanel;

import javax.swing.*;
import java.awt.*;

public class GetStudentsFrame extends JFrame {
    public GetStudentPanel panel;

    public GetStudentsFrame(Course course, boolean toEnroll) {
        if (!saveUser.user.equals("student")) {
            if (toEnroll) {
                setTitle("Зачислить на курс: " + course.getTitle());
            } else
                setTitle("Студенты на курсе: " + course.getTitle());
            setSize(500, 500);
            setLocation(1920 / 2 - 250, 1080 / 2 - 250);
            setLayout(new FlowLayout());
            panel = new GetStudentPanel(course, toEnroll);
            BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
            panel.setLayout(layout);

            add(panel);
            JButton button = new JButton();
            if (toEnroll) {
                button.setText("Зачислить");
                button.addActionListener(e -> {
                    int rowIndex = panel.table.getSelectedRow();
                    int student_id = Integer.parseInt(panel.table.getValueAt(rowIndex, 0).toString());
                    panel.model.removeRow(rowIndex);
                    new Enrollment(Student.getStudentById(student_id), course);
                });
            } else {
                button.setText("Отчислить");
                button.addActionListener(e -> {
                    int rowIndex = panel.table.getSelectedRow();
                    int student_id = Integer.parseInt(panel.table.getValueAt(rowIndex, 0).toString());
                    panel.model.removeRow(rowIndex);
                    Enrollment enrollment = Enrollment.getEnrollment(Student.getStudentById(student_id), course);
                    Enrollment.remove(enrollment.getId());
                });
            }
            panel.add(button);
            pack();
            setVisible(true);
        }else {
            noAccessPanel.noAccess.setVisible(true);
            noAccessPanel.error.setVisible(false);
            Main.noAccessFrame.setVisible(true);
            Main.noAccessFrame.pack();
        }
    }
}
