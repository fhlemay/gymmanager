module com.gymargente.gymmanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.xerial.sqlitejdbc;


    opens com.gymargente.gymmanager to javafx.fxml;
    exports com.gymargente.gymmanager;
}