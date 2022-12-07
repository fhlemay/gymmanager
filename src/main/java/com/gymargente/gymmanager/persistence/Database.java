package com.gymargente.gymmanager.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Donne accès à la base de données.
 * Utilise le patron Singleton.
 */
public class Database {
    private static Database db = new Database();
    private final String LOGIN = "gymmanager";
    private final String PASSWORD = "gymmanager";
    private static final String URL = "jdbc:mysql://localhost:3306/gym_manager";
    private Connection connection;

    public static Database getInstance() {
        return db;
    }

    private Database () {
    }

    public Connection getConnection(){
        return connection;
    }

    public void connect (Properties properties) throws SQLException {
        String server = properties.getProperty("server");
        String port = properties.getProperty("port");
        String database = properties.getProperty("database");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String dbConnector = "jdbc:mysql://";
        String url = dbConnector+server+":"+port+"/"+database;

        connection = DriverManager.getConnection(url, user, password);
//        connection.setAutoCommit(false); // autocommit : met à jour la db automatiquement
    }

    public void disconnect () throws SQLException {
        connection.close();
    }
}
