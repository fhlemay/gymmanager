package com.gymargente.gymmanager;

import java.sql.*;

public class AppMySQL {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        int[] ids = {0,1,2};
        String[] names = {"Sue", "Bob", "Charley"};

        Class.forName("com.mysql.cj.jdbc.Driver"); // juste pour tester.

        String dbUrl = "jdbc:mysql://localhost:3306/gym_manager";
        Connection conn = DriverManager.getConnection(dbUrl, "root", "Midori10");// TODO : to change from root!!!
        conn.setAutoCommit(false); // autocommit : met à jour la db automatiquement

        Statement stmt = conn.createStatement();


//        String sql = "insert into user (id, name) values (?, ?)";
//        PreparedStatement insertStmt = conn.prepareStatement(sql);
//
//        for (int i = 0; i < ids.length; i++) {
//            insertStmt.setInt(1, ids[i]);
//            insertStmt.setString(2, names[i]);
//
//            insertStmt.executeUpdate();
//        }
//        conn.commit(); // on écrit les données permanentes
//        insertStmt.close();

        String sqlPrint = "select * from user";
        ResultSet rs = stmt.executeQuery(sqlPrint);
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            System.out.println(id + " " + name);
        }

//        stmt.execute("drop table user"); // on efface les données.
        conn.commit();
        stmt.close();
        conn.close();

    }
}
