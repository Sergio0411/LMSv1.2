package com.company;

import com.company.model.Course;
import com.company.repository.Repository;
import com.company.view.course.AddCourseFrame;
import com.company.view.delete.DeleteAccountFrame;
import com.company.view.login.LoginFrame;
import com.company.view.MainFrame;
import com.company.view.naoAccess.noAccessFrame;
import com.company.view.recover.RecoverFrame;
import com.company.view.register.RegisterFrame;

public class Main {
    public static LoginFrame loginFrame;
    public static MainFrame mainFrame = new MainFrame();
    public static RecoverFrame recoverFrame = new RecoverFrame();
    public static RegisterFrame registerFrame = new RegisterFrame();
    public static Repository repository = new Repository();
    public static AddCourseFrame addCourseFrame = new AddCourseFrame();
    public static int id;
    public static DeleteAccountFrame deleteAccountFrame;
    public static com.company.view.naoAccess.noAccessFrame noAccessFrame = new noAccessFrame();
    public static Course course;

    public static void main(String[] args) {
        loginFrame = new LoginFrame();
    }
    public static void startProgram(){
        mainFrame.setVisible(true);
        loginFrame.setVisible(false);
    }
}