module com.example.serveur {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.serveur to javafx.fxml;
    exports com.example.serveur;
}