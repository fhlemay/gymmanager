package com.gymargente.gymmanager.controller;

import com.gymargente.gymmanager.model.client.Client;
import com.gymargente.gymmanager.model.client.ClientCellFactory;
import com.gymargente.gymmanager.model.client.ClientService;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class GestionCompteController implements Initializable {
    @FXML
    private ListView<Client> lvClients;
    @FXML
    private TextField txtSearchClient;
    @FXML
    private Label lblClientInfo;

    private ObservableList<Client> clientsList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        Platform.runLater(() -> btnLogin.requestFocus()); // Le focus est initialement sur le bouton.

        clientsList = ClientService.getAllClients();

        lvClients.setCellFactory(new ClientCellFactory());
        lvClients.setItems(clientsList);
        lvClients.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        lvClients.getSelectionModel().selectedItemProperty().addListener(
                (observableValue, oldClient, newClient) -> {
                    if(newClient != null)
                        lblClientInfo.setText(newClient.prenom() + " " + newClient.nom());
                });
        Client selectedClient = lvClients.getSelectionModel().getSelectedItem();
    }

    @FXML
    void handleTextChange(KeyEvent event) {
        clientsList.setAll(ClientService.getClientsByText(txtSearchClient.getText()));
    }


}
