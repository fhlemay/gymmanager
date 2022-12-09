package com.gymargente.gymmanager.model.client;

import java.util.Date;

public record Client(int id,
                     String nom,
                     Date dateAdhesion,
                     int heureSpecialiste,
                     int heureReservee) {

    public Client(String nom,
                  Date dateAdhesion,
                  int heureSpecialiste,
                  int heureReservee){
        this(0, nom, dateAdhesion, heureSpecialiste, heureReservee);
    }
}
