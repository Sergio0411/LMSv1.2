package com.company.model;

import com.company.Main;
import com.company.repository.Repository;
import com.company.view.naoAccess.noAccessPanel;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.HashSet;

public class Teacher {
    static int id;
    String name;
    String surname;
    String email;
    String phone;

    private static int lastId;
    public static DefaultTableModel model = new DefaultTableModel();
    public static ArrayList<Teacher> allTeachers = new ArrayList<>();

    public Teacher(String name, String surname, String email, String phone) {
        setProperties(++lastId, name, surname, email, phone);
        Repository.addTeacher(String.valueOf(lastId), forRepository(name, surname, email, phone));
    }

    public Teacher(int id, String name, String surname, String email, String phone) {
        lastId = id;
        setProperties(id, name, surname,  email, phone);
    }

    public void setProperties(int id, String name, String surname, String email, String phone) {
        Teacher.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        allTeachers.add(this);
        model.addRow(new Object[]{id, name, surname, email, phone});
    }

    private static String[] forRepository(String name, String surname, String email, String phone) {
        name = "'" + name + "'";
        surname = "'" + surname + "'";
        email = "'" + email + "'";
        phone = "'" + phone + "'";
        return new String[]{name, surname, email, phone};
    }

    public static void update(int id, String name, String surname, String email, String phone) {
        if (!saveUser.user.equals("student")) {
            Repository.updateTeacher(id, forRepository(name, surname, email, phone));
        }else {
            noAccessPanel.noAccess.setVisible(true);
            noAccessPanel.error.setVisible(false);
            Main.noAccessFrame.setVisible(true);
            Main.noAccessFrame.pack();
        }
    }

    public static void delete(int id, int rowIndex) {
        if (!saveUser.user.equals("student")) {
            Repository.deleteTeacher(id);
            model.removeRow(rowIndex);
        }else {
            noAccessPanel.noAccess.setVisible(true);
            noAccessPanel.error.setVisible(false);
            Main.noAccessFrame.setVisible(true);
            Main.noAccessFrame.pack();
        }
    }

    public static ArrayList<Course> getCoursesById(int id) {
        return Enrollment.getCoursesByStudentId(id);
    }
    public static ArrayList<Teacher> getTeachersToEnrollByCourse(Course course) {
        HashSet<Teacher> set = new HashSet<>(allTeachers);
        HashSet<Teacher> enrolledStudents = new HashSet<>(Enrollment.getTeachersByCourse(course));
        set.removeAll(enrolledStudents);
        return new ArrayList<>(set);
    }

    public static String[] getTeachers(){
        String[] teachers = new String[allTeachers.size() + 1];
        teachers[0] = "Нет учителя";
        ArrayList<String> teacher = Repository.getAll("teacher");
        int t = 1;
        for(int i = 1; i < teachers.length; i++){
            teachers[i] = teacher.get(t) + " " + teacher.get(t+1);
            t += 5;
        }
        return teachers;
    }

    public static int[] returnIds(){
        ArrayList<String> accounts = Repository.getAll("teacher");
        int[] id = new int[accounts.size()/5];
        int t = 0;
        for (int i = 0; i < accounts.size(); i += 5) {
            id[t] = Integer.parseInt(accounts.get(i));
            t++;
        }
        return id;
    }

    public static int getId() {
        return Teacher.id;
    }

    public String getInfo() {
        return this.id + " " + this.name + " " + this.surname;
    }

    public ArrayList<Course> getCourses(boolean toEnroll) {
        if (toEnroll)
            return Course.getCoursesToEnrollByTeacher(this);
        else
            return Enrollment.getCoursesByTeacher(this);
    }

    public static Teacher getTeacherById(int id) {
        for (Teacher teacher : allTeachers) {
            if (teacher.getId() == id) {
                return teacher;
            }
        }
        return null;
    }

    public String toString() {
        return Teacher.id + " " + this.name;
    }

    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
}