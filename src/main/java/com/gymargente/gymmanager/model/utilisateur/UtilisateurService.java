package com.gymargente.gymmanager.model.utilisateur;

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
}
