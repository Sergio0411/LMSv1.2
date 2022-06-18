package com.company.view.student;

import com.company.model.Course;
import com.company.model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class GetStudentPanel extends JPanel {
    public JTable table = new JTable();
    public DefaultTableModel model = new DefaultTableModel();
    public GetStudentPanel(Course course, boolean toEnroll) {
        model.setColumnIdentifiers(new String[]{"ID", "Name", "Surname"});

        for (Student student : course.getStudents(toEnroll)){
            model.addRow(new Object[] {student.getId(), student.getName(), student.getSurname()});
        }
        table.setModel(model);
        add(new JScrollPane(table));
    }
}
