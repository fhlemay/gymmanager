package com.gymargente.gymmanager.model.utilisateur.fonction;

public record Fonction(int id, String nom) {

    public Fonction(String nom){
        this(0, nom);
    }
}
