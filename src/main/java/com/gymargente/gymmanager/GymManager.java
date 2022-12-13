package com.gymargente.gymmanager;

import com.gymargente.gymmanager.controller.LoginController;
import com.gymargente.gymmanager.controller.MainController;
import com.gymargente.gymmanager.db.Database;
import com.gymargente.gymmanager.model.utilisateur.Utilisateur;
import com.gymargente.gymmanager.model.utilisateur.UtilisateurService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GymManager extends Application {

    private Group root = new Group(); //
    private Utilisateur loggedUser;
    private Stage stage;

    public static void main(String[] args) {

        // TODO : est-e le bon endroit pour se connecte a la db ?
        try {
            Database.getInstance().connect();
        } catch (SQLException e) {
            System.out.println("Ne peut pas se connecter à la base de données.");
            return;
        }
        System.out.println("Base de données connectée.");

        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        stage = primaryStage;
        Parent parent = createContent(); // retourne root modifié par gotoLogin.
        Scene scene = new Scene(parent); // Crée une Scene avec Root.
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public Parent createContent() { // Quelle est l'utilité ?
        gotoLogin(); // modifie root
        return root;
    }

    public boolean userLogging(String userId, String password){

        Optional<Utilisateur> user = UtilisateurService.authenticate(userId, password);
        if(user.isPresent()){
            loggedUser = user.get();
            gotoMain();
            System.out.println(loggedUser);
            return true;
        } else {
            return false;
        }
    }

    public void userLogout(){
        loggedUser = null;
        gotoLogin();
    }

    public Utilisateur getLoggedUser() {
        return loggedUser;
    }

    private void gotoMain () {
        try {
            MainController main =
                    (MainController) replaceSceneContent("/fxml/main.fxml");
            main.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void gotoLogin() {
        try {
            LoginController login =
                    (LoginController) replaceSceneContent("/fxml/login.fxml");
            login.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = GymManager.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(GymManager.class.getResource(fxml));
        AnchorPane page;

        try {
            page = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        }
        root.getChildren().clear();
        root.getChildren().addAll(page);
        stage.sizeToScene();

        return (Initializable) loader.getController();
    }
}
