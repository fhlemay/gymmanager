package com.gymargente.gymmanager;

import com.gymargente.gymmanager.db.Dao;
import com.gymargente.gymmanager.db.Database;
import com.gymargente.gymmanager.model.utilisateur.Utilisateur;
import com.gymargente.gymmanager.model.utilisateur.UtilisateurDao;

import java.sql.SQLException;
import java.util.List;

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

//        Dao<Utilisateur> utilisateurDao = new UtilisateurDao();
//
//        List<Utilisateur> utilisateurs = utilisateurDao.getAll();
//        utilisateurs.forEach(System.out::println);

//        for (int i = 0; i<10; i++) {
//            utilisateurDao.create(new Utilisateur("Bob", "motdepassebidon", 0));
//        }
//        var utilisateurOpt = utilisateurDao.findById(7);

//        if(utilisateurOpt.isPresent()){
//            var utilisateur = new Utilisateur(
//                    utilisateurOpt.get().id(),
//                    "Gandalf",
//                    utilisateurOpt.get().motDePasse(),
//                    utilisateurOpt.get().profileId());
//            utilisateurDao.update(utilisateur);
//            utilisateurDao.delete(utilisateurOpt.get());
//        }








        try {
            db.disconnect();
        } catch (SQLException e) {
            System.out.println("Ne peut se déconnecter de la base de données.");
            return;
        }
    }
}
