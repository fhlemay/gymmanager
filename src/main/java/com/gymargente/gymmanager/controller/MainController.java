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
    private MenuBar menuUtilisateur;
    @FXML
    private TabPane tabPaneAccessControl;
    @FXML
    private Tab tabAdministration;

    @FXML
    private Tab tabConsultation;

    @FXML
    private Tab tabGestionCompte;

    public void setApp(GymManager application) {
        if (application == null) System.out.println("Application est nulle. Pourquoi?");
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

        String credentials = application.getLoggedUser().identifiant() + " >";

        List<String> userProfils = UtilisateurService.getProfiles(userId);
//        System.out.println(userProfils);

        // Pour la sélection du tab.
        SingleSelectionModel<Tab> selectionModel = tabPaneAccessControl.getSelectionModel();

        for (String profil : userProfils) {
            switch (profil) {
                case "GESTIONNAIRE":
                    tabGestionCompte.setDisable(false);
                    selectionModel.select(tabGestionCompte);
                    credentials += " : gestionnaire";
                    break;
                case "SPECIALISTE":
                    tabConsultation.setDisable(false);
                    selectionModel.select(tabConsultation);
                    credentials += " : spécialiste";
                    break;
                case "ADMINISTRATEUR":
                    tabAdministration.setDisable(false);
                    selectionModel.select(tabAdministration);
                    credentials += " : administrateur/trice";
                    break;
            }
        }

        menuUtilisateur.getMenus().get(0).setText(credentials); // Nom et profils de l'utilisateur dans le menu.
    }
    @FXML
    void handleDeconnecter(ActionEvent event) {
        application.userLogout();
    }
}
