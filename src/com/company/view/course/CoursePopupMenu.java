package com.company.view.course;

import com.company.model.Account;
import com.company.model.Course;
import com.company.model.Enrollment;
import com.company.model.saveUser;
import com.company.view.student.GetStudentsFrame;
import com.company.view.teacher.GetTeacherFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CoursePopupMenu extends JPopupMenu {
    public static MenuActionListener menuActionListener = new MenuActionListener();

    public CoursePopupMenu() {
        add(item("Сохранить", "save"));
        add(item("Студенты", "students"));
        add(item("Зачислить", "enroll"));
        addSeparator();
        add(item("Удалить", "delete"));

    }

    private static JMenuItem item(String text, String command) {
        JMenuItem button = new JMenuItem(text);
        button.setActionCommand(command);
        button.addActionListener(menuActionListener);

        return button;
    }

    static class MenuActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int rowIndex = CourseListPanel.table.getSelectedRow();
            int id = Integer.parseInt(CourseListPanel.table.getValueAt(rowIndex, 0).toString());
            String title = CourseListPanel.table.getValueAt(rowIndex, 1).toString();
            String description = CourseListPanel.table.getValueAt(rowIndex, 2).toString();
            String teacher = CourseListPanel.table.getValueAt(rowIndex, 3).toString();

            switch (e.getActionCommand()) {
                case "save" -> Course.update(id, title, description, teacher);
                case "delete" -> {
                    Course.delete(id, rowIndex);
                    Enrollment.removeByCourseId(id);
                }
                case "students" -> new GetStudentsFrame(Course.getCourseById(id), false);
                case "enroll" -> new GetStudentsFrame(Course.getCourseById(id), true);
                default -> System.out.println("Неизвестная команда");
            }
        }
    }
}
