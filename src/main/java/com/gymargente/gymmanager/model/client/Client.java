package com.gymargente.gymmanager.model.client;

import java.util.Date;

public record Client (int id,
                     String nom,
                     String prenom,
                     String courriel,
                     String telephone,
                     Date dateAdhesion) {

    public Client(String nom, String prenom,
                  String courriel, String telephone,
                  Date dateAdhesion){
        this(0, nom, prenom, courriel, telephone, dateAdhesion);
    }
}
