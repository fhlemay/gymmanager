package com.gymargente.gymmanager.controller;

import com.gymargente.gymmanager.model.client.Client;
import com.gymargente.gymmanager.model.client.ClientCellFactory;
import com.gymargente.gymmanager.model.client.ClientController;
import com.gymargente.gymmanager.model.client.ClientService;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    @FXML private ClientController clientController;
    @FXML private BlocHeuresController blocHeuresController;

    private ObservableList<Client> clientsList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        txtSearchClient.clear();
        Platform.runLater(() -> txtSearchClient.requestFocus()); // Mettre le focus sur un controle.

        clientsList = ClientService.getAllClients();
        lvClients.setCellFactory(new ClientCellFactory());
        lvClients.setItems(clientsList);
        lvClients.getSelectionModel().setSelectionMode(SelectionMode.SINGLE); //un seul client peut etre selectionne
        lvClients.getSelectionModel().selectedItemProperty().addListener(
                (observableValue, oldClient, newClient) -> {
                    if(newClient != null)
                        clientController.setClient(newClient);
                    blocHeuresController.setClient(newClient);
                });
        clientController.refreshParent().addListener((observable, oldValue, newValue) -> this.initialize(location, resources));
    }

    @FXML
    void handleTextChange(KeyEvent event) {
        clientsList.setAll(ClientService.getClientsByText(txtSearchClient.getText()));
    }
}
