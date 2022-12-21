package com.gymargente.gymmanager.model.consultation.prixheuresconsultation;

import java.math.BigDecimal;

public record PrixHeuresConsultation(int id,
                                     int heureMin,
                                     int heureMax,
                                     BigDecimal prix) {
    static public int NBR_MAX = 20;

    PrixHeuresConsultation(int heureMax,int heureMin, BigDecimal prix) {
        this(0, heureMin, heureMax, prix);
    }
}
