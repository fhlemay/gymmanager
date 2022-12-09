package com.gymargente.gymmanager.model.consultation;

import java.util.Date;

public record Consultation(int id,
                           int idSpecialiste,
                           int idClient,
                           Date dateConsultation,
                           int etat) {

    public Consultation(int idSpecialiste, int idClient,
                        Date dateConsultation, int etat){
        this(0, idSpecialiste, idClient, dateConsultation, etat);
    }

}
