package com.example.tugasbesar;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

import java.sql.*;

public class HelloController {
    @FXML
    public AnchorPane panelDataFrame;
    public AnchorPane loginScene;
    public Image imgScene;
    public AnchorPane imgAnchorLogin;

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


    @FXML
    public void login() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String database = "eleanor_db";
        String url = "jdbc:mysql://localhost:3306/" + database;

        // mencoba menyambungkan database ke dalam aplikasi.
        try ( Connection connection = DriverManager.getConnection(url, username, password) ) {
            statusLabel.setText("Login successful!");

            data = FXCollections.observableArrayList();

            // Retrieve data from the database
            String query = "SELECT * FROM pegawai";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("namaPegawai");
                int id = resultSet.getInt("idPegawai");
                double salary = resultSet.getDouble("gaji");
                data.add(new Person(name, salary, id));
            }

            // Set up the TableView and its columns
            tableView.setItems(data);
            IDTableColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
            nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            salaryTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSalary() + ""));
            loginScene.setVisible(false);
            imgAnchorLogin.setVisible(false);
            tableView.setVisible(true);
            showTable();
        } catch (SQLException e) {
            statusLabel.setText("Akun tidak terverifikasi");
            e.printStackTrace();
            hideTable();
        }
    }

    private void showTable() { panelDataFrame.setVisible(true);    }

    private void hideTable() {
        panelDataFrame.setVisible(false);
    }

    public static class Person {
        private String name;
        private double salary;
        private int id;

        /**
         * @param name fetching name of the user
         * @param salary for fetching data salary
         * @param id fetching unique identifier of user in that database to prevent duplication
         */
        public Person(String name, double salary, int id) {
            this.name = name;
            this.salary = salary;
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getSalary() {
            return salary;
        }

        public void setSalary(double salary) {
            this.salary = salary;
        }

        public int getID() {
            return id;
        }

        public void setID(int id) {
            this.id = id;
        }
    }
}