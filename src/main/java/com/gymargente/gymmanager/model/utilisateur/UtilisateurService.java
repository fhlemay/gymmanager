package com.gymargente.gymmanager.model.utilisateur;

import com.gymargente.gymmanager.model.utilisateur.profil.ProfilDao;
import com.gymargente.gymmanager.model.utilisateur.utilisateurprofil.UtilisateurProfilDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UtilisateurService {
    public static Optional<Utilisateur> authenticate (String user, String password){

        var utilisateurDao = new UtilisateurDao();
        var utilisateur = utilisateurDao.findByIdentifiant(user);
        if(utilisateur.isPresent() &&
                utilisateur.get().motDePasse().equals(password)) {
            return utilisateur;
        } else {
            return Optional.empty();
        }

    }

    // TODO : C'est de la bouette!!! Rendre cette méthode plus compréhensible.
    public static List<String> getProfiles (int userId){

        var utilisateurProfileDao = new UtilisateurProfilDao();
        var profilDao = new ProfilDao();

        List<String> profil = new ArrayList<>();

        var profilsId = utilisateurProfileDao.getProfilIdsfor(userId);

        if(profilsId.isPresent()){
//            System.out.println("UtlisateurService " + profilsId.get());
            for(Integer profilId : profilsId.get()) {
                var profilName = profilDao.findById(profilId);
                profilName.ifPresent(value -> profil.add(value.nom()));
            }
        }
        return profil;
    }
}
