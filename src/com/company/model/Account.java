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
    public Account(String login, String password) {
        setProperties(++lastId, login, password);
        add(id, login, password);
    }

    public Account(int id, String login, String password) {
        lastId = id;
        setProperties(id, login, password);
    }

    public void setProperties(int id, String login, String password){
        int[] acc = new int[]{login.hashCode(), password.hashCode()};
        this.id = id;
        Account.login = acc[0];
        Account.password = acc[1];
        allAccount.add(this);
    }

    public static void add(int id, String login, String password){
        Repository.addAccount(id, login.hashCode(), password.hashCode());
    }

    public static void update(String login, String password){
        ArrayList<Integer> values = getAccountValues();
        for (int i = 0; i < values.size(); i++) {
            if (login.hashCode() == values.get(i)){
                Repository.updateAccount(lastId, password);
            }
        }
    }
    public static boolean checkingAccountAvailability(int login) {
        ArrayList<Integer> values = getAccountValues();
        for (int i = 0; i < values.size(); i++){
            if (values.get(i) == login){
                return true;
            }
        }
        return false;
    }
    public static ArrayList<Integer> getAccountValues(){
        ArrayList<String> accountsValues = Repository.getAll("account");
        ArrayList<Integer> values = new ArrayList<>();
        for (int i = 0; i < accountsValues.size(); i++) {
            values.add(Integer.parseInt(accountsValues.get(i)));
        }
        return values;
    }
    public static void delete(int id){
        Repository.deleteAccount(id);
    }
    public static void saveLog(String loginText, String passwordText){
        int login = loginText.hashCode(), password = passwordText.hashCode();
        ArrayList<String> accounts = Repository.getAll("account");
        for (int i = 0; i < accounts.size() - 1; i++) {
            if (Integer.parseInt(accounts.get(i)) == login && Integer.parseInt(accounts.get(i + 1)) == password){
                Main.startProgram();
                saveUser.login = login;
                saveUser.password = password;
            }
        }
    }

    public static int[] returnId() {
        ArrayList<String> accounts = Repository.getAll("account");
        int[] id = new int[accounts.size()/3];
        int t = 0;
        int t1 = 0;
        int t2 = 0;
        for (int i = 0; i < id.length; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 0) {
                    id[t] = Integer.parseInt(accounts.get(t2));
                    t2 += 3;
                    t++;
                }
                t1++;
            }
        }
        return id;
    }
    public static int[] returnLogin(){
        ArrayList<String> accounts = Repository.getAll("account");
        int[] login = new int[accounts.size()/3];
        int t = 0;
        int t1 = 0;
        int t2 = 1;
        for (int i = 0; i < login.length; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 1) {
                    login[t] = Integer.parseInt(accounts.get(t2));
                    t2 += 3;
                    t++;
                }
                t1++;
            }
        }
        return login;
    }
    public static int[] returnPassword(){
        ArrayList<String> accounts = Repository.getAll("account");
        int[] password = new int[accounts.size()/3];
        int t = 0;
        int t1 = 0;
        int t2 = 2;
        for (int i = 0; i < password.length; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 2) {
                    password[t] = Integer.parseInt(accounts.get(t2));
                    t2 += 3;
                    t++;
                }
                t1++;
            }
        }
        return password;
    }
    public static int getIdByUser(){
        int[] ids = returnId();
        int[] login = returnLogin();
        int[] password = returnPassword();
        int id;
        ArrayList<String> accounts = Repository.getAll("account");
        for (int i = 0; i < accounts.size(); i++) {
            if (login[i] == saveUser.login && password[i] == saveUser.password){
                id = ids[i];
                saveUser.id = id;
                return id;
            }
        }
        return 0;
    }
    public static int toDelete(){
        return saveUser.id;
    }
}
class saveUser {
    public static int id;
    public static int login;
    public static int password;

    public saveUser(int login, int password){
        saveUser.login = login;
        saveUser.password = password;
    }
}
