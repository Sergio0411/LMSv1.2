package com.company;

import com.company.repository.Repository;
import com.company.view.*;
import com.company.view.course.AddCourseFrame;

public class Main {
    public static LoginFrame loginFrame;
    public static MainFrame mainFrame = new MainFrame();
    public static RecoverFrame recoverFrame = new RecoverFrame();
    public static RegisterFrame registerFrame = new RegisterFrame();
    public static Repository repository = new Repository();
    public static AddCourseFrame addCourseFrame = new AddCourseFrame();
    public static int id;
    public static DeleteAccountFrame deleteAccountFrame;

    public static void main(String[] args) {
        loginFrame = new LoginFrame();
    }
    public static void startProgram(){
        mainFrame.setVisible(true);
        loginFrame.setVisible(false);
    }
}