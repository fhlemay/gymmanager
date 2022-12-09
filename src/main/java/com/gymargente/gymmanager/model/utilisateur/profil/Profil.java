package com.gymargente.gymmanager.model.utilisateur.profil;

public record Profil(int id, String nom) {

    public Profil(String nom){
        this(0, nom);
    }
}
