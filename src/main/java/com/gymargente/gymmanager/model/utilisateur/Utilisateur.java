package com.gymargente.gymmanager.model.utilisateur;

public record Utilisateur(int id,
                          String identifiant,
                          String motDePasse) {
    public Utilisateur(String nom, String motDePasse, int profileId){
        this(0, nom, motDePasse);
    }
}