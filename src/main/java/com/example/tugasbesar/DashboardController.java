package com.example.tugasbesar;

import com.example.tugasbesar.film.Film;
import com.example.tugasbesar.film.FilmDAO;
import com.example.tugasbesar.jadwalTayang.JadwalTayangDAO;
import com.example.tugasbesar.jamTayang.JamTayangDAO;
import com.example.tugasbesar.randomizer.Randomizer;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class DashboardController extends Application {
	public Button btnNavLogout;
	public AnchorPane Dashboard;
	public TableView tableView;
	public TableColumn IDTableColumn;
	public TableColumn salaryTableColumn;
	public Button btnBookMovie;
	public TextField textfieldNamaFilmBook;
	public TextField textfieldIDFilmBook;
	public ImageView moviePoster;
	public Pane judulFilmDashboard;
	public Label labelJudul;
	public Label labelDeskripsi;
	public Button btnShowTransaksi;
	public VBox groupReservationVBox;
	public Button btnExit1;
	public ImageView movieBackdrop;
	public Button btnDaftarMovie;
	public StackPane stackPaneImagePoster;
	public Button psnTiketBtnID;
	public TextField jumlahTiketTextField;
	public ComboBox pilihanComboBox;
	public Label keteranganHargaTiketLabel;
	public Label hargaTiketLabel;
	public Label penghitungSeatText;
	public TableView transaksiTable;
	public Label sambutanLogin;
	public DatePicker datePickerDashboard;
	public Label keterangSisaTiket;

	@FXML
	private TableView<Film> filmTable;

	@FXML
	private TableColumn<Film, Integer> idColumn;

	@FXML
	private TableColumn<Film, String> namaColumn;

	@FXML
	private TableColumn<Film, String> deskripsiColumn;

	Image image;
	FilmDAO filmDAO = new FilmDAO( );

	Navigation navigator = new Navigation( );
	ObservableList<String> listComboBox;
	int indexCombo;

	int initialValue = 0;

	String namaTable = "";

	int i;
	public String imagePoster = "src/main/resources/com/example/tugasbesar/media/poster/";
	public File directoryPoster = new File(imagePoster);
	public File[] filesPoster = directoryPoster.listFiles( );
	String[] imageListPoster = new String[filesPoster.length];

	public String backDropSource = "src/main/resources/com/example/tugasbesar/media/backdrop/";
	public File directoryBackdrop = new File(backDropSource);
	public File[] filesBackdrop = directoryBackdrop.listFiles( );

	Randomizer randomizer = new Randomizer( );
	String[] imageListBackDrop = new String[filesBackdrop.length];

	String[] backDropList = new String[filesBackdrop.length];

	{
		for (i = 0; i < filesPoster.length; i++) {
			imageListPoster[i] = filesPoster[i].getName( );
		}
	}

	Image imageLoader ( int movieId ) {
		try {

			image = new Image(new FileInputStream(imagePoster + imageListPoster[movieId]));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		return image;
	}


	{
		for (i = 0; i < filesBackdrop.length; i++) {
			backDropList[i] = filesBackdrop[i].getName( );
		}
	}

	JamTayangDAO jamTayangDAO = new JamTayangDAO( );
	JadwalTayangDAO jadwalTayangDAO = new JadwalTayangDAO( );

	@FXML
	void initialize () throws SQLException {
		movieBackdrop.setVisible(true);
		movieBackdrop.setImage(backDropLoader(3));

		listComboBox = FXCollections.observableArrayList(jamTayangDAO.getDistinctJamTayang( ));
		pilihanComboBox.setItems(listComboBox);
		// Assuming pilihanComboBox is your ComboBox instance
		pilihanComboBox.setValue("siang");

		pilihanComboBoxListener = ( observable, oldValue, newValue ) -> {
			try {
				// Use the selectedFilm from filmTable's row
				Film selectedFilm = filmTable.getSelectionModel( ).getSelectedItem( );
				if (selectedFilm != null) {
					String tanggalTayang = datePickerDashboard.getValue( ).toString( );
					String jamTayangValue = pilihanComboBox.getValue( ) != null ? pilihanComboBox.getValue( ).toString( ) : null;

					int sisaTiket = lihatTotalTiketFilmTertentuBerdasarkanTanggal(selectedFilm.getId_film( ), tanggalTayang, jamTayangValue);
					penghitungSeatText.setText(String.valueOf(sisaTiket));
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		};

		pilihanComboBox.valueProperty( ).addListener(pilihanComboBoxListener);
		jumlahTiketTextField.setText("1");


		sambutanLogin.setText("Selamat datang, Diana");


	}

	public void setFilesPoster ( File[] filesPoster ) {
		this.filesPoster = filesPoster;
	}

	public File[] getFilesBackdrop () {
		return filesBackdrop;
	}

	Image backDropLoader ( int movieId ) {
		try {
//			Jika file backdrop tidak ada atau lebih dari range array yang ada di dalam backdrop file, maka secara
//			default akan mengembalikan file index ke 2 ([0])
			if (movieId < 0 || movieId >= filesBackdrop.length) {
				image = new Image(new FileInputStream(backDropSource + backDropList[1]));
			} else {
				image = new Image(new FileInputStream(backDropSource + backDropList[movieId]));
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		return image;
	}


	void showImageFromTable ( Film selectedFilm ) {


		movieBackdrop.setImage(backDropLoader(selectedFilm.getId_film( ) - 1));

		movieBackdrop.setVisible(true);

		labelJudul.setText(selectedFilm.getNama_film( ));
		labelDeskripsi.setText(selectedFilm.getDeskripsi_film( ));
	}


	@Override
	public void start ( Stage stage ) throws IOException {
		showTable( );
	}


	public void logOut ( ActionEvent actionEvent ) throws IOException {
		Dashboard.getScene( ).getWindow( ).hide( );
		navigator.navigateTo("LoginView.fxml", false);
	}

	void showTable () {
		tableView.setVisible(true);
		IDTableColumn.setVisible(true);
		salaryTableColumn.setVisible(true);
		groupReservationVBox.setVisible(true);
	}


	@FXML
	private void reservationDialog ( ActionEvent actionEvent ) {
		initialValue = Integer.parseInt(jumlahTiketTextField.getText( ));
		namaTable = pilihanComboBox.getValue( ).toString( );
		filmDAO.updateData(initialValue);

//		refresh table setelah
		reserveAction( );
	}


	private void registerMovie () {

	}

	private ChangeListener<String> pilihanComboBoxListener;

	@FXML
	private void reserveAction () {
		System.out.println(randomizer.generateRandomJamTayang( ));
		filmTable.setVisible(true);
		idColumn.setVisible(true);
		namaColumn.setVisible(true);
		deskripsiColumn.setVisible(true);
		movieBackdrop.setVisible(true);
		transaksiTable.setVisible(false);
		psnTiketBtnID.setVisible(true);
		textfieldNamaFilmBook.setVisible(true);
		textfieldIDFilmBook.setVisible(true);
		btnBookMovie.setVisible(true);
		pilihanComboBox.setVisible(true);
		jumlahTiketTextField.setVisible(true);

		try {
			filmTable.setItems(filmDAO.getAllFilms( ));
		} catch (SQLException e) {
			e.printStackTrace( );
		}

		datePickerDashboard.valueProperty( ).setValue(LocalDate.now( ));

		datePickerDashboard.valueProperty( ).addListener(( observable, oldValue, newValue ) -> {
			Film selectedFilm = filmTable.getSelectionModel( ).getSelectedItem( );
			if (selectedFilm != null) {
				updateSeatCount(selectedFilm);
			}
		});

		filmTable.getSelectionModel( ).selectedItemProperty( ).addListener(( observable, oldValue, newValue ) -> {
			if (newValue != null) {
				updateSeatCount(newValue);
			}
		});


		filmTable.setRowFactory(tv -> {
			TableRow<Film> row = new TableRow<>( );
			row.setOnMouseClicked(event -> {

				keterangSisaTiket.setVisible(true);
				penghitungSeatText.setVisible(true);
				judulFilmDashboard.setVisible(true);

//				label nama & deskripsi film
				labelDeskripsi.setVisible(true);
				labelJudul.setVisible(true);

//				label harga
				keteranganHargaTiketLabel.setVisible(true);
				hargaTiketLabel.setVisible(true);

				if (event.getClickCount( ) == 1 && !row.isEmpty( )) {
					int totalTiket = Integer.parseInt(jumlahTiketTextField.getText( ));

					Film selectedFilm = row.getItem( );
					showImageFromTable(selectedFilm);
					updateSeatCount(selectedFilm);
//					pilihanComboBox.valueProperty( ).addListener(pilihanComboBoxListener);

					String jamTayangValue = (String) pilihanComboBox.getValue( );
					try {
						penghitungSeatText.setText(String.valueOf(lihatTotalTiketFilmTertentuBerdasarkanTanggal(selectedFilm.getId_film( ),
								String.valueOf(datePickerDashboard.getValue( )), jamTayangValue)));

						System.out.println("DashboardController.reserveAction " + selectedFilm.getId_film( ) + ' ' +
								datePickerDashboard.getValue( ) + ' ' + jamTayangValue);
					} catch (SQLException e) {
						throw new RuntimeException(e);
					}

					hargaTiketLabel.setText("IDR " + String.format("%,d", selectedFilm.getHarga( ) * totalTiket) + "/pcs");
					System.out.println(
							datePickerDashboard.getValue( )
									+ " " + selectedFilm.getHarga( )
									+ ' ' + pilihanComboBox.getValue( )
									+ ' ' + totalTiket);

				}
			});
			return row;
		});

		datePickerDashboard.setVisible(true);

	}


	private void updateSeatCount ( Film selectedFilm ) {
		LocalDate selectedDate = datePickerDashboard.getValue( );
		if (selectedDate == null) {
			return;
		}
		String tanggalTayang = selectedDate.toString( );
		String jamTayangValue = pilihanComboBox.getValue( ).toString( ); // Get the selected jamTayang from the ComboBox
		try {
			int sisaTiket = lihatTotalTiketFilmTertentuBerdasarkanTanggal(selectedFilm.getId_film( ), tanggalTayang, jamTayangValue);
			penghitungSeatText.setText(String.valueOf(sisaTiket));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	int lihatTotalTiketFilmTertentuBerdasarkanTanggal ( int idFilm, String tanggalTayang, String jamTayangValue ) throws SQLException {
		return jadwalTayangDAO.getTiketTersisaBerdasarkanTanggalTayang(idFilm, tanggalTayang, jamTayangValue);
	}


	public void btnShowTransaksiEvent ( ActionEvent actionEvent ) {
		filmTable.setVisible(false);
		hargaTiketLabel.setVisible(false);
		keteranganHargaTiketLabel.setVisible(false);
		groupReservationVBox.setVisible(false);
		transaksiTable.setVisible(true);
	}

	public void exit ( ActionEvent actionEvent ) throws IOException {
		navigator.exit("DashboardView.fxml");
	}

	public void daftarkanFilmAction ( ActionEvent actionEvent ) throws IOException {
		navigator.navigateTo("DaftarDialogView.fxml", false);
	}

}
