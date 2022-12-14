package com.gymargente.gymmanager.model.client;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class ClientController implements Initializable {

    @FXML private Button btnAdd;
    @FXML private Button btnDelete;
    @FXML private Button btnModify;

    @FXML private Button btnSave;
    @FXML private Button btnInsert;
    @FXML private DatePicker dateAdhesion;
    @FXML private TextField txtCourriel;
    @FXML private TextField txtNom;
    @FXML private TextField txtPrenom;
    @FXML private TextField txtTelephone;
    @FXML private Label lblMontantDu;

    private final BooleanProperty refreshParent = new SimpleBooleanProperty();
    private Client client;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setClient(Client client) {
        this.client = client;
        txtNom.setText(client.nom());
        txtPrenom.setText(client.prenom());
        txtCourriel.setText(client.courriel());
        txtTelephone.setText(client.telephone());
        dateAdhesion.setValue(client.dateAdhesion().toLocalDate());
        lblMontantDu.setText(client.montantDu().toString());
    }

    @FXML
    void handleAddClient(ActionEvent event) {
        cleanFields();

        txtNom.setDisable(false);
        txtPrenom.setDisable(false);
        txtCourriel.setDisable(false);
        txtTelephone.setDisable(false);
        dateAdhesion.setDisable(false);

        btnDelete.setVisible(false);
        btnModify.setVisible(false);
        btnAdd.setVisible(false);

        btnInsert.setVisible(true);

        txtPrenom.requestFocus();
        dateAdhesion.setValue(LocalDate.now());
    }

    @FXML
    void handleDeleteClient(ActionEvent event) {

        if(client != null) {
            ClientService.deleteClient(this.client);
        }
        this.client = null;
        cleanFields();
        refreshParent.set(!refreshParent.getValue());
    }


    @FXML
    void handleModifyClient(ActionEvent event) {
        txtNom.setDisable(false);
        txtPrenom.setDisable(false);
        txtCourriel.setDisable(false);
        txtTelephone.setDisable(false);
        dateAdhesion.setDisable(false);

        btnDelete.setVisible(false);
        btnModify.setVisible(false);
        btnAdd.setVisible(false);

        btnSave.setVisible(true);
    }

    @FXML
    void handleSaveModification(ActionEvent event) {

        Client modClient = new Client(
                this.client.id(),
                txtNom.getText(),
                txtPrenom.getText(),
                txtCourriel.getText(),
                txtTelephone.getText(),
                Date.valueOf(dateAdhesion.getValue()),
                this.client.montantDu()
        );

        ClientService.updateClient(modClient);
        refreshParent.set(!refreshParent.getValue());

        btnDelete.setVisible(true);
        btnModify.setVisible(true);
        btnAdd.setVisible(true);
        btnSave.setVisible(false);
        txtNom.setDisable(true);
        txtPrenom.setDisable(true);
        txtCourriel.setDisable(true);
        txtTelephone.setDisable(true);
        dateAdhesion.setDisable(true);

    }

    @FXML
    public void handleInsert(){

        if(txtNom.getText().isBlank()) {
            return;
        }

        Client clientToAdd = new Client(
                txtNom.getText(),
                txtPrenom.getText(),
                txtCourriel.getText(),
                txtTelephone.getText(),
                Date.valueOf(dateAdhesion.getValue()), // yeurk!
                new BigDecimal("0.0")
        );

        ClientService.addClient(clientToAdd);

        refreshParent.set(!refreshParent.getValue());

        btnDelete.setVisible(true);
        btnModify.setVisible(true);
        btnAdd.setVisible(true);
        btnSave.setVisible(false);
        btnInsert.setVisible(false);
        txtNom.setDisable(true);
        txtPrenom.setDisable(true);
        txtCourriel.setDisable(true);
        txtTelephone.setDisable(true);
        dateAdhesion.setDisable(true);
    }

    @FXML
    public void handlePayer(){
        Alert message = new Alert(Alert.AlertType.INFORMATION);
        message.setTitle("On va payer ici...");
        message.setHeaderText("Transaction ?? d??velopper.");
        message.setContentText("On cr??er une transaction puis on soustrait la somme pay??e du montant d??.");

        message.showAndWait();
    }

    private void cleanFields() {
        txtNom.clear();
        txtPrenom.clear();
        txtCourriel.clear();
        txtTelephone.clear();
        dateAdhesion.setValue(null);
    }

    public BooleanProperty refreshParent () {
        return refreshParent;
    }
}
