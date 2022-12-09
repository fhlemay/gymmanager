package com.gymargente.gymmanager.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Donne accès à la base de données.
 * Utilise le patron Singleton.
 */
public class Database {
    private static final Database db = new Database();
    private Connection connection;
    private String server, port, database, user, password;

    private Database() {
    }

    public static Database getInstance() {
        return db;
    }

    public Connection getConnection() {
        return connection;
    }

    public void connect() throws SQLException {
        setProperties();
        String dbConnector = "jdbc:mysql://";
        String url = dbConnector + server + ":" + port + "/" + database;
        System.out.println("Connexion à " + server + ":" + port + "/" + database);
        connection = DriverManager.getConnection(url, user, password);
//        System.out.println("Attention : AutoCommit est désactivé. (mode démo)");
//        connection.setAutoCommit(false); // TODO : autocommit : met à jour la db automatiquement
    }

    public void disconnect() throws SQLException {
        connection.close();
    }

    private void setProperties() {
        Properties properties = new Properties();
        String dbPropertiesFile = "/config/db.properties";
        try {
            properties.load(Database.class.getResourceAsStream(dbPropertiesFile));
        } catch (IOException e) {
            System.out.println("Ne peut pas charger le fichier des propriétés : " + dbPropertiesFile);
        }
        server = properties.getProperty("server");
        port = properties.getProperty("port");
        database = properties.getProperty("database");
        user = properties.getProperty("user");
        password = properties.getProperty("password");
    }
}
