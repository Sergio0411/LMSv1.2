package com.company.model;

import com.company.repository.Repository;

import java.util.ArrayList;
import java.util.HashSet;

public class Enrollment {
    int id;
    Student student;
    Course course;
    Teacher teacher;

    static int lastID;
    public static ArrayList<Enrollment> allEnrollmentsS = new ArrayList<>();
    public static ArrayList<Enrollment> allEnrollmentsT = new ArrayList<>();


    public Enrollment(Student student, Course course) {
        this.id = ++lastID;
        this.student = student;
        this.course = course;
        allEnrollmentsS.add(this);
        Repository.addEnrollment(id + "", "" + student.getId(), "" + course.getId(), "'student'");
    }

    public Enrollment(Teacher teacher, Course course) {
        this.id = ++lastID;
        this.teacher = teacher;
        this.course = course;
        allEnrollmentsT.add(this);
        Repository.addEnrollment(id + "", "" + teacher.getId(), "" + course.getId(), "'teacher'");
    }

    public Enrollment(int id, int userId, int courseId, String tableName) {
        this.id = lastID = id;
        this.course = Course.getCourseById(courseId);
        switch (tableName) {
            case "student" -> {
                int studentId = userId;
                this.student = Student.getStudentById(studentId);
                allEnrollmentsS.add(this);
            }
            case "teacher" ->{
                int teacherId = userId;
                this.teacher = Teacher.getTeacherById(teacherId);
                allEnrollmentsT.add(this);
            }
        }
    }


    public static ArrayList<Student> getStudentsByCourse(Course course) {
        ArrayList<Student> res = new ArrayList<>();
        for (Enrollment ce : allEnrollmentsS) {
            if (course.getId() == ce.course.getId()) {
                res.add(ce.student);
            }
        }
        return res;
    }

    public static ArrayList<Teacher> getTeachersByCourse(Course course) {
        ArrayList<Teacher> res = new ArrayList<>();
        for (Enrollment ce : allEnrollmentsS) {
            if (course.getId() == ce.course.getId()) {
                res.add(ce.teacher);
            }
        }
        return res;
    }

    public static ArrayList<Course> getCoursesByStudent(Student student) {
        ArrayList<Course> res = new ArrayList<>();
        for (Enrollment ce : allEnrollmentsS) {
            if (student.getId() == ce.student.getId()) {
                res.add(ce.course);
            }
        }
        return res;
    }

    public static ArrayList<Course> getCoursesByTeacher(Teacher teacher) {
        ArrayList<Course> res = new ArrayList<>();
        for (Enrollment ce : allEnrollmentsT) {
            if (teacher.getId() == ce.teacher.getId()) {
                res.add(ce.course);
            }
        }
        return res;
    }

    public static Enrollment getEnrollment(Student st, Course c) {
        for (Enrollment ce : allEnrollmentsS) {
            if (ce.student.getId() == st.getId() && ce.course.getId() == c.getId()) {
                return ce;
            }
        }
        return new Enrollment(st, c);
    }
    public static Enrollment getEnrollment(Teacher teacher, Course c){
        for (Enrollment ce : allEnrollmentsS) {
            if (ce.teacher.getId() == teacher.getId() && ce.course.getId() == c.getId()) {
                return ce;
            }
        }
        return new Enrollment(teacher, c);
    }

    public static ArrayList<Course> getCoursesByStudentId(int id) {
        ArrayList<Course> res = new ArrayList<>();
        for (Enrollment ce : allEnrollmentsS) {
            if (id == ce.teacher.getId()) {
                res.add(ce.course);
            }
        }
        return res;
    }

    public static void remove(int id) {
        allEnrollmentsS.removeIf(ce -> id == ce.id);
        Repository.deleteEnrollment(id);
    }

    public static void removeByStudentId(int id) {
        HashSet<Enrollment> toRemove = new HashSet<>();
        for (Enrollment ce : allEnrollmentsS) {
            if (id == ce.student.getId()) {
                toRemove.add(ce);
                Repository.deleteEnrollment(ce.getId());
            }
        }
        allEnrollmentsS.removeAll(toRemove);
    }

    public static void removeByTeacherId(int id) {
        HashSet<Enrollment> toRemove = new HashSet<>();
        for (Enrollment ce : allEnrollmentsT) {
            if (id == ce.teacher.getId()) {
                toRemove.add(ce);
                Repository.deleteEnrollment(ce.getId());
            }
        }
        allEnrollmentsT.removeAll(toRemove);
    }

    public static void removeByCourseId(int id) {
        HashSet<Enrollment> toRemove = new HashSet<>();
        for (Enrollment ce : allEnrollmentsS) {
            if (id == ce.course.getId()) {
                toRemove.add(ce);
                Repository.deleteEnrollment(ce.getId());
            }
        }
        for (Enrollment ce : allEnrollmentsT) {
            if (id == ce.course.getId()) {
                toRemove.add(ce);
                Repository.deleteEnrollment(ce.getId());
            }
        }
        allEnrollmentsS.removeAll(toRemove);
        allEnrollmentsT.removeAll(toRemove);
    }

    public int getId() {
        return id;
    }
}
