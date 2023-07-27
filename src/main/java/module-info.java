module com.example.tugasbesar {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.media;
    requires javafx.web;
//    requires annotations;


    opens com.example.tugasbesar to javafx.fxml;
    exports com.example.tugasbesar;
    exports com.example.tugasbesar.film;
    opens com.example.tugasbesar.film to javafx.fxml;
    exports com.example.tugasbesar.reservasi;
    opens com.example.tugasbesar.reservasi to javafx.fxml;
}