package com.gymargente.gymmanager.model.client;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


// Pour le listview du formulaire principal
public class ClientCellFactory implements Callback<ListView<Client>, ListCell<Client>> {
    @Override
    public ListCell<Client> call(ListView<Client> clientListView) {
        return new ListCell<>() {
            @Override
            public void updateItem (Client client, boolean empty) {
                super.updateItem(client, empty);
                if(empty || client == null) {
                    setText(null);
                } else {
                    DateFormat bla = new SimpleDateFormat("d MMMM yyyy");
                    setText(client.nom() + ", " + client.prenom() + " date d'adhésion : " + bla.format(client.dateAdhesion()));
                }
            }
        };
    }
}
