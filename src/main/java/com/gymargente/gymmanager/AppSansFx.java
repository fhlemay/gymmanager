package com.gymargente.gymmanager;

import com.gymargente.gymmanager.db.Dao;
import com.gymargente.gymmanager.db.Database;
import com.gymargente.gymmanager.model.user.User;
import com.gymargente.gymmanager.model.user.UserDao;

import java.sql.SQLException;

public class AppSansFx {

    private static Database db;

    public static void main(String[] args) {


        db = Database.getInstance();

        try {
            db.connect();
        } catch (SQLException e) {
            System.out.println("Ne peut pas se connecter à la base de données.");
            return;
        }
        System.out.println("Base de données connectée.");

        Dao<User> userDao = new UserDao(db.getConnection());

        var userOptional = userDao.findById(7);
        var user = userOptional.get();
        user.setName("Gandalf");
        userDao.update(user);

        try {
            db.disconnect();
        } catch (SQLException e) {
            System.out.println("Ne peut se déconnecter de la base de données.");
            return;
        }
    }
}
