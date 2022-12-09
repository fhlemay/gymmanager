package com.gymargente.gymmanager.model.abonnement;

import java.util.Date;

public record Abonnement(int id,
                         int idClient,
                         int idPlan,
                         Date dateDebut,
                         Date dateFin,
                         int etat) {

    public Abonnement (int idClient, int idPlan, Date dateDebut, Date dateFin, int etat){
        this(0, idClient, idPlan, dateDebut,dateFin, etat);
    }
}
