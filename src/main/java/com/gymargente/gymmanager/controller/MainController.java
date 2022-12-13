package com.gymargente.gymmanager.controller;

import com.gymargente.gymmanager.GymManager;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController extends AnchorPane implements Initializable {

    private GymManager application;

    public void setApp(GymManager application){
        this.application = application;
    }

    @Override
    public void initialize (URL location, ResourceBundle resources){
//        Platform.runLater(() -> btnLogin.requestFocus()); // Le focus est initialement sur le bouton.
//        lblWarning.setText("");
//        txtUsername.setPromptText("Identifiant");
//        txtPassword.setPromptText("Mot de passe");
    }
}
