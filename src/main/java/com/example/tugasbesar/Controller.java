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
    public ToggleButton playToggleButton;
    public Button openWebUrlButton2;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label statusLabel;

    @FXML
    private TableView<Person> tableView;

    @FXML
    public TableColumn<Person, String> IDTableColumn;

    @FXML
    public TableColumn<Person, String> nameTableColumn;

    @FXML
    public TableColumn<Person, String> salaryTableColumn;

    private ObservableList<Person> data;

    public String username;
    public String password;

    public void getUsername () {
        username = usernameField.getText( );
    }

    public void getPassword () {
        password = passwordField.getText( );
    }

    String lagu = "F:\\Coding\\java\\tugas-besar\\src\\main\\java\\com\\example\\tugasbesar\\ninuwibu.mp3";

    private MediaPlayer mediaPlayer;

//    public void initialize() {
//        // Initialize other components...
//
//        // Load and play the music when the app starts
//        String musicFile = lagu ;
//        Media media = new Media(new File(musicFile).toURI().toString());
//        mediaPlayer = new MediaPlayer(media);
//        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
//        mediaPlayer.play();
//    }

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

    @FXML
    public void playMedia (ActionEvent actionEvent) {
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
                panelDataFrame.getChildren( ).add(mediaView);

                mediaPlayer.setOnEndOfMedia(() -> playToggleButton.setSelected(false));
            }
        }
    }


    String database = "eleanor_db";
    String url = "jdbc:mysql://localhost:3306/" + database;

    public Connection getConnection () throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    private Connection connection;

    void connection () {
        try {
            connection = getConnection( ); // Store the connection in the member variable
            loginScene.setVisible(false);
            imgAnchorLogin.setVisible(false);
        } catch (SQLException e) {
            statusLabel.setText("Koneksi Gagal, Cek Stacktrace!");
            e.printStackTrace( );
            hideTable( );
        }
    }

    public void login () {
        getUsername( );
        getPassword( );
        connection( );
        controlBtnDataFrame.setVisible(true);
        tableView.setVisible(false);
        statusLabel.setText("Login successful!");

    }

    private void dataFramer (Connection connection) throws SQLException {
        data = FXCollections.observableArrayList( );

        // Retrieve data from the database
        String query = "SELECT * FROM pegawai";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery( );
        while (resultSet.next( )) {
            String name = resultSet.getString("namaPegawai");
            int id = resultSet.getInt("idPegawai");
            double salary = resultSet.getDouble("gaji");
            data.add(new Person(name, salary, id));
        }

        // Set up the TableView and its columns
        tableView.setItems(data);
        IDTableColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        salaryTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue( ).getSalary( ) + ""));
    }

//    public void addRecord (ActionEvent actionEvent) {
//        try ( Connection connection = getConnection( ) ) {
//            // Prepare the INSERT statement
//            String query = "INSERT INTO pegawai (namaPegawai, gaji) VALUES (?, ?)";
//            PreparedStatement statement = connection.prepareStatement(query);
//            statement.setString(1, "John Doe"); // Replace with actual name
//            statement.setDouble(2, 5000.0); // Replace with actual salary
//
//            // Execute the INSERT statement
//            int affectedRows = statement.executeUpdate( );
//            if (affectedRows > 0) {
//                System.out.println("Record inserted successfully!");
//            } else {
//                System.out.println("Failed to insert record!");
//            }
//
//            // Refresh the data in the TableView
//            dataFramer(connection);
//        } catch (SQLException e) {
//            e.printStackTrace( );
//        }
//    }

    public void addRecord (ActionEvent actionEvent) {
        Dialog<Pair<String, Double>> dialog = new Dialog<>( );
        dialog.setTitle("Add Record");
        dialog.setHeaderText("Enter the name and salary for the new record:");

        // Set up the text fields
        TextField nameTextField = new TextField( );
        TextField salaryTextField = new TextField( );
        GridPane gridPane = new GridPane( );
        gridPane.add(new Label("Name:"), 0, 0);
        gridPane.add(nameTextField, 1, 0);
        gridPane.add(new Label("Salary:"), 0, 1);
        gridPane.add(salaryTextField, 1, 1);
        dialog.getDialogPane( ).setContent(gridPane);

        // Add buttons to the dialog
        ButtonType addButton = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane( ).getButtonTypes( ).addAll(addButton, ButtonType.CANCEL);

        // Set the result converter to get the entered name and salary
        dialog.setResultConverter(buttonType -> {
            if (buttonType == addButton) {
                String name = nameTextField.getText( ).trim( );
                double salary = Double.parseDouble(salaryTextField.getText( ).trim( ));
                return new Pair<>(name, salary);
            }
            return null;
        });

        // Show the dialog and wait for user input
        Optional<Pair<String, Double>> result = dialog.showAndWait( );

        // Process the entered name and salary
        result.ifPresent(pair -> {
            try ( Connection connection = getConnection( ) ) {
                // Prepare the INSERT statement
                String query = "INSERT INTO pegawai (namaPegawai, gaji) VALUES (?, ?)";
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
        try ( Connection connection = getConnection( ) ) {
            // Prepare the UPDATE statement
            String query = "UPDATE pegawai SET gaji = ? WHERE namaPegawai = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDouble(1, 50.0); // Replace with the updated salary
            statement.setString(2, "John Doe"); // Replace with the name of the record to update

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
    }

//    public void deleteRecord (ActionEvent actionEvent) {
//        try ( Connection connection = getConnection( ) ) {
//
//            // Prepare the UPDATE statement
//            String query = "DELETE FROM pegawai WHERE namaPegawai = ?";
//            PreparedStatement statement = connection.prepareStatement(query);
//            statement.setString(1, "John Doe"); // Replace with the name of the record to update
//
//            // Execute the UPDATE statement
//            int affectedRows = statement.executeUpdate( );
//            if (affectedRows > 0) {
//                System.out.println("Record deleted successfully!");
//            } else {
//                System.out.println("Failed to update record!");
//            }
//
//            // Refresh the data in the TableView
//            dataFramer(connection);
//        } catch (SQLException e) {
//            e.printStackTrace( );
//        }
//    }


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
                String query = "DELETE FROM pegawai WHERE namaPegawai = ?";
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

            // Retrieve and display the data in the table
            dataFramer(connection);

            // Enable editing of the table cells
            tableView.setEditable(false);
            nameTableColumn.setCellFactory(TextFieldTableCell.forTableColumn( ));
            salaryTableColumn.setCellFactory(TextFieldTableCell.forTableColumn( ));

            // Set up event handlers for committing edits
            nameTableColumn.setOnEditCommit(event -> {
                Person person = event.getRowValue( );
                person.setName(event.getNewValue( ));
                updatePerson(connection, person);
            });

            salaryTableColumn.setOnEditCommit(event -> {
                Person person = event.getRowValue( );
                person.setSalary(Double.parseDouble(event.getNewValue( )));
                updatePerson(connection, person);
            });
        } catch (SQLException e) {
            e.printStackTrace( );
        }

    }

    private void updatePerson (Connection connection, Person person) {
        try {
            // Prepare the UPDATE statement
            String query = "UPDATE pegawai SET gaji = ? WHERE idPegawai = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDouble(1, person.getSalary( ));
            statement.setInt(2, person.getID( ));

            // Execute the UPDATE statement
            int affectedRows = statement.executeUpdate( );
            if (affectedRows > 0) {
                System.out.println("Record updated successfully!");
            } else {
                System.out.println("Failed to update record!");
            }
        } catch (SQLException e) {
            e.printStackTrace( );
        }
    }

    public static class Person {
        private String name;
        private double salary;
        private int id;

        /**
         * @param name   fetching name of the user
         * @param salary for fetching data salary
         * @param id     fetching unique identifier of user in that database to prevent duplication
         */
        public Person (String name, double salary, int id) {
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