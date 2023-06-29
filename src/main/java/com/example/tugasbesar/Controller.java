package com.example.tugasbesar;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

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
	private TableColumn<User, String> IDTableColumn, nameTableColumn;

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


	@FXML
	private void handleLogin ( ActionEvent actionEvent ) {
		String username = usernameField.getText( );
		String password = passwordField.getText( );

		Navigation navigator = new Navigation( );

		String preparedStatement = "SELECT * FROM user WHERE nama_user = ?";
//        todo : only for dev, use username&password from textfield when done
        try ( Connection connection = getConnection( );
              PreparedStatement statement = connection.prepareStatement(preparedStatement) ) {
            statement.setString(1, "diana");
            try ( ResultSet resultSet = statement.executeQuery( ) ) {
                if (resultSet.next( )) {
                    String storedPassword = resultSet.getString("password_user");
                    if (storedPassword.equals("12")) {
                        statusLabel.setText("Login successful!");
                        AnchorLogin.getScene( ).getWindow( ).hide( );


                        navigator.navigateTo("dashboard.fxml");
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
		//appropriate feedback
		statusLabel.setText("Invalid username or password!");
	}

	@FXML
	public void initialize () {
		connection( );
	}

}

