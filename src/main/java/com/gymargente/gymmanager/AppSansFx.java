package com.gymargente.gymmanager;

import com.gymargente.gymmanager.persistence.Dao;
import com.gymargente.gymmanager.persistence.Database;
import com.gymargente.gymmanager.user.User;
import com.gymargente.gymmanager.user.UserDao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public class AppSansFx {

    public static void main(String[] args) {

        String dbPropertiesFile = "/config/db.properties";
        Properties properties = new Properties();
        try {
            properties.load(AppSansFx.class.getResourceAsStream(dbPropertiesFile));
        } catch (IOException e) {
            System.out.println("Ne peut pas charger le fichier des propriétés : " + dbPropertiesFile);
        }

        var db = Database.getInstance();

        try {
            db.connect(properties);
        } catch (SQLException e) {
            System.out.println("Ne peut pas se connecter à la base de données.");
            return;
        }

        System.out.println("Base de données connectée.");

        Dao<User> userDao = new UserDao();

        var userOptional = userDao.findById(7);
        var user = userOptional.get();
        user.setName("MickeyMouse");
        userDao.update(user);

        try {
            db.disconnect();
        } catch (SQLException e) {
            System.out.println("Ne peut se déconnecter de la base de données.");
            return;
        }
    }
}
