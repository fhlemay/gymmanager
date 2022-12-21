package com.gymargente.gymmanager.model.consultation.prixheuresconsultation;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class PrixHeuresConsultationService {

    public static List<PrixHeuresConsultation> getAllPrixHeures() {

//        List<PrixHeuresConsultation> prixHeures = new ArrayList<>();
        return  new PrixHeuresConsultationDao().getAll();

    }

    public static PrixHeuresConsultation getPrixQteHeures(int qteHeures) {
        Optional<PrixHeuresConsultation> t = new PrixHeuresConsultationDao().getPriceForHoursQty(qteHeures);

        if(t.isPresent()) return t.get();

        return new PrixHeuresConsultation(0,0,BigDecimal.ZERO);
    }
}
