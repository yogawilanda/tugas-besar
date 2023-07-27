package com.example.tugasbesar;

import com.example.tugasbesar.connectToDB.ConnectToDB;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.*;


public class LoginController {
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
	Navigation navigator = new Navigation( );

	private ObservableList<User> data;




	ConnectToDB connectToDB = new ConnectToDB();



	@FXML
	public void handleLogin ( ActionEvent actionEvent ) throws IOException {

		String username = usernameField.getText( );
		String password = passwordField.getText( );
		// Handle the login event
		boolean isLoginSuccessful = login(username, password);

		// Display the appropriate feedback
		if (isLoginSuccessful) {
			statusLabel.setText("Login successful!");
			navigator.navigateTo("DashboardView.fxml", false
			);
		} else {
			statusLabel.setText("Invalid username or password!");
		}
	}

	private boolean login ( String username, String password ) {

		String preparedStatementQuery = "SELECT * FROM user WHERE nama_user = ?";

		try ( Connection connection = connectToDB.getConnection( );
		      PreparedStatement pStatement = connection.prepareStatement(preparedStatementQuery) ) {
			pStatement.setString(1, username);

			try ( ResultSet resultSet = pStatement.executeQuery( ) ) {
				if (resultSet.next( )) {
					String storedPassword = resultSet.getString("password_user");
					return storedPassword.equals(password);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace( );
		}

		return false;
	}


	@FXML
	public void initialize () throws SQLException {
		connectToDB.connection( );
	}
}

