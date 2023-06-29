//package com.example.tugasbesar;
//
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.image.Image;
//import javafx.stage.Stage;
//import javafx.stage.StageStyle;
//
//import java.io.IOException;
//import java.util.Objects;
//
//public class Navigation {
//	private static Scene scene;
//
//	private static double xOffset = 0;
//	private static double yOffset = 0;
//
//	static void windowsIcon ( Stage stage ) {
//		stage.getIcons( ).add(
//				new Image(
//						Objects.requireNonNull(
//								Navigation.class.getResourceAsStream("media/window-icon.jpg")
//						)
//				)
//		);
//	}
//
//	static void dashboardStage ( Stage stage ) {
//		stage.getIcons( ).
//				add(
//						new Image(Navigation.class.getResourceAsStream("media/window-icon.jpg")
//						)
//				);
//		stage.setScene(scene);
//		stage.show( );
//	}
//
//	static void backToLogin ( ) throws IOException {
//		Parent root = FXMLLoader.load(Navigation.class.getResource("pageView.fxml"));
//		Stage stage = new Stage( );
//		Scene scene = new Scene(root);
//
//		Navigation navigator = new Navigation( );
//		navigator.bezeledStyle(stage, root);
//
//		stage.setScene(scene);
//		stage.show( );
//
//	}
//
//	public void moveTo ( String page ) throws IOException {
//		Parent root = FXMLLoader.load(getClass( ).getResource(page));
//		Stage stage = new Stage( );
//		Scene scene = new Scene(root);
//
//		Navigation navigator = new Navigation( );
//		navigator.bezeledStyle(stage, root);
//
//		stage.setScene(scene);
//		stage.show( );
//	}
//
//	void bezeledStyle ( Stage stage, Parent root ) {
//		//menyembunyikan windows bar
//		stage.initStyle(StageStyle.UNDECORATED);
//
//		//membuat fitur drag and move
//		root.setOnMousePressed(event -> {
//			xOffset = event.getSceneX( );
//			yOffset = event.getSceneY( );
//		});
//
//		//menggerakan scene
//		root.setOnMouseDragged(event -> {
//			stage.setX(event.getScreenX( ) - xOffset);
//			stage.setY(event.getScreenY( ) - yOffset);
//		});
//
//	}
//}


package com.example.tugasbesar;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class Navigation {
	private static Scene scene;
	private static double xOffset = 0;
	private static double yOffset = 0;

	public static void setWindowsIcon(Stage stage) {
		stage.getIcons().add(new Image(
				Objects.requireNonNull(Navigation.class.getResourceAsStream("media/window-icon.jpg"))
		));
	}

	public static void showDashboardStage(Stage stage) {
		stage.getIcons().add(new Image(Navigation.class.getResourceAsStream("media/window-icon.jpg")));
		stage.setScene(scene);
		stage.show();
	}

	public static void navigateToLogin() throws IOException {
		Parent root = FXMLLoader.load(Navigation.class.getResource("pageView.fxml"));
		Stage stage = new Stage();
		Scene scene = new Scene(root);

		Navigation navigation = new Navigation();
		navigation.applyBezeledStyle(stage, root);

		stage.setScene(scene);
		stage.show();
	}

	public void navigateTo(String page) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource(page));
		Stage stage = new Stage();
		Scene scene = new Scene(root);

		applyBezeledStyle(stage, root);

		stage.setScene(scene);
		stage.show();
	}

	private void applyBezeledStyle(Stage stage, Parent root) {
		// Hide the window bar
		stage.initStyle(StageStyle.UNDECORATED);

		// Enable drag and move feature
		root.setOnMousePressed(event -> {
			xOffset = event.getSceneX();
			yOffset = event.getSceneY();
		});

		// Move the scene
		root.setOnMouseDragged(event -> {
			stage.setX(event.getScreenX() - xOffset);
			stage.setY(event.getScreenY() - yOffset);
		});
	}
}
