package com.company.view.teacher;

import com.company.model.Course;
import com.company.model.Enrollment;
import com.company.model.Teacher;
import com.company.view.course.CourseListPanel;
import com.company.view.course.GetCoursesFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeacherPopupMenu extends JPopupMenu {
    public static TeacherPopupMenu.MenuActionListener menuActionListener = new TeacherPopupMenu.MenuActionListener();

    public TeacherPopupMenu() {
        add(item("Сохранить", "save"));
        add(item("Курсы учителя", "courses"));
        add(item("Записать на курс", "enroll"));
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
            int rowIndex = TeacherListPanel.table.getSelectedRow();
            int id = Integer.parseInt(TeacherListPanel.table.getValueAt(rowIndex, 0).toString());
            String name = TeacherListPanel.table.getValueAt(rowIndex, 1).toString();
            String surname = TeacherListPanel.table.getValueAt(rowIndex, 2).toString();
            String email = TeacherListPanel.table.getValueAt(rowIndex, 3).toString();
            String phone = TeacherListPanel.table.getValueAt(rowIndex, 4).toString();

            switch (e.getActionCommand()) {
                case "save" -> Teacher.update(id, name, surname, email, phone);
                case "enroll" -> new GetCoursesFrame(Teacher.getTeacherById(id), true);
                case "courses" -> new GetCoursesFrame(Teacher.getTeacherById(id), false);
                case "delete" -> {
                    Teacher.delete(id, rowIndex);
                    Enrollment.removeByTeacherId(id);
                    CourseListPanel.columnsName[3] = "Нет учителя";
                }
                default -> System.out.println("Неизвестная команда");
            }
        }
    }
}
