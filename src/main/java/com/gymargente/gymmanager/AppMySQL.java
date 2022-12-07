package com.gymargente.gymmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AppMySQL {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc"); // juste pour tester.

//        String dbUrl = "jdbc:sqlite:people.db";
//        Connection conn = DriverManager.getConnection(dbUrl);
//        conn.setAutoCommit(false); // autocommit : met à jour la db automatiquement

    }
}
