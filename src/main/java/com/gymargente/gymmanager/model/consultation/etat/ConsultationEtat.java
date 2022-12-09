package com.gymargente.gymmanager.model.consultation.etat;

public record ConsultationEtat(int id,
                               String nom) {
    public ConsultationEtat(String nom) {
        this(0, nom);
    }
}
