package com.company.model;

import com.company.repository.Repository;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.HashSet;

public class Student {
    private static int id;
    String name;
    String surname;
    String email;
    String phone;

    private static int lastId;
    public static DefaultTableModel model = new DefaultTableModel();
    public static ArrayList<Student> allStudents = new ArrayList<>();

    public Student(String name, String surname, String email, String phone) {
        setProperties(++lastId, name, surname, email, phone);
        Repository.addStudent(String.valueOf(lastId), forRepository(name, surname, email, phone));
    }

    public Student(int id, String name, String surname, String email, String phone) {
        lastId = id;
        setProperties(id, name, surname,  email, phone);
    }

    private static String[] forRepository(String name, String surname, String email, String phone) {
        name = "'" + name + "'";
        surname = "'" + surname + "'";
        email = "'" + email + "'";
        phone = "'" + phone + "'";
        return new String[]{name, surname, email, phone};
    }

    public void add(int id, String name, String surname, String email, String phone){
        Repository.addStudent(id + "", forRepository(name, surname, email, phone));
    }

    public static void update(int id, String name, String surname, String email, String phone) {
        Repository.updateStudent(id, forRepository(name, surname, email, phone));
    }

    public static void delete(int id, int rowIndex) {
        Repository.deleteStudent(id);
        model.removeRow(rowIndex);
    }

    public static ArrayList<Course> getCoursesById(int id) {
        return Enrollment.getCoursesByStudentId(id);
    }

    public static ArrayList<Student> getStudentsToEnrollByCourse(Course course) {
        HashSet<Student> set = new HashSet<>(allStudents);
        HashSet<Student> enrolledStudents = new HashSet<Student>(Enrollment.getStudentsByCourse(course));
        set.removeAll(enrolledStudents);
        return new ArrayList<>(set);
    }

    public void setProperties(int id, String name, String surname, String email, String phone) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        allStudents.add(this);
        model.addRow(new Object[]{id, name, surname, email, phone});
    }

    public static int getId() {
        return Student.id;
    }

    public String getInfo() {
        return this.id + " " + this.name + " " + this.surname;
    }

    public ArrayList<Course> getCourses(boolean toEnroll) {
        if (toEnroll)
            return Course.getCoursesToEnrollByStudent(this);
        else
            return Enrollment.getCoursesByStudent(this);
    }

    public ArrayList<Integer> getMarks() {
        ArrayList<Integer> marks = new ArrayList<>();
        for (Course course : this.getCourses(false)) {
            marks.add(AcademicPerformance.getMarkByStudentAndCourse(this, course));
        }
        return marks;
    }

    public static int[] returnIds(){
        ArrayList<String> accounts = Repository.getAll("student");
        int[] id = new int[accounts.size()/5];
        int t = 0;
        for (int i = 0; i < accounts.size(); i += 5) {
            id[t] = Integer.parseInt(accounts.get(i));
            t++;
        }
        return id;
    }


    public static Student getStudentById(int id) {
        for (Student student : allStudents) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    public String toString() {
        return this.id + " " + this.name;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}

