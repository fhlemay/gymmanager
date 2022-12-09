package com.gymargente.gymmanager.model.abonnement.etat;

public record AbonnementEtat(int id,
                             String nom) {
    public AbonnementEtat(String nom) {
        this(0, nom);
    }
}
