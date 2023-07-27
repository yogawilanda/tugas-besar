package com.example.tugasbesar;

import com.example.tugasbesar.film.FilmDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class TransactionController {

	public Button okButton;
	public Button cancelButton;
	public AnchorPane anchorPaneReservation;
	public Label labelJudulReservasi;
	@FXML
	private Label filmLabel;

	@FXML
	private Label IDLabel;

	@FXML
	private ImageView imageView;

	public Stage stage;

	DashboardController dashboardController = new DashboardController( );
	FilmDAO filmDAO = new FilmDAO( );

	@FXML
	public void initialize () {

		String imageUrl = "https://picsum.photos/200/300";

		Thread async = new Thread(
				() -> {

					Image image = new Image(imageUrl);
					imageView.setImage(dashboardController.backDropLoader(1));
				}
		);


		okButton.setOnAction(actionEvent -> {
			//todo : pesan tiket disini menggunakan fitur film.stok_seat - jumlah_seat

			System.out.println("Status : Success\n");
			filmDAO.updateData( );
			anchorPaneReservation.getScene( ).getWindow( ).hide( );

		});
		async.start( );

	}

	@FXML
	public void openReservationDialog () {
		try {
			new Navigation( ).navigateTo(
					"TransactionView.fxml",
					false);

		} catch (IOException e) {
			e.printStackTrace( );
		}
	}

	private void setDialogStage ( Stage dialogStage ) {
		this.stage = dialogStage;
	}

	private void setDialogStage ( ActionEvent actionEvent ) {
		stage = (Stage) actionEvent.getSource( );
	}
}
