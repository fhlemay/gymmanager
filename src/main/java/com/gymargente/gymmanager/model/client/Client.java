package com.gymargente.gymmanager.model.client;

import java.math.BigDecimal;
import java.sql.Date;

public record Client(int id,
                     String nom, String prenom,
                     String courriel, String telephone,
                     Date dateAdhesion,  // attention --> sql.date, not util.date
                     BigDecimal montantDu) {

    public Client(String nom, String prenom,
                  String courriel, String telephone,
                  Date dateAdhesion,
                  BigDecimal montantDu) {
        this(0,
                nom, prenom,
                courriel, telephone,
                dateAdhesion,
                montantDu);
    }
}
