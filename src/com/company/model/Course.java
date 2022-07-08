package com.company.model;

import com.company.Main;
import com.company.repository.Repository;
import com.company.view.naoAccess.noAccessPanel;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.HashSet;

public class Course {

    private int id;
    String title;
    String description;
    String teacher;

    private static int lastId;
    public static DefaultTableModel model = new DefaultTableModel();
    public static ArrayList<Course> allCourses = new ArrayList<>();


    public Course(String title, String description, String teacher) {
        setProperties(++lastId, title, description, teacher);
        Repository.addCourse(String.valueOf(id), forRepository(title, description, teacher));
    }

    public Course(int id, String title, String description, String teacher) {
        lastId = id;
        setProperties(id, title, description, teacher);
    }

    private static String[] forRepository(String title, String description, String teacher) {
        title = "'" + title + "'";
        description = "'" + description + "'";
        teacher = "'" + teacher + "'";
        return new String[]{title, description, teacher};
    }

    public static ArrayList<Course> getCoursesToEnrollByStudent(Student student) {
        HashSet<Course> set = new HashSet<>(allCourses);
        HashSet<Course> enrolledByStudent = new HashSet<>(Enrollment.getCoursesByStudent(student));
        set.removeAll(enrolledByStudent);
        return new ArrayList<>(set);
    }

    public static ArrayList<Course> getCoursesToEnrollByTeacher(Teacher teacher) {
        HashSet<Course> set = new HashSet<>(allCourses);
        HashSet<Course> enrolledByTeacher = new HashSet<>(Enrollment.getCoursesByTeacher(teacher));
        set.removeAll(enrolledByTeacher);
        return new ArrayList<>(set);
    }

    public void setProperties(int id, String title, String description, String teacher) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.teacher = teacher;
        allCourses.add(this);
        model.addRow(new Object[]{this.id, title, description, teacher});
        Main.course = this;
    }

    public static void update(int id, String title, String description, String teacher) {
        if (!saveUser.user.equals("student")) {
            Repository.updateCourse(id, forRepository(title, description, teacher));
        }else {
            noAccessPanel.noAccess.setVisible(true);
            noAccessPanel.error.setVisible(false);
            Main.noAccessFrame.setVisible(true);
            Main.noAccessFrame.pack();
        }
    }

    public static void delete(int id, int rowIndex) {
        if (!saveUser.user.equals("student")) {
            Repository.deleteCourse(id);
            model.removeRow(rowIndex);
        }else{
            noAccessPanel.noAccess.setVisible(true);
            noAccessPanel.error.setVisible(false);
            Main.noAccessFrame.setVisible(true);
            Main.noAccessFrame.pack();
        }
    }

    public static void setTeacher(String teacher){

    }

    public String getInfo() {
        return this.id + " " + this.title + "\n" + this.description;
    }


    public int getId() {
        return id;
    }

    public ArrayList<Student> getStudents(boolean toEnroll) {
        if (toEnroll)
            return Student.getStudentsToEnrollByCourse(this);
        else
            return Enrollment.getStudentsByCourse(this);
    }
    public ArrayList<Teacher> getTeacher(boolean toEnroll){
        if (toEnroll)
            return Teacher.getTeachersToEnrollByCourse(this);
        else
            return Enrollment.getTeachersByCourse(this);
    }
    public String toString() {
        return this.id + " " + this.title;
    }

    public static Course getCourseById(int id) {
        for (Course course : allCourses) {
            if (course.getId() == id) {
                return course;
            }
        }
        return null;

    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}

