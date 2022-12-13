package com.gymargente.gymmanager.controller;

import com.gymargente.gymmanager.GymManager;
import com.gymargente.gymmanager.model.utilisateur.UtilisateurService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainController extends AnchorPane implements Initializable {

    private GymManager application;
    @FXML
    private TabPane tabPaneAccessControl;
    @FXML
    private Tab tabAdministration;

    @FXML
    private Tab tabConsultation;

    @FXML
    private Tab tabGestionCompte;

    public void setApp(GymManager application) {
        if (application == null) System.out.println("Application est nulle. Pourqoi?");
        this.application = application;

        giveAccessByUserProfil();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        Platform.runLater(() -> btnLogin.requestFocus()); // Le focus est initialement sur le bouton.
        tabAdministration.setDisable(true);
        tabConsultation.setDisable(true);
        tabGestionCompte.setDisable(true);
    }

    private void giveAccessByUserProfil() {
        int userId = application.getLoggedUser().id();
        List<String> userProfils = UtilisateurService.getProfiles(userId);
        System.out.println(userProfils);

        // Pour la sélection du tab.
        SingleSelectionModel<Tab> selectionModel = tabPaneAccessControl.getSelectionModel();

        for (String profil : userProfils) {
            switch (profil) {
                case "GESTIONNAIRE":
                    tabGestionCompte.setDisable(false);
                    selectionModel.select(tabGestionCompte);
                    break;
                case "SPECIALISTE":
                    tabConsultation.setDisable(false);
                    selectionModel.select(tabConsultation);
                    break;
                case "ADMINISTRATEUR":
                    tabAdministration.setDisable(false);
                    selectionModel.select(tabAdministration);
                    break;
            }
        }
    }
    @FXML
    void handleDeconnecter(ActionEvent event) {
        System.out.println("On veut déconnecter");
        application.userLogout();
    }
}
