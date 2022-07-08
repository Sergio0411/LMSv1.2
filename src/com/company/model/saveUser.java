package com.company.model;

public class saveUser {
    public static int id;
    public static int login;
    public static int password;
    public static int userId;
    public static String user;

    public saveUser(int login, int password) {
        saveUser.login = login;
        saveUser.password = password;
    }
}
