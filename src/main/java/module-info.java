module com.gymargente.gymmanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.xerial.sqlitejdbc;


    opens com.gymargente.gymmanager to javafx.fxml;
    exports com.gymargente.gymmanager;
    exports com.gymargente.gymmanager.persistence;
    opens com.gymargente.gymmanager.persistence to javafx.fxml;
    exports com.gymargente.gymmanager.user;
    opens com.gymargente.gymmanager.user to javafx.fxml;
}