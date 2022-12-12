package com.gymargente.gymmanager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GymManager extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
        final Parent parent = loader.load();
        final Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.setWidth(320);
        stage.setHeight(240);
        stage.show();
    }


//        public void start_chat(Stage stage) throws Exception {
//        final FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main/main.fxml"));
//
//        final Parent parent = loader.load();
//        MainController controller = loader.getController();
//
//        final Scene scene = new Scene(parent);
//        stage.setOnCloseRequest(windowEvent -> {
//            controller.onClose();
//            ThreadPool.shutDown();
//        });
//        stage.setScene(scene);
//        stage.setTitle("Chat - ITnetwork tutorial");
//        stage.setWidth(640);
//        stage.setHeight(420);
//        stage.show();
//    }
}