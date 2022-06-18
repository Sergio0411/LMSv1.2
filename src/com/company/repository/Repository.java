package com.company.repository;

import com.company.model.Course;
import com.company.model.Enrollment;
import com.company.model.Student;

import java.sql.*;
import java.util.HashMap;

public class Repository {
    private static String url;
    private static String user;
    private static String password;
    private static final HashMap<String, String[]> tables = new HashMap<>() {
        {
            put("enrollment", new String[]{"id", "student_id", "course_id"});
            put("student", new String[]{"id", "name", "surname"});
            put("course", new String[]{"id", "title", "description"});
        }
    };

    public Repository(String url, String user, String password) {
        Repository.url = url;
        Repository.user = user;
        Repository.password = password;
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

    private static void getAllTableRecords(Connection conn, String tableName) throws SQLException {
        // запустим соединение
        Statement statement = conn.createStatement();
        ResultSet results = statement.executeQuery("select * from " + tableName + " order by id");

        while (results.next()) {
            int id = Integer.parseInt(results.getString(1));
            String col2 = results.getString(2);
            String col3 = results.getString(3);
            switch (tableName) {
                case "enrollment" -> new Enrollment(id, Integer.parseInt(col2), Integer.parseInt(col3));
                case "course" -> new Course(id, col2, col3);
                case "student" -> new Student(id, col2, col3);
            }
        }
    }

    public static void deleteStudent(int id) {
        delete("student", id);
    }

    public static void deleteCourse(int id) {
        delete("course", id);
    }

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


    public static void addCourse(int id, String title, String description) {
        add("course", id, title, description);
    }

    public static void addStudent(int id, String name, String surname) {
        add("student", id, name, surname);
    }

    public static void addEnrollment(int id, String student_id, String course_id) {
        add("enrollment", id, student_id, course_id);
    }

    public static void add(String tableName, int id, String col2, String col3) {
        try {
            // создаём соединение
            Connection conn = DriverManager.getConnection(Repository.url, Repository.user, Repository.password);

            // запустим соединение
            PreparedStatement statement =
                    conn.prepareStatement(
                            "insert into " + tableName + " values " +
                                    "(" + id + ", " + col2 + ", " + col3 + ")");
            statement.executeUpdate();
            conn.close();
        } catch (Exception e) {
            System.out.println("Добавить запись в " + tableName + " не удалось");
            System.out.println(e.getMessage());
        }
    }

    public static void updateCourse(int id, String title, String description) {
        update("course", id, title, description);

    }

    public static void updateStudent(int id, String name, String surname) {
        update("course", id, name, surname);

    }


    public static void update(String tableName, int id, String col2, String col3) {
        try {
            // создаём соединение
            Connection conn = DriverManager.getConnection(url, user, password);

            // запустим соединение
            String[] columns = tables.get(tableName);

            PreparedStatement statement =
                    conn.prepareStatement("update " + tableName +
                            " set " +
                            columns[1] + " = " + col2 +
                            columns[2] + " = " + col3 +
                            "where id = " + id);
            statement.executeUpdate();
            conn.close();
        } catch (Exception e) {
            System.out.println("Не удалось изменить запись в " + tableName);
            System.out.println(e.getMessage());
        }
    }
}