package com.example.tugasbesar;

import javafx.collections.ObservableList;
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
import java.sql.*;

public class Controller {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label statusLabel;

    @FXML
    private Button loginButton;

    @FXML
    private TableView<User> tableView;

    @FXML
    private TableColumn<User, String> IDTableColumn;

    @FXML
    private TableColumn<User, String> nameTableColumn;

    @FXML
    private TableColumn<User, Double> salaryTableColumn;

    @FXML
    public AnchorPane AnchorLogin;

    private ObservableList<User> data;


    public static Connection getConnection () throws SQLException {
        String db = "eleanor_db";
        String url = "jdbc:mysql://localhost:3306/" + db;

        return DriverManager.getConnection(url, "root", null);
    }

    void connection () {
        try {
            Connection connection = getConnection( );
            System.out.println("Succesfully Connect to DB\nNow verify you are the user!");

        } catch (SQLException e) {
            e.printStackTrace( );
        }
    }

//    public Button getLoginButton () {
//        return loginButton;
//    }

    @FXML
    private void handleLogin (ActionEvent actionEvent) {
        String username = usernameField.getText( );
        String password = passwordField.getText( );

        String preparedStatement = "SELECT * FROM user WHERE nama_user = ?";
//        todo : only for dev, use below when done
        try ( Connection connection = getConnection( );
               PreparedStatement statement = connection.prepareStatement(preparedStatement) ) {
            statement.setString(1, "diana");
            try ( ResultSet resultSet = statement.executeQuery( ) ) {
                if (resultSet.next( )) {
                    String storedPassword = resultSet.getString("password_user");
                    if (storedPassword.equals("12")) {
                        statusLabel.setText("Login successful!");
                        AnchorLogin.getScene( ).getWindow( ).hide( );


                        navigation("dashboard.fxml");
                        return;
                    }
                    if (storedPassword.isEmpty( )) {
                        statusLabel.setText("Password is empty!");
                    }

                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            e.printStackTrace( );
        }
//        try ( Connection connection = getConnection( );
//               PreparedStatement statement = connection.prepareStatement(preparedStatement) ) {
//            statement.setString(1, username);
//            try ( ResultSet resultSet = statement.executeQuery( ) ) {
//                if (resultSet.next( )) {
//                    String storedPassword = resultSet.getString("password_user");
//                    if (storedPassword.equals(password)) {
//                        statusLabel.setText("Login successful!");
//                        AnchorLogin.getScene( ).getWindow( ).hide( );
//
//
//                        navigation("dashboard.fxml");
//                        return;
//                    }
//                    if (storedPassword.isEmpty( )) {
//                        statusLabel.setText("Password is empty!");
//                    }
//
//                }
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace( );
//        }
        //appropriate feedback
        statusLabel.setText("Invalid username or password!");
    }

    private double xOffset = 0;
    private double yOffset = 0;

    //    Navigate through every page when it is called
    private void navigation (String page) throws IOException {
        Parent navigations = FXMLLoader.load(getClass( ).getResource(page));


        Stage stage = new Stage( );
        Scene scene = new Scene(navigations);
        //menyembunyikan windows bar
        stage.initStyle(StageStyle.UNDECORATED);

        //membuat fitur drag and move
        navigations.setOnMousePressed(event -> {
            xOffset = event.getSceneX( );
            yOffset = event.getSceneY( );
        });

        //menggerakan scene
        navigations.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX( ) - xOffset);
            stage.setY(event.getScreenY( ) - yOffset);
        });

        stage.setScene(scene);
        stage.show( );


    }

    @FXML
    public void initialize () {
        connection( );
    }

}

