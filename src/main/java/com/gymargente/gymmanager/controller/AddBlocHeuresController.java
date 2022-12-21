package com.gymargente.gymmanager.controller;


import com.gymargente.gymmanager.model.client.Client;
import com.gymargente.gymmanager.model.consultation.blocsheures.BlocsHeures;
import com.gymargente.gymmanager.model.consultation.blocsheures.BlocsHeuresService;
import com.gymargente.gymmanager.model.consultation.prixheuresconsultation.PrixHeuresConsultation;
import com.gymargente.gymmanager.model.consultation.prixheuresconsultation.PrixHeuresConsultationCellFactory;
import com.gymargente.gymmanager.model.consultation.prixheuresconsultation.PrixHeuresConsultationService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

public class AddBlocHeuresController implements Initializable {

    @FXML
    private ChoiceBox<Integer> choiceQuantite;

    @FXML
    private ListView<PrixHeuresConsultation> lvPrix;

    @FXML
    private TextField txtPrix;
    private Client client;
    private PrixHeuresConsultation prix;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        for (int i = 1; i <= PrixHeuresConsultation.NBR_MAX; i++) {
            choiceQuantite.getItems().add(i);
        }

        var allPrixHeures = PrixHeuresConsultationService.getAllPrixHeures();

        lvPrix.setCellFactory(new PrixHeuresConsultationCellFactory());
        lvPrix.setItems(FXCollections.observableArrayList(allPrixHeures));
        lvPrix.setEditable(false);

        choiceQuantite.getSelectionModel().selectedItemProperty().addListener(
                (observableValue, oldInt, newInt) -> {

                    prix = PrixHeuresConsultationService.getPrixQteHeures(newInt);
                    BigDecimal prixQteHeures = prix.prix();
                    var prixTotal = prixQteHeures.multiply(BigDecimal.valueOf(newInt));
                    txtPrix.setText(prixTotal.toString());
                });

        choiceQuantite.setValue(1); // par défaut.
    }

    @FXML
    void handleAcheterBlocHeures(ActionEvent event) {

        BlocsHeures bloc = new BlocsHeures(client, choiceQuantite.getValue(), 0, choiceQuantite.getValue(), prix.id());
        BlocsHeuresService.addBlocHeures(bloc);

        Stage stage = (Stage) lvPrix.getScene().getWindow();
        
        stage.getOnCloseRequest().handle(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
        stage.close();

    }

    public void setClient(Client client) {
        this.client = client;
    }
}
