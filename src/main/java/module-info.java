module com.example.tugasbesar {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.media;
    requires javafx.web;


    opens com.example.tugasbesar to javafx.fxml;
    exports com.example.tugasbesar;
}