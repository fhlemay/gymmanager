package com.gymargente.gymmanager.model.utilisateur.utilisateurprofil;

public record UtilisateurProfil(int id,
                                int idUtilisateur,
                                int idProfil) {
    public UtilisateurProfil(int idUtilisateur, int idProfil){
        this(0, idUtilisateur, idProfil);
    }
}
