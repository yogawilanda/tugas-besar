package com.example.tugasbesar;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class App extends Application {
    private double xOffset = 0;
    private double yOffset = 0;

    final String fileName = "pageView.fxml";

    @Override
    public void start (Stage stage) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass( ).getResource(fileName));
//        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fileName)));
        Parent root = loader.load( );

        Scene scene = new Scene(root);
        stage.setTitle("Eleanor - Desktop App Ticket Reservation");
        //menyembunyikan windows bar
        stage.initStyle(StageStyle.UNDECORATED);

        //membuat fitur drag and move
        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX( );
            yOffset = event.getSceneY( );
        });

        //menggerakan scene
        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX( ) - xOffset);
            stage.setY(event.getScreenY( ) - yOffset);
        });

        stage.setScene(scene);
        stage.show( );
    }


    public static void main (String[] args) {
        launch( );
    }


}