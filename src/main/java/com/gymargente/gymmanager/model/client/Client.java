package com.gymargente.gymmanager.model.client;

import java.util.Date;

public record Client (int id,
                     String nom,
                     String prenom,
                     String courriel,
                     String telephone,
                     Date dateAdhesion,
                     int heureSpecialiste,
                     int heureReservee) {

    public Client(String nom, String prenom,
                  String courriel, String telephone,
                  Date dateAdhesion,
                  int heureSpecialiste,
                  int heureReservee){
        this(0, nom, prenom, courriel, telephone, dateAdhesion, heureSpecialiste, heureReservee);
    }
}
