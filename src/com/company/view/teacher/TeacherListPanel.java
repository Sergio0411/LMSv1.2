package com.company.view.teacher;

import com.company.model.Student;
import com.company.model.Teacher;
import com.company.view.student.StudentPopupMenu;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TeacherListPanel extends JPanel{
    public static JTable table = new JTable();
    public TeacherListPanel(){
        Teacher.model.setColumnIdentifiers(new String[]{"ID", "Имя", "Фамилия", "Почта", "Телефон"});
        table.setModel(Teacher.model);
        add(new JScrollPane(table));
        TeacherPopupMenu popupMenu = new TeacherPopupMenu();
        setComponentPopupMenu(popupMenu);
        table.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (e.getButton() == 3) { // 1 - ЛКМ, 2 - СРМ, 3 - ПКМ
                            int currentRow = table.rowAtPoint(e.getPoint());
                            table.setRowSelectionInterval(currentRow, currentRow);
                            popupMenu.show(table, e.getX(), e.getY());
                        }
                    }
                }
        );
    }
}
