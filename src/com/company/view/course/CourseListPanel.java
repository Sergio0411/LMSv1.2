package com.company.view.course;

import com.company.model.Course;
import com.company.view.student.StudentPopupMenu;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CourseListPanel extends JPanel {
    public static JTable table = new JTable();

    public CourseListPanel(){
        Course.model.setColumnIdentifiers(new String[]{"ID", "Title", "Description"});
        table.setModel(Course.model);
        add(new JScrollPane(table));
        CoursePopupMenu popupMenu = new CoursePopupMenu();
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
