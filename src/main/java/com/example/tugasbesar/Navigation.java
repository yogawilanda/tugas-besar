package com.example.tugasbesar;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

public class Navigation {


	private static Scene scene;
	private static double xOffset = 0;
	private static double yOffset = 0;

	public static void setWindowsIcon ( Stage stage ) {
		stage.getIcons( ).add(new Image(
				Objects.requireNonNull(Navigation.class.getResourceAsStream("media/icons/window-icon.jpg"))
		));
	}

	public void navigateTo ( String page, boolean setToFullScreen ) throws IOException {

		if (currentStage != null) {
			currentStage.close();
		}

		Parent root = FXMLLoader.load(Objects.requireNonNull(getClass( ).getResource(page)));
		Stage stage = new Stage( );
		Scene scene = new Scene(root);
		setWindowsIcon(stage);


		stage.setMaximized(setToFullScreen);

		stage.initStyle(StageStyle.TRANSPARENT);
		scene.setFill(Color.TRANSPARENT);

		var windowShape = new Rectangle();
		windowShape.arcWidthProperty().bind(stage.widthProperty().divide(120)); // Adjust the factor for the curve
		windowShape.arcHeightProperty().bind(stage.heightProperty().divide(60));
		windowShape.widthProperty().bind(stage.widthProperty());
		windowShape.heightProperty().bind(stage.heightProperty());

		applyTransitions(root);
		applyBezeledStyle(stage, root);

		root.setClip(windowShape);

		stage.setScene(scene);
		stage.show( );
		currentStage = stage;



	}

	Stage currentStage;
	public void navigateTo ( String page, boolean setToFullScreen, double transparencyLevel ) throws IOException {
		if (currentStage != null) currentStage.close( );

		Parent root = FXMLLoader.load(getClass( ).getResource(page));
			Stage stage = new Stage( );
			Scene scene = new Scene(root);
			stage.setOpacity(transparencyLevel);

			scene.setFill(Color.TRANSPARENT);
			stage.initStyle(StageStyle.TRANSPARENT);

			Rectangle windowShape = new Rectangle();
			windowShape.arcWidthProperty().bind(stage.widthProperty().divide(60)); // Adjust the factor for the curve
			windowShape.arcHeightProperty().bind(stage.heightProperty().divide(60)); // Adjust the factor for the curve
			windowShape.widthProperty().bind(stage.widthProperty());
			windowShape.heightProperty().bind(stage.heightProperty());

			root.setClip(windowShape);

			applyTransitions(root);
			applyBezeledStyle(stage, root);


			stage.setScene(scene);
			stage.show( );


	}

	public void exit ( String page ) throws IOException {

		Parent root = FXMLLoader.load(getClass( ).getResource(page));
		Stage stage = new Stage( );
		Scene scene = new Scene(root);


		Platform.exit( );
		System.exit(0);




	}


	private static void applyTransitions ( Parent root ) {
		FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.6), root);
		fadeTransition.setFromValue(0);
		fadeTransition.setToValue(1);
		fadeTransition.play( );
	}

	private void applyBezeledStyle ( Stage stage, Parent root, Double opacity ) {
		// Hide the window bar
		stage.initStyle(StageStyle.TRANSPARENT);
		stage.setOpacity(opacity);

		// Enable drag and move feature
		root.setOnMousePressed(event -> {
			xOffset = event.getSceneX( );
			yOffset = event.getSceneY( );
		});


		// Move the scene
		root.setOnMouseDragged(event -> {
			stage.setX(event.getScreenX( ) - xOffset);
			stage.setY(event.getScreenY( ) - yOffset);
		});
	}

	private void applyBezeledStyle ( Stage stage, Parent root ) {
		// Hide the window bar
		stage.initStyle(StageStyle.TRANSPARENT);
//		stage.setOpacity(opacity);

		// Enable drag and move feature
		root.setOnMousePressed(event -> {
			xOffset = event.getSceneX( );
			yOffset = event.getSceneY( );
		});


		// Move the scene
		root.setOnMouseDragged(event -> {
			stage.setX(event.getScreenX( ) - xOffset);
			stage.setY(event.getScreenY( ) - yOffset);
		});
	}


	protected void navigateTo ( String page, boolean setToFullScreen, Stage window ) {
	}
}
