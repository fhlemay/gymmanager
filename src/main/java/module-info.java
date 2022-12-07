module com.gymargente.gymmanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.xerial.sqlitejdbc;


    opens com.gymargente.gymmanager to javafx.fxml;
    exports com.gymargente.gymmanager;
    exports com.gymargente.gymmanager.db;
    opens com.gymargente.gymmanager.db to javafx.fxml;
    exports com.gymargente.gymmanager.model.user;
    opens com.gymargente.gymmanager.model.user to javafx.fxml;
    exports com.gymargente.gymmanager.controller;
    opens com.gymargente.gymmanager.controller to javafx.fxml;
}