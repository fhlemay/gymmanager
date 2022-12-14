package com.gymargente.gymmanager.model.client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ClientService {

    public static ObservableList<Client> getAllClients(){
        ObservableList<Client> allClients = FXCollections.observableArrayList();
        allClients.addAll(new ClientDao().getAll());
        return allClients;
    }

    public static ObservableList<Client> getClientsByText(String searchText){
        ObservableList<Client> allClients = FXCollections.observableArrayList();
        allClients.addAll(new ClientDao().getClientsByTextSearch(searchText));
        return allClients;
    }

    public static void updateClient(Client modClient) {
        new ClientDao().update(modClient);
    }

    public static void deleteClient(Client clientToDelete) {
        new ClientDao().delete(clientToDelete);
    }

    public static void addClient(Client clientToAdd) {
        new ClientDao().create(clientToAdd);
    }


}
