package com.gymargente.gymmanager;

import java.sql.*;

public class AppSQLite {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        int[] ids = {0,1,2};
        String[] names = {"Sue", "Bob", "Charley"};

        Class.forName("org.sqlite.JDBC"); // juste pour tester.

        String dbUrl = "jdbc:sqlite:people.db";
        Connection conn = DriverManager.getConnection(dbUrl);
        conn.setAutoCommit(false); // autocommit : met à jour la db automatiquement

        Statement stmt = conn.createStatement();

        String createUserTable = "create table if not exists user (id integer primary key, name text not null)";
        stmt.execute(createUserTable);

        String sql = "insert into user (id, name) values (?, ?)";
        PreparedStatement insertStmt = conn.prepareStatement(sql);

        for (int i = 0; i < ids.length; i++) {
            insertStmt.setInt(1, ids[i]);
            insertStmt.setString(2, names[i]);

            insertStmt.executeUpdate();
        }

        conn.commit(); // on écrit les données permanentes
        insertStmt.close();

        String sqlPrint = "select * from user";
        ResultSet rs = stmt.executeQuery(sqlPrint);
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            System.out.println(id + " " + name);
        }

        stmt.execute("drop table user"); // on efface les données.
        conn.commit();
        stmt.close();
        conn.close();
    }
}
