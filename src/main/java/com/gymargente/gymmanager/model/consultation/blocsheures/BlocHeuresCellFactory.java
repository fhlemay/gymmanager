package com.gymargente.gymmanager.model.consultation.blocsheures;

import com.gymargente.gymmanager.model.client.Client;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;


// Pour le listview du formulaire principal
public class BlocHeuresCellFactory implements Callback<ListView<BlocsHeures>, ListCell<BlocsHeures>> {
    @Override
    public ListCell<BlocsHeures> call(ListView<BlocsHeures> clientListView) {
        return new ListCell<BlocsHeures>() {
            @Override
            public void updateItem (BlocsHeures blocsHeures, boolean empty) {
                super.updateItem(blocsHeures, empty);
                if(empty || blocsHeures == null) {
                    setText(null);
                } else {
//                    DateFormat bla = new SimpleDateFormat("d MMMM yyyy");
                    setText("Heures achetées : "+ blocsHeures.heuresAchetees() +
                            ", heures restantes : " + blocsHeures.heuresRestantes());
                }
            }
        };
    }
}
