package com.example.tugasbesar.film;

import com.example.tugasbesar.connectToDB.ConnectToDB;
import com.example.tugasbesar.dao.DAO;
import com.example.tugasbesar.querySQL.QuerySQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FilmDAO implements DAO {

	ConnectToDB connectToDB = new ConnectToDB( );

	public ObservableList<Film> getAllFilms () throws SQLException {
		ObservableList<Film> films = FXCollections.observableArrayList( );
		try ( var connection = connectToDB.getConnection( );
		      Statement statement = connection.createStatement( );
		      ResultSet resultSet = statement.executeQuery(QuerySQL.getQueryShowFilm( )) ) {
			while (resultSet.next( )) {
				Film film = new Film( );
				film.setId_film(resultSet.getInt("id_film"));
				film.setNama_film(resultSet.getString("nama_film"));
				film.setDeskripsi_film(resultSet.getString("deskripsi_film"));
				film.setHarga(resultSet.getInt("harga_pokok_penjualan_film"));
				films.add(film);
			}
		}

		return films;
	}


	@Override
	public void getConnection () {

	}

	@Override
	public void setConnection () {

	}

	@Override
	public void updateData () {
		String updateQuery = "UPDATE studio SET jumlah_seat = jumlah_seat - 1 WHERE id_studio = 1;";
		try ( var connection = connectToDB.getConnection( );
		      Statement statement = connection.createStatement( ); ) {
			int rowsAffected = statement.executeUpdate(updateQuery);


			if (rowsAffected == 1) System.out.printf("Successful bought 1 item");

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateData ( int jumlahSeat ) {
		String updateQuery = "UPDATE studio SET jumlah_seat = jumlah_seat - " + jumlahSeat + " WHERE id_studio = 1;";

		try ( var connection = connectToDB.getConnection( );
		      Statement statement = connection.createStatement( ); ) {
			int rowsAffected = statement.executeUpdate(updateQuery);

			if (rowsAffected == 1) System.out.println("Successful bought 1 item");

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public int getTiketTersisa(int idStudio, String tanggalTayang, String jamTayangValue) throws SQLException {
		String query = "SELECT tt.id_tanggal_tayang, f.nama_film, s.nama_studio, j.nama_jam_tayang, tt.tanggal_tayang, s.kapasitas - COALESCE(SUM(t.jumlah_seat_terpesan), 0) AS sisa_tiket " +
				"FROM tanggal_tayang tt " +
				"JOIN film f ON tt.id_film = f.id_film " +
				"JOIN studio s ON tt.id_studio = s.id_studio " +
				"JOIN jam_tayang j ON tt.id_jam_tayang = j.id_jam_tayang " +
				"LEFT JOIN transaksi t ON tt.id_tanggal_tayang = t.id_tanggal_tayang " +
				"WHERE tt.id_studio = ? AND tt.tanggal_tayang = ? AND j.nama_jam_tayang = ? " +
				"GROUP BY tt.id_tanggal_tayang, f.nama_film, s.nama_studio, j.nama_jam_tayang, tt.tanggal_tayang, s.kapasitas";

		try (var connection = connectToDB.getConnection();
		     var preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, idStudio);
			preparedStatement.setString(2, tanggalTayang);
			preparedStatement.setString(3, jamTayangValue);

			var resultSet = preparedStatement.executeQuery();

			int totalSisaTiket = 0;
			while (resultSet.next()) {
				int sisaTiket = resultSet.getInt("sisa_tiket");
				totalSisaTiket += sisaTiket;
			}

			return totalSisaTiket;
		}
	}


//	public int getTiketTersisa ( int idStudio, String tanggalTayang ) throws SQLException {
//		String query =
//				"SELECT tt.id_tanggal_tayang, f.nama_film, s.nama_studio, j.nama_jam_tayang, tt.tanggal_tayang, s.kapasitas - COALESCE(SUM(t.jumlah_seat_terpesan), 0) AS sisa_tiket " +
//						"FROM tanggal_tayang tt " +
//						"JOIN film f ON tt.id_film = f.id_film " +
//						"JOIN studio s ON tt.id_studio = s.id_studio " +
//						"JOIN jam_tayang j ON tt.id_jam_tayang = j.id_jam_tayang " +
//						"LEFT JOIN transaksi t ON tt.id_tanggal_tayang = t.id_tanggal_tayang " +
//						"WHERE tt.tanggal_tayang = ?, f.id_film = ? " +
//						"GROUP BY tt.id_tanggal_tayang, f.nama_film, s.nama_studio, j.nama_jam_tayang, tt.tanggal_tayang, s.kapasitas";
//
//		try ( var connection = connectToDB.getConnection( );
//		      var preparedStatement = connection.prepareStatement(query) ) {
//			preparedStatement.setString(1, tanggalTayang);
//			preparedStatement.setInt(2, idStudio);
//
//			var resultSet = preparedStatement.executeQuery( );
//			if (resultSet.next( )) {
//				return resultSet.getInt("sisa_tiket");
//			}
//
//			return 0;
//		}
//	}

	@Override
	public void deleteData () {
		System.out.println("FilmDAO.deleteData : Tidak Digunakan");
	}

	@Override
	public void addData () {
		System.out.println("FilmDAO.addData : Harus dibuat");
	}

	@Override
	public void showData () {
		try {
			getAllFilms( );
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void reservasiData () {

	}
}
