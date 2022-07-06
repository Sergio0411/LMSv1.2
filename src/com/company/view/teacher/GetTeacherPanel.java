package com.company.view.teacher;

import com.company.model.Course;
import com.company.model.Teacher;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class GetTeacherPanel extends JPanel{
    public JTable table = new JTable();
    public DefaultTableModel model = new DefaultTableModel();
    public GetTeacherPanel(Course course, boolean toEnroll) {
        model.setColumnIdentifiers(new String[]{"ID", "Name", "Surname"});

        for (Teacher teacher : course.getTeacher(toEnroll)){
            model.addRow(new Object[] {teacher.getId(), teacher.getName(), teacher.getSurname()});
        }
        table.setModel(model);
        add(new JScrollPane(table));
    }
}
