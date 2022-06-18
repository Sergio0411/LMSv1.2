package com.company;


import com.company.repository.Repository;
import com.company.view.LoginFrame;
import com.company.view.MainFrame;
import com.company.view.course.AddCourseFrame;
import com.company.view.student.AddStudentFrame;

public class Main {
    public static LoginFrame loginFrame;
    public static MainFrame mainFrame = new MainFrame();
    public static AddStudentFrame addStudentFrame = new AddStudentFrame();
    public static AddCourseFrame addCourseFrame = new AddCourseFrame();
    public static Repository repository;
    public static void main(String[] args) {
        loginFrame = new LoginFrame();
    }
    public static void startProg(String user, String password){
        mainFrame.setVisible(true);
        repository = new Repository("jdbc:postgresql://localhost:5432/postgres", user, password);
        loginFrame.setVisible(false);
    }
}