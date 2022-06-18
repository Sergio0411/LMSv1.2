package com.company.view.student;

import com.company.model.Enrollment;
import com.company.model.Student;
import com.company.view.course.GetCoursesFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentPopupMenu extends JPopupMenu {
    public static MenuActionListener menuActionListener = new MenuActionListener();

    public StudentPopupMenu() {
        add(item("Сохранить", "save"));
        add(item("Курсы студента", "courses"));
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
            int rowIndex = StudentListPanel.table.getSelectedRow();
            int id = Integer.parseInt(StudentListPanel.table.getValueAt(rowIndex, 0).toString());
            String name = StudentListPanel.table.getValueAt(rowIndex, 1).toString();
            String surname = StudentListPanel.table.getValueAt(rowIndex, 2).toString();

            switch (e.getActionCommand()) {
                case "save" -> Student.update(id, name, surname);
                case "delete" -> {
                    Student.delete(id, rowIndex);
                    Enrollment.removeByStudentId(id);
                }
                case "courses" -> new GetCoursesFrame(Student.getStudentById(id), false);
                case "enroll" -> new GetCoursesFrame(Student.getStudentById(id), true);
                default -> System.out.println("Неизвестная команда");
            }
        }
    }


}
