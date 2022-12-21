package com.gymargente.gymmanager.model.consultation.prixheuresconsultation;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;


// Pour le listview du formulaire principal
public class PrixHeuresConsultationCellFactory implements Callback<ListView<PrixHeuresConsultation>, ListCell<PrixHeuresConsultation>> {
    @Override
    public ListCell<PrixHeuresConsultation> call(ListView<PrixHeuresConsultation> clientListView) {
        return new ListCell<PrixHeuresConsultation>() {
            @Override
            public void updateItem (PrixHeuresConsultation prixHeuresConsultation, boolean empty) {
                super.updateItem(prixHeuresConsultation, empty);
                if(empty || prixHeuresConsultation == null) {
                    setText(null);
                } else {
                    setText("h min : "+ prixHeuresConsultation.heureMin() +
                            ", h max : " + prixHeuresConsultation.heureMax() +
                            ", prix : " + prixHeuresConsultation.prix());
                }
            }
        };
    }
}
