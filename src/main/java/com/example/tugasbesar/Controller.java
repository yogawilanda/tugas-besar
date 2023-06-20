package com.example.tugasbesar;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.Optional;


public class Controller {
    @FXML
    public AnchorPane panelDataFrame;
    public AnchorPane loginScene;
    public Image imgScene;
    public AnchorPane imgAnchorLogin;
    public Button logout;
    public ToggleButton btnshowTable;
    public Button btnAddNewTable;
    public Button btnUpdateUser;
    public HBox controlBtnDataFrame;
    public Button btnDeleteUser;
    String lagu = "F:\\Coding\\java\\tugas-besar\\src\\main\\java\\com\\example\\tugasbesar\\ninuwibu.mp3";
    public ToggleButton playToggleButton;
    public Button openWebUrlButton2;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label statusLabel;

    @FXML
    private TableView<User> tableView;

    @FXML
    public TableColumn<User, String> IDTableColumn;

    @FXML
    public TableColumn<User, String> nameTableColumn;

    @FXML
    public TableColumn<User, String> salaryTableColumn;

    private ObservableList<User> data;

    public String username;
    public String password;

    public void getUsername () {
        username = usernameField.getText( );
    }

    public void getPassword () {
        password = passwordField.getText( );
    }

    private MediaPlayer mediaPlayer;

    @FXML
    private WebView webView;
    private WebEngine webEngine;

    @FXML
    private Button openWebUrlButton;

    @FXML
    private void openWebUrl2 (ActionEvent event) {
        // Create a new stage
        Stage stage = new Stage( );
        stage.setTitle("YouTube Video");

        // Create a WebView and WebEngine
        WebView webView = new WebView( );
        WebEngine webEngine = webView.getEngine( );

        // Set the YouTube video embed URL as the source
        String videoUrl = "https://youtu.be/Ony6_S4J-zc";
        webEngine.load(videoUrl);

        // Set the WebView as the content of the stage
        stage.setScene(new Scene(webView));

        // Show the stage
        stage.show( );
    }

    @FXML
    private void openWebUrl (ActionEvent event) {
        // Create a new stage
        Stage stage = new Stage( );
        stage.setTitle("Web View");

        // Create a WebView and WebEngine
        WebView webView = new WebView( );
        WebEngine webEngine = webView.getEngine( );

        // Load the web URL in the WebEngine
        webEngine.load("https://www.youtube.com");

        // Set the WebView as the content of the stage
        stage.setScene(new Scene(webView));

        // Show the stage
        stage.show( );
    }


    @FXML
    private Button playButton;


    //    function playMedia membutuhkan trigger dari ActionEvent dengan menggunakan klik pada atau enter pada keyboard
    //    Alur kerjanya :
    //    1. memutar audio berdasarkan path yang dituju, apabila ditemukan, maka akan memutar
    //    2. Jika tidak ditemukan, akan menggunakan memberikan informasi bahwa musik tidak ditemukan.
    @FXML
    public void playAudio (ActionEvent actionEvent) {
        String mediaPath = lagu;


        if (mediaPlayer != null && mediaPlayer.getStatus( ) == MediaPlayer.Status.PLAYING) {
            mediaPlayer.pause( );
        } else {
            if (mediaPlayer != null && mediaPlayer.getStatus( ) == MediaPlayer.Status.PAUSED) {
                mediaPlayer.play( );
            } else {
                Media media = new Media(new File(mediaPath).toURI( ).toString( ));

                if (mediaPlayer != null) {
                    mediaPlayer.dispose( );
                }

                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setAutoPlay(true);

                MediaView mediaView = new MediaView(mediaPlayer);

                mediaPlayer.setOnEndOfMedia(() -> playToggleButton.setSelected(false));
            }
        }

        if (mediaPlayer == null) {

        }
    }


    public Connection getConnection () throws SQLException {
        String database = "eleanor_db";
        String url = "jdbc:mysql://localhost:3306/" + database;
        return DriverManager.getConnection(url, "admin_eleanor", null);
    }


    void connection () {
        try {
            Connection connection = getConnection( ); // Store the connection in the member variable
            loginScene.setVisible(false);
        } catch (SQLException e) {
            statusLabel.setText("Koneksi Gagal, Cek Stacktrace!");
            e.printStackTrace( );
            hideTable( );
        }
    }

//    public void login () {
//        connection( );
//        getUsername( );
//        getPassword( );
//
//
//        try ( Connection connection = getConnection( );
//              PreparedStatement statement = connection.prepareStatement("SELECT * FROM user WHERE nama_user = ?");
//        ) {
//            statement.setString(1, username);
//            ResultSet resultSet = statement.executeQuery( );
//
//            if (resultSet.next( )) {
//                String storedPassword = resultSet.getString("password_user");
//                // Perform password comparison
//                if (storedPassword.equals(password)) {
//
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace( );
//        }
//
//
//        controlBtnDataFrame.setVisible(true);
//        tableView.setVisible(false);
//        statusLabel.setText("Login successful!");
//
//    }

    public void login() {
        getUsername();
        getPassword();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM user WHERE nama_user = ?")) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String storedPassword = resultSet.getString("password_user");
                    // Perform password comparison
                    if (storedPassword.equals(password)) {
                        controlBtnDataFrame.setVisible(true);
                        tableView.setVisible(false);
                        statusLabel.setText("Login successful!");
                        return;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // If authentication fails or an exception occurs, show appropriate feedback
        statusLabel.setText("Invalid username or password!");
    }


    private void dataFramer (Connection connection) throws SQLException {
        data = FXCollections.observableArrayList( );

        // Retrieve data from the database
        String query = "SELECT * FROM user";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery( );
        while (resultSet.next( )) {
            String name = resultSet.getString("nama_user");
            int id = resultSet.getInt("id_user");
//            String salary = resultSet.getDouble("_user");
//            data.add(new User(name, salary, id));
        }

        // Set up the TableView and its columns
        tableView.setItems(data);
        IDTableColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        salaryTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue( ).getSalary( ) + ""));
    }

    public TextField nameTextField;
    public Dialog<Pair<String, Double>> dialog;

    public void addRecord (ActionEvent actionEvent) {
        dialog = new Dialog<>( );
        dialog.setTitle("Add Record");
        dialog.setHeaderText("Enter the name and salary for the new record:");

        // Set up the text fields

        nameTextField = new TextField( );
        TextField jabatan = new TextField( );
        GridPane gridPane = new GridPane( );
        gridPane.add(new Label("Name:"), 0, 0);
        gridPane.add(nameTextField, 1, 0);
        gridPane.add(new Label("Salary:"), 0, 1);
        gridPane.add(jabatan, 1, 1);
        dialog.getDialogPane( ).setContent(gridPane);

        // Add buttons to the dialog
        ButtonType addButton = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane( ).getButtonTypes( ).addAll(addButton, ButtonType.CANCEL);

//        todo: buat ulang
        // Set the result converter to get the entered name and salary
        dialog.setResultConverter(buttonType -> {
            if (buttonType == addButton) {
                String name = nameTextField.getText( ).trim( );
                String salary = jabatan.getText( );
//                return new Pair<>(name, salary);
            }
            return null;
        });

        // Show the dialog and wait for user input
        Optional<Pair<String, Double>> result = dialog.showAndWait( );

        // Process the entered name and salary
        result.ifPresent(pair -> {
            try ( Connection connection = getConnection( ) ) {
                // Prepare the INSERT statement
                String query = "INSERT INTO user (nama_user, kode_user) VALUES (?, ?)";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, pair.getKey( )); // Name
                statement.setDouble(2, pair.getValue( )); // Salary

                // Execute the INSERT statement
                int affectedRows = statement.executeUpdate( );
                if (affectedRows > 0) {
                    System.out.println("Record inserted successfully!");
                } else {
                    System.out.println("Failed to insert record!");
                }

                // Refresh the data in the TableView
                dataFramer(connection);
            } catch (SQLException e) {
                e.printStackTrace( );
            }
        });
    }

    private void hideTable () {
        panelDataFrame.setVisible(false);
    }

    public void moveToNext (ActionEvent actionEvent) throws IOException, SQLException {
        getConnection( ).close( );
        tableView.setVisible(false);
        loginScene.setVisible(true);
    }

    public void updateRecord (ActionEvent actionEvent) {
        Dialog<Pair<String, Double>> dialog = new Dialog<>( );
        dialog.setTitle("Update Record");
        dialog.setHeaderText("Enter the name and updated salary for the record:");

        // Set up the text fields
        TextField nameTextField = new TextField( );
        TextField salaryTextField = new TextField( );
        GridPane gridPane = new GridPane( );
        gridPane.add(new Label("Name:"), 0, 0);
        gridPane.add(nameTextField, 1, 0);
        gridPane.add(new Label("Updated Salary:"), 0, 1);
        gridPane.add(salaryTextField, 1, 1);
        dialog.getDialogPane( ).setContent(gridPane);

        // Add buttons to the dialog
        ButtonType updateButton = new ButtonType("Update", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane( ).getButtonTypes( ).addAll(updateButton, ButtonType.CANCEL);

        // Set the result converter to get the entered name and updated salary
        dialog.setResultConverter(buttonType -> {
            if (buttonType == updateButton) {
                String name = nameTextField.getText( ).trim( );
                double updatedSalary = Double.parseDouble(salaryTextField.getText( ).trim( ));
                return new Pair<>(name, updatedSalary);
            }
            return null;
        });

        // Show the dialog and wait for user input
        Optional<Pair<String, Double>> result = dialog.showAndWait( );

        // Process the entered name and updated salary
        result.ifPresent(pair -> {
            try ( Connection connection = getConnection( ) ) {
                // Prepare the UPDATE statement
                String query = "UPDATE user SET gaji = ? WHERE nama_user = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setDouble(1, pair.getValue( )); // Updated Salary
                statement.setString(2, pair.getKey( )); // Name

                // Execute the UPDATE statement
                int affectedRows = statement.executeUpdate( );
                if (affectedRows > 0) {
                    System.out.println("Record updated successfully!");
                } else {
                    System.out.println("Failed to update record!");
                }

                // Refresh the data in the TableView
                dataFramer(connection);
            } catch (SQLException e) {
                e.printStackTrace( );
            }
        });
    }


    public void deleteRecord (ActionEvent actionEvent) {
        Dialog<String> dialog = new Dialog<>( );
        dialog.setTitle("Delete Record");
        dialog.setHeaderText("Enter the name of the record to delete:");

        // Set up the text field
        TextField deleteTextField = new TextField( );
        dialog.getDialogPane( ).setContent(deleteTextField);

        // Add buttons to the dialog
        ButtonType deleteButton = new ButtonType("Delete", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane( ).getButtonTypes( ).addAll(deleteButton, ButtonType.CANCEL);

        // Set the result converter to get the entered name
        dialog.setResultConverter(buttonType -> {
            if (buttonType == deleteButton) {
                return deleteTextField.getText( ).trim( );
            }
            return null;
        });

        // Show the dialog and wait for user input
        Optional<String> result = dialog.showAndWait( );

        // Process the entered name
        result.ifPresent(name -> {
            try ( Connection connection = getConnection( ) ) {
                // Prepare the DELETE statement
                String query = "DELETE FROM user WHERE nama_user = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, name);

                // Execute the DELETE statement
                int affectedRows = statement.executeUpdate( );
                if (affectedRows > 0) {
                    System.out.println("Record deleted successfully!");
                } else {
                    System.out.println("No record found with the specified name!");
                }

                // Refresh the data in the TableView
                dataFramer(connection);
            } catch (SQLException e) {
                e.printStackTrace( );
            }
        });
    }


    public void showTable (ActionEvent actionEvent) {
        try ( Connection connection = getConnection( ) ) {
            connection( );
            tableView.setVisible(true);

            // Ensure the connection is open
            if (connection.isClosed( )) {
                // Handle the connection closure and return or throw an exception
                // depending on your requirements
                // Example: return or throw an exception indicating the connection closure
                return;
            }

            // Retrieve and display the data in the table
            dataFramer(connection);

            // Enable editing of the table cells
            tableView.setEditable(true);
            nameTableColumn.setCellFactory(TextFieldTableCell.forTableColumn( ));
            salaryTableColumn.setCellFactory(TextFieldTableCell.forTableColumn( ));

            // Set up event handlers for committing edits
            nameTableColumn.setOnEditCommit(event -> {
                User user = event.getRowValue( );
                user.setName(event.getNewValue( ));
                updatePerson(connection, user);
            });

            salaryTableColumn.setOnEditCommit(event -> {
                User user = event.getRowValue( );
                user.setSalary(Double.parseDouble(event.getNewValue( )));
                updatePerson(connection, user);
            });
        } catch (SQLException e) {
            e.printStackTrace( );
        }
    }

    private void updatePerson (Connection connection, User user) {
        try {
            // Check if the connection is closed or invalid
            if (connection == null || connection.isClosed( )) {
                // Handle the connection issue appropriately
                System.out.println("Connection is closed or invalid.");
                return;
            }

            // Prepare the SQL statement
            String sql = "UPDATE user SET gaji = ? WHERE iduser = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, user.getSalary( ));
            statement.setInt(2, user.getID( ));

            // Execute the SQL statement
            int rowsAffected = statement.executeUpdate( );

            // Check the result of the update operation
            if (rowsAffected > 0) {
                System.out.println("Person updated successfully.");
            } else {
                System.out.println("Failed to update person.");
            }

            // Close the statement (optional, but recommended)
            statement.close( );
        } catch (SQLException e) {
            e.printStackTrace( );
        }
    }


    public static class User {
        private String name;
        private double salary;
        private int id;

        /**
         * @param name   fetching name of the user
         * @param salary for fetching data salary
         * @param id     fetching unique identifier of user in that database to prevent duplication
         */
        public User (String name, double salary, int id) {
            this.name = name;
            this.salary = salary;
            this.id = id;
        }

        public String getName () {
            return name;
        }

        public void setName (String name) {
            this.name = name;
        }

        public double getSalary () {
            return salary;
        }

        public void setSalary (double salary) {
            this.salary = salary;
        }

        public int getID () {
            return id;
        }

        public void setID (int id) {
            this.id = id;
        }
    }
}