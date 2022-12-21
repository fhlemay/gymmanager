package com.gymargente.gymmanager.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class AbonnementController {

    @FXML
    void handleAjouterAbonnement(ActionEvent event) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/gestionCompte/plan.fxml"));
            root = loader.load();
//            AddBlocHeuresController controller = loader.getController();
//            controller.setClient(client);
            Stage stage = new Stage();
            stage.setTitle("Plans");
            stage.setScene(new Scene(root));
            stage.show();
//            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
//                @Override
//                public void handle(WindowEvent windowEvent) {
//                    refresh();
//                }
//            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
