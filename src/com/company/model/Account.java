package com.company.model;

import com.company.Main;
import com.company.repository.Repository;

import java.util.ArrayList;

public class Account {
    private int id;
    private static int login;
    private static int password;
    private static int lastId = 0;

    public static ArrayList<Account> allAccount = new ArrayList<>();
    public Account(String login, String password, String user, int user_id) {
        setProperties(++lastId, login, password, user, user_id);
        add(id, login, password, "'" + user + "'", user_id);
    }

    public Account(int id, String login, String password, String user, int user_id) {
        lastId = id;
        setProperties(id, login, password, user, user_id);
    }

    public static void deleteUser(int id) {
        int[] Ids = returnIds();
        int[] teachersIds = Teacher.returnIds();
        int[] studentsIds = Student.returnIds();
        String user = returnUser(id);
        int userId;
        for (int i = 0; i < Ids.length; i++) {
            if (id == Ids[i]) {
                userId = returnUserId(id);
                switch (user) {
                    case "student" -> {
                        int rowCount = Student.model.getRowCount();
                        ArrayList<String> studentsValues = Repository.getAll("student");
                        for (int j = 0; j < rowCount; j++) {
                            if (userId == studentsIds[j]) {
                                for (int k = 0; k < studentsValues.size(); k += 5) {
                                    String getValue = String.valueOf(Student.model.getValueAt(j, 0));
                                    String getStudentsValues = studentsValues.get(k);
                                    if (getValue.equals(getStudentsValues)) {
                                        Student.delete(userId, j);
                                    }
                                }
                            }
                        }
                        break;
                    }
                    case "teacher" -> {
                        int rowCount = Teacher.model.getRowCount();
                        ArrayList<String> teachersValues = Repository.getAll("teacher");
                        for (int j = 0; j < rowCount; j++) {
                            if (userId == teachersIds[j]) {
                            for (int k = 0; k < teachersValues.size(); k += 5) {
                                    String getValue = String.valueOf(Teacher.model.getValueAt(j, 0));
                                    String getTeachersValues = teachersValues.get(k);
                                    if (getValue.equals(getTeachersValues)) {
                                        Teacher.delete(userId, j);
                                    }
                                }
                            }
                        }
                        break;
                    }
                }
            }
        }
    }

    public void setProperties(int id, String login, String password, String user, int user_id){
        int[] acc = new int[]{login.hashCode(), password.hashCode()};
        this.id = id;
        Account.login = acc[0];
        Account.password = acc[1];
        saveUser.user = user;
        saveUser.userId = user_id;
        allAccount.add(this);
    }

    public static void add(int id, String login, String password, String user, int user_id){
        Repository.addAccount(id, login.hashCode(), password.hashCode(), user, user_id);
    }

    public static void update(String login, String password){
        ArrayList<String> values = getAccountValues();
        for (int i = 1; i < values.size(); i += 5) {
            if (login.hashCode() == Integer.parseInt(values.get(i))){
                Repository.updateAccount(Integer.parseInt(values.get(i - 1)), password);
            }
        }
    }

    public static boolean checkingAccountAvailability(int login) {
        ArrayList<String> values = getAccountValues();
        for (int i = 1; i < values.size(); i += 5){
            if (Integer.parseInt(values.get(i)) == login){
                return true;
            }
        }
        return false;
    }

    public static ArrayList<String> getAccountValues(){
        return Repository.getAll("account");
    }

    public static void delete(int id){
        Repository.deleteAccount(id);
    }
    public static void saveLog(String loginText, String passwordText){
        int login = loginText.hashCode(), password = passwordText.hashCode();
        ArrayList<String> accounts = Repository.getAll("account");
        for (int i = 0; i < accounts.size(); i += 5) {
            if (Integer.parseInt(accounts.get(i + 1)) == login && Integer.parseInt(accounts.get(i + 2)) == password){
                Main.startProgram();
                saveUser.login = login;
                saveUser.password = password;
                saveUser.user = accounts.get(i + 3);
                break;
            }
        }
    }
    public static int[] returnIds() {
        ArrayList<String> accounts = Repository.getAll("account");
        int[] id = new int[accounts.size()/5];
        int t = 0;
        for (int i = 0; i < accounts.size(); i += 5) {
            id[t] = Integer.parseInt(accounts.get(i));
            t++;
        }
        return id;
    }
    public static int[] returnLogins(){
        ArrayList<String> accounts = Repository.getAll("account");
        int[] login = new int[accounts.size()/5];
        int t = 0;
        for (int i = 1; i < accounts.size(); i += 5) {
            login[t] = Integer.parseInt(accounts.get(i));
            t++;
        }
        return login;
    }
    public static int[] returnPasswords(){
        ArrayList<String> accounts = Repository.getAll("account");
        int[] password = new int[accounts.size()/5];
        int t = 0;
        for (int i = 2; i < accounts.size(); i += 5) {
            password[t] = Integer.parseInt(accounts.get(i));
            t++;
        }
        return password;
    }

    private static String[] returnUsers() {
        ArrayList<String> accounts = Repository.getAll("account");
        String[] user = new String[accounts.size()/5];
        int t = 0;
        for (int i = 3; i < accounts.size(); i += 5) {
            user[t] = accounts.get(i);
            t++;
        }
        return user;
    }

    private static String returnUser(int id) {
        ArrayList<String> accounts = Repository.getAll("account");
        String stringId = String.valueOf(id);
        for (int i = 0; i < accounts.size(); i += 5){
            if (accounts.get(i).equals(stringId)){
                return accounts.get(i + 3);
            }
        }
        return null;
    }
    public static int[] returnUsersIds(){
        ArrayList<String> accounts = Repository.getAll("account");
        int[] userId = new int[accounts.size()/5];
        int t = 0;
        for (int i = 4; i < accounts.size(); i += 5) {
            userId[t] = Integer.parseInt(accounts.get(i));
            t++;
        }
        return userId;
    }
    private static int returnUserId(int id) {
        ArrayList<String> accounts = Repository.getAll("account");
        String stringId = String.valueOf(id);
        for (int i = 0; i < accounts.size(); i += 5){
            if (accounts.get(i).equals(stringId)){
                return Integer.parseInt(accounts.get(i + 4));
            }
        }
        return -1;
    }

    public static int getIdByUser(String loginText, String passwordText){
        int[] ids = returnIds();
        int[] login = returnLogins();
        int[] password = returnPasswords();
        int id;
        int t = 0;
        ArrayList<String> accounts = Repository.getAll("account");
        for (int i = 0; i < accounts.size()/5; i++) {
            if (login[t] == saveUser.login &&
                    saveUser.login == loginText.hashCode() &&
                    password[t] == saveUser.password &&
                    saveUser.password == passwordText.hashCode()){
                id = ids[t];
                saveUser.id = id;
                return id;
            }
            t++;
        }
        return -8942736;
    }
    public static int toDelete(){
        return saveUser.id;
    }
}