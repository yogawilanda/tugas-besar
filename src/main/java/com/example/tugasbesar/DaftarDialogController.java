package com.example.tugasbesar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class DaftarDialogController extends Navigation {
	@FXML
	public AnchorPane daftarDialogAnchorPane;
	//	todo: fungsionalkan okButton
	public Button okButton;
	//	todo: fungsionalkan cancelButton
	public Button cancelButton;
	@FXML
	private TextArea deskripsiFilmYangDiDaftarkanTextField;
	@FXML
	private TextField namaFilmYangDidaftarkanTextField;
	@FXML
	private TextField hargaFilmYangDidaftarkanTextField;


	@FXML
	void initialize () throws IOException {

	}

	Navigation navigation = new Navigation( );

	public void daftarkanMovie () throws IOException {
//		1. todo: jika data ada yang null, buatkan error handling


// 		2. todo: buatkan penampung data  jika selesai
		String textDaftarNamaFilm = getNamaFilmYangDidaftarkanTextField( ).getText( );
		System.out.println("DaftarDialogController.daftarkanMovie: " + textDaftarNamaFilm);

		String textDaftarHargaFilm = getHargaFilmYangDidaftarkanTextField( ).getText( );
		System.out.println("DaftarDialogController.daftarkanMovie: " + textDaftarHargaFilm);

		String textDeskripsiFilm = getDeskripsiFilmYangDiDaftarkanTextField( ).getText( );
		System.out.println("DaftarDialogController.daftarkanMovie" + textDeskripsiFilm);


//		3. todo: buatkan navigasi ke Controller jika selesai
		daftarDialogAnchorPane.getScene( ).getWindow( ).hide( );

	}


	public TextField getNamaFilmYangDidaftarkanTextField () {
		namaFilmYangDidaftarkanTextField.getText( );
		return namaFilmYangDidaftarkanTextField;
	}

	public void setNamaFilmYangDidaftarkanTextField ( TextField namaFilmYangDidaftarkanTextField ) {
		this.namaFilmYangDidaftarkanTextField = namaFilmYangDidaftarkanTextField;
	}

	public TextField getHargaFilmYangDidaftarkanTextField () {
		return hargaFilmYangDidaftarkanTextField;
	}

	public void setHargaFilmYangDidaftarkanTextField ( TextField hargaFilmYangDidaftarkanTextField ) {
		this.hargaFilmYangDidaftarkanTextField = hargaFilmYangDidaftarkanTextField;
	}

	public TextArea getDeskripsiFilmYangDiDaftarkanTextField () {
		return deskripsiFilmYangDiDaftarkanTextField;
	}

	public void setDeskripsiFilmYangDiDaftarkanTextField ( String stringIsiDeskripsi ) {
		deskripsiFilmYangDiDaftarkanTextField.setText(stringIsiDeskripsi);
		this.deskripsiFilmYangDiDaftarkanTextField = deskripsiFilmYangDiDaftarkanTextField;
	}

	public void cancelDaftarMovie ( ActionEvent actionEvent ) {
		daftarDialogAnchorPane.getScene( ).getWindow( ).hide( );
	}
}
