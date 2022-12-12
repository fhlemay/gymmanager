package com.gymargente.gymmanager.model.plan.acces;

public record Acces(int id, String nom) {

    public Acces(String nom) {
        this(0, nom);
    }
}
