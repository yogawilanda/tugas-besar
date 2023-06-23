package com.example.tugasbesar;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Optional;

public class Dashboard extends Application {
    public Label label;
    public Button btnNavLogout;
    public AnchorPane Dashboard;
    public TableView tableView;
    public TableColumn IDTableColumn;
    public TableColumn salaryTableColumn;
    public Button btnBookMovie;


    public Label getLabel () {
        return label;
    }

    public void setLabel () {
        this.label = label;
    }

    private static Scene scene;


    @Override
    public void start (Stage stage) throws IOException {
        stage.setScene(scene);
        stage.show( );
        showTable( );
    }

    @FXML
    public static void setRoot (String fxml) throws IOException {
        scene = new Scene(loadFXML("pageView"), 640, 480);
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML (String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load( );
    }


    private double xOffset = 0;
    private double yOffset = 0;

    public void logOut (ActionEvent actionEvent) {
        Dashboard.getScene( ).getWindow( ).hide( );

        try {
            Parent root = FXMLLoader.load(getClass( ).getResource("pageView.fxml"));
            Stage stage = new Stage( );
            Scene scene1 = new Scene(root);

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

            stage.setScene(scene1);
            stage.show( );
        } catch (Exception e) {
            e.printStackTrace( );
        }
    }

    void showTable () {
        tableView.setVisible(true);
        IDTableColumn.setVisible(true);
        salaryTableColumn.setVisible(true);
    }

    public void confirmationBook (ActionEvent actionEvent) {
        Dialog<ButtonType> dialog = new Dialog<>( );
        dialog.getDialogPane( );
        dialog.getDialogPane( ).getButtonTypes( ).addAll(ButtonType.OK, ButtonType.CANCEL);
        dialog.setHeaderText("Are you sure you want to book the ticket?");
        dialog.setTitle("Reserval Confirmation");

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // User clicked OK, handle the booking confirmation
            bookTicket();
        } else {
        }
    }

    private void bookTicket () {
        System.out.println("OK!");

    }
}
