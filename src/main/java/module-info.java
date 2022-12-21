module com.gymargente.gymmanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.xerial.sqlitejdbc;


    opens com.gymargente.gymmanager to javafx.fxml;
    exports com.gymargente.gymmanager;
    exports com.gymargente.gymmanager.db;
    opens com.gymargente.gymmanager.db to javafx.fxml;
    exports com.gymargente.gymmanager.model.utilisateur;
    opens com.gymargente.gymmanager.model.utilisateur to javafx.fxml;
    exports com.gymargente.gymmanager.model.client;
    opens com.gymargente.gymmanager.model.client to javafx.fxml;
    exports com.gymargente.gymmanager.model.consultation.blocsheures;
    opens com.gymargente.gymmanager.model.consultation.blocsheures to javafx.fxml;
    exports com.gymargente.gymmanager.controller;
    opens com.gymargente.gymmanager.controller to javafx.fxml;
}