package com.example.tugasbesar;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

	final String login = "LoginView.fxml";
	final String dashboardView = "DashboardView.fxml";

	final String dialogReservationPage = "TransactionView.fxml";

	final String daftarDialogView = "DaftarDialogView.fxml";
	@Override
	public void start ( Stage stage ) throws IOException {
			navigator.navigateTo(dashboardView, false);
	}

	Navigation navigator = new Navigation( );

	public static void main ( String[] args ) {
		launch( );
	}


}