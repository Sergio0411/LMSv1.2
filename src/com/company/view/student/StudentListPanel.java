package com.company.view.student;

import com.company.model.Student;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StudentListPanel extends JPanel {

    public static JTable table = new JTable();
    public StudentListPanel(){
        Student.model.setColumnIdentifiers(new String[]{"ID", "Name", "Surname"});
        table.setModel(Student.model);
        add(new JScrollPane(table));
        StudentPopupMenu popupMenu = new StudentPopupMenu();
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
