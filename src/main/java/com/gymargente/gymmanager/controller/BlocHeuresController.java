package com.gymargente.gymmanager.controller;

import com.gymargente.gymmanager.GymManager;
import com.gymargente.gymmanager.model.client.Client;
import com.gymargente.gymmanager.model.consultation.blocsheures.BlocHeuresCellFactory;
import com.gymargente.gymmanager.model.consultation.blocsheures.BlocsHeures;
import com.gymargente.gymmanager.model.consultation.blocsheures.BlocsHeuresException;
import com.gymargente.gymmanager.model.consultation.blocsheures.BlocsHeuresService;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableIntegerValue;
import javafx.beans.value.ObservableObjectValue;
import javafx.beans.value.ObservableStringValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BlocHeuresController implements Initializable {

    @FXML private Button btnSupprimer;
    @FXML private Label lblDisponibles;
    @FXML private Label lblReservees;
    @FXML private ListView<BlocsHeures> lvBlocHeures;
    private ObservableList<BlocsHeures> blocsHeuresList;
//    private ObservableObjectValue<BlocsHeures> sumBlocsHeures;
    private SimpleStringProperty sumHeuresRestantes;
    private SimpleStringProperty sumHeuresReservees;

    private Client client;
    private BlocsHeures selectedBloc;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        Platform.runLater(() -> btnSupprimer.requestFocus());

        blocsHeuresList = FXCollections.observableArrayList();
        sumHeuresRestantes = new SimpleStringProperty("0");
        sumHeuresReservees = new SimpleStringProperty("0");


        lvBlocHeures.setItems(blocsHeuresList);
        lvBlocHeures.setCellFactory(new BlocHeuresCellFactory());
        lvBlocHeures.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        lvBlocHeures.getSelectionModel().selectedItemProperty().addListener(
                (observableValue, oldBloc, newBloc) -> {
                    if(newBloc != null) selectedBloc = newBloc;
                });

        lblDisponibles.textProperty().bind(sumHeuresRestantes);
        lblReservees.textProperty().bind(sumHeuresReservees);

    }

    public void setClient(Client client) {

        this.client = client;

        refresh();
    }

    private void refresh() {
        blocsHeuresList.clear();
        blocsHeuresList.addAll(BlocsHeuresService.getAllBlocsHeuresFor(this.client));

        BlocsHeures sumBlocsHeures = BlocsHeuresService.getSumBlocsHeuresFor(this.client);

        sumHeuresReservees.set(Integer.toString(sumBlocsHeures.heuresReservees()));
        sumHeuresRestantes.set(Integer.toString(sumBlocsHeures.heuresRestantes()));
    }

    @FXML
    void handleAjouterBlocHeures(ActionEvent event) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/gestionCompte/addBlocsHeures.fxml"));
            root = loader.load();
            AddBlocHeuresController controller = loader.getController();
            controller.setClient(client);
            Stage stage = new Stage();
            stage.setTitle("Ajouter un bloc d'heures.");
            stage.setScene(new Scene(root));
            stage.show();
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent windowEvent) {
                    refresh();
                }
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void handleSupprimerBlocHeures(ActionEvent event) {

        // Business logic dans le controlleur --> NO!
        // Doit-on la placer dans le service ou dans le modèle?
        // «The business logic should be placed in the model, and we should be
        // aiming for fat models and skinny controllers.»
        // https://softwareengineering.stackexchange.com/questions/165444/where-to-put-business-logic-in-mvc-design
        // On peut voir le service comme une extension du modèle.

        try {
            BlocsHeuresService.deleteBlocsHeures(selectedBloc);
            refresh();
        } catch (BlocsHeuresException e) {
            Alert message = new Alert(Alert.AlertType.INFORMATION);
            message.setTitle("Remboursement d'un bloc d'heures");
            message.setHeaderText("Remboursement interdit.");
            message.setContentText(e.getMessage());

            message.showAndWait();
        }
    }
}
