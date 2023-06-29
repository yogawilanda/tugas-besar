package com.example.tugasbesar;

import com.example.tugasbesar.film.Film;
import com.example.tugasbesar.film.FilmDAO;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class Dashboard extends Application {
	public Button btnNavLogout;
	public AnchorPane Dashboard;
	public TableView tableView;
	public TableColumn IDTableColumn;
	public TableColumn salaryTableColumn;
	public Button btnBookMovie;
	public TextField textfieldNamaFilmBook;
	public TextField textfieldIDFilmBook;

	@FXML
	private TableView<Film> filmTable;

	@FXML
	private TableColumn<Film, Integer> idColumn;

	@FXML
	private TableColumn<Film, String> namaColumn;

	@FXML
	private TableColumn<Film, String> deskripsiColumn;


	@Override
	public void start (@NotNull Stage stage) throws IOException {
		Navigation.showDashboardStage(stage);
		showTable( );
	}

	public void logOut (ActionEvent actionEvent) throws  IOException {
		Dashboard.getScene( ).getWindow( ).hide( );
		Navigation.navigateToLogin( );
	}

	void showTable () {
		tableView.setVisible(true);
		IDTableColumn.setVisible(true);
		salaryTableColumn.setVisible(true);
	}


	@FXML
	private void reservationDialog (ActionEvent actionEvent) {
		Dialog<ButtonType> dialog = new Dialog<>( );
		dialog.getDialogPane( ).getButtonTypes( ).addAll(ButtonType.OK, ButtonType.CANCEL);
		dialog.setHeaderText("Are you sure you want to book the ticket?");
		dialog.setTitle("Reservation Confirmation");
		dialog.setWidth(800);

		// Create the labels
		Label filmLabel = new Label( );
		Label IDLabel = new Label( );
		ImageView imageView = new ImageView( );
		imageView.setFitWidth(200); // Set the desired width of the image
		imageView.setPreserveRatio(true); // Preserve the image's aspect ratio

		VBox dialogContent = new VBox(10, new Label("Selected Film:"), filmLabel, new Label("Selected ID:"), IDLabel, imageView);
		dialog.getDialogPane( ).setContent(dialogContent);

		btnBookMovie.setOnAction(event -> {
			String selectedFilmName = textfieldNamaFilmBook.getText( );
			int selectedFilmId = Integer.parseInt(textfieldIDFilmBook.getText( ));

			IDLabel.setText(String.valueOf(selectedFilmId));
			filmLabel.setText(String.valueOf(selectedFilmName));

			// Load and set the image from the internet
			String imageUrl = "https://picsum.photos/200/300";
			Image image = new Image(imageUrl);
			imageView.setImage(image);

			Optional<ButtonType> result = dialog.showAndWait( );
			if (result.isPresent( ) && result.get( ) == ButtonType.OK) {
				bookTicket( );
			} else {
				// Handle cancellation
			}
		});
	}


	private void bookTicket () {
		System.out.println("OK!");
		System.out.println("You want!");

		//todo: pengurangan pada tabel studio.kapasitas_studio
	}

	private void registerMovie () {

	}

	private void settingsDashboard () {

	}

	@FXML
	void filmTable () {
		filmTable.setVisible(true);
		idColumn.setVisible(true);
		namaColumn.setVisible(true);
		deskripsiColumn.setVisible(true);

		FilmDAO filmDAO = new FilmDAO( );
		try {
			filmTable.setItems(filmDAO.getAllFilms( ));
		} catch (SQLException e) {
			e.printStackTrace( );
		}

		// todo: Membuat pilihan dapat diklik, lalu dikirim ke teks dan diproses untuk booking.
		filmTable.editableProperty( );

		// Set the visibility of text fields and button
		textfieldNamaFilmBook.setVisible(true);
		textfieldIDFilmBook.setVisible(true);
		btnBookMovie.setVisible(true);


		filmTable.setRowFactory(tv -> {
			TableRow<Film> row = new TableRow<>( );
			row.setOnMouseClicked(event -> {
				if (event.getClickCount( ) == 1 && !row.isEmpty( )) {
					Film selectedFilm = row.getItem( );
					textfieldNamaFilmBook.setText(selectedFilm.getNama_film( ));
					textfieldIDFilmBook.setText(String.valueOf(selectedFilm.getId_film( )));
				}
			});
			return row;
		});

	}


}
