package com.company.repository;

import com.company.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Repository {
    private static String url = "jdbc:postgresql://localhost:5432/postgres";
    private static String user = "postgres";
    private static String password = "123";
    private static final HashMap<String, String[]> tables = new HashMap<>() {
        {
            put("enrollment", new String[]{"id", "user_id", "course_id", "user"});
            put("student", new String[]{"id", "name", "surname", "email", "phone"});
            put("teacher", new String[]{"id", "name", "surname", "email", "phone"});
            put("course", new String[]{"id", "title", "description", "teacher"});
            put("account", new String[]{"id", "login", "password", "user", "user_id"});
        }
    };

    public Repository() {
        getAll();
    }

    public static void getAll() {
        try {
            // создаём соединение
            Connection conn = DriverManager.getConnection(url, user, password);
            for (String table : tables.keySet()) {
                getAllTableRecords(conn, table);
            }
            conn.close();
        } catch (Exception e) {
            System.out.println("Не удалось подключиться к БД");
            System.out.println(e.getMessage());
        }
    }
    public static ArrayList<String> getAll(String tableName){
        ArrayList values = null;
        try {
            // создаём соединение
            Connection conn = DriverManager.getConnection(url, user, password);
            values = getAllTableRecords(tableName, conn);
            conn.close();
            return values;
        } catch (Exception e) {
            System.out.println("Не удалось подключиться к БД");
            System.out.println(e.getMessage());
        }
        return values;
    }

    private static void getAllTableRecords(Connection conn, String tableName) throws SQLException {
        // запустим соединение
        Statement statement = conn.createStatement();
        ResultSet results = statement.executeQuery("select * from " + tableName + " order by id");
        String[] fields = tables.get(tableName);
        while (results.next()) {
            String[] values = new String[fields.length];
            for (int i = 0; i < fields.length; i++) {
                values[i] = results.getString(i + 1);
            }

            switch (tableName) {
                case "enrollment" -> new Enrollment(Integer.parseInt(values[0]),
                        Integer.parseInt(values[1]),
                        Integer.parseInt(values[2]),
                                         values[3]);
                case "course" -> new Course(Integer.parseInt(values[0]), values[1], values[2], values[3]);
                case "student" -> new Student(Integer.parseInt(values[0]), values[1], values[2], values[3], values[4]);
                case "teacher" -> new Teacher(Integer.parseInt(values[0]), values[1], values[2], values[3], values[4]);
                case "account" -> new Account(Integer.parseInt(values[0]), values[1], values[2], values[3], Integer.parseInt(values[4]));
            }
        }
    }
    private static ArrayList<String> getAllTableRecords(String tableName, Connection conn) throws SQLException {
        // запустим соединение
        Statement statement = conn.createStatement();
        ResultSet results = statement.executeQuery("select * from " + tableName + " order by id");
        String[] fields = tables.get(tableName);
        ArrayList<String> values = new ArrayList<>();
        while (results.next()) {
            for (int i = 0; i < fields.length; i++) {
                values.add(results.getString(i + 1));
            }
        }
        return values;
    }

    public static void deleteStudent(int id) {
        delete("student", id);
    }
    public static void deleteTeacher(int id) {
        delete("teacher", id);
    }
    public static void deleteCourse(int id) {
        delete("course", id);
    }
    public static void deleteAccount(int id){delete("account", id);}
    public static void deleteEnrollment(int id) {
        delete("enrollment", id);
    }

    public static void delete(String tableName, int id) {
        try {
            // создаём соединение
            Connection conn = DriverManager.getConnection(url, user, password);
            // выполним запрос
            conn.createStatement().executeUpdate("DELETE FROM " + tableName + " where (id= " + id + ")");
            conn.close();
        } catch (Exception e) {
            System.out.println("Не удалось удалить " + tableName);
            System.out.println(e.getMessage());
        }
    }


    public static void addStudent(String id, String[] values) {
        add("student",  new String[]{id, values[0], values[1], values[2], values[2]});
    }
    public static void addTeacher(String id, String[] values) {
        add("teacher",  new String[]{id, values[0], values[1], values[2], values[2]});
    }
    public static void addCourse(String id, String[] values) {
        add("course", new String[]{id, values[0], values[1], values[2]});
    }
    public static void addAccount(int id, int login, int password, String user, int user_id){
        add("account", new String[]{id + "", login + "", password + "", user, String.valueOf(user_id)});
    }
    public static void addEnrollment(String id, String user_id, String course_id, String user) {
        add("enrollment",  new String[]{id, user_id, course_id, user});
    }

    public static void add(String tableName, String[] values) {
        try {
            // создаём соединение
            Connection conn = DriverManager.getConnection(Repository.url, Repository.user, Repository.password);

            // запустим соединение
            PreparedStatement statement =
                    conn.prepareStatement(
                            "insert into " + tableName + " values " +
                                    "(" + String.join(", ", values) + ")");
            statement.executeUpdate();
            conn.close();
        } catch (Exception e) {
            System.out.println("Добавить запись в " + tableName + " не удалось");
            System.out.println(e.getMessage());
        }
    }

    public static void updateCourse(int id, String[] values) {
        update("course", id, values);
    }
    public static void updateTeacher(int id, String[] values){
        update("teacher", id, values);
    }

    public static void updateStudent(int id, String[] values) {
        update("student", id, values);
    }

    public static void updateAccount(int id, String password){
        update("account", id, new String[]{password});
    }

    public static void update(String tableName, int id, String[] values) {
        try {
            // создаём соединение
            Connection conn = DriverManager.getConnection(url, user, password);

            // запустим соединение
            String[] columns = tables.get(tableName);

            PreparedStatement statement = null;
            switch (tableName) {
                case "course" -> statement =
                            conn.prepareStatement("update " + tableName +
                                    " set " +
                                    columns[1] + " = " + values[0] +", " +
                                    columns[2] + " = " + values[1] +", " +
                                    columns[3] + " = " + values[2] +", " +
                                    "where id = " + id + ");");

                case "teacher", "student" -> statement =
                        conn.prepareStatement(
                                  "update " + tableName + " set " +
                                columns[1] + " = " + values[1] + ", " +
                                columns[2] + " = " + values[2] + ", " +
                                columns[3] + " = " + values[3] + ", " +
                                columns[4] + " = " + values[4] + ", " +
                                "where id = " + id + ");");
                case "account" -> statement =
                            conn.prepareStatement("update " + tableName +
                                    " set " +
                                    columns[2] + " = '" + values[0].hashCode() +
                                    "' WHERE id = " + id + ";");
            }
            statement.executeUpdate();
            conn.close();
        } catch (Exception e) {
            System.out.println("Не удалось изменить запись в " + tableName);
            System.out.println(e.getMessage());
        }
    }

}
