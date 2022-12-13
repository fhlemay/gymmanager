package com.gymargente.gymmanager.controller;

import com.gymargente.gymmanager.GymManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController extends AnchorPane implements Initializable {


    @FXML
    private Button btnLogin;

    @FXML
    private Label lblWarning;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    private GymManager application;

    public void setApp(GymManager application){
        this.application = application;
    }

    @Override
    public void initialize (URL location, ResourceBundle resources){
        Platform.runLater(() -> btnLogin.requestFocus()); // Le focus est initialement sur le bouton.
        lblWarning.setText("");
        txtUsername.setPromptText("Identifiant");
        txtPassword.setPromptText("Mot de passe");
    }

    @FXML
    void handleLogin(ActionEvent event) {

        lblWarning.setVisible(true);

        if (application == null){
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            lblWarning.setText("Hello " + txtUsername.getText());
        } else {
            if (txtUsername.getText().isBlank()) {
                lblWarning.setText("L'identifiant est vide.");
                return;
            }
            if (!application.userLogging(txtUsername.getText(), txtPassword.getText())){
                lblWarning.setText("Identifiant / mot de passe incorrect.");
            }
        }
    }
}
