package com.example.tugasbesar.jadwalTayang;

import com.example.tugasbesar.connectToDB.ConnectToDB;
import com.example.tugasbesar.dao.DAO;

import java.sql.SQLException;

public class JadwalTayangDAO implements DAO {

	ConnectToDB connectToDB = new ConnectToDB( );

	@Override
	public void getConnection () {

	}

	@Override
	public void setConnection () {

	}

	@Override
	public void updateData () {

	}

	@Override
	public void updateData ( int jumlahSeat ) {

	}

	@Override
	public void deleteData () {

	}

	@Override
	public void addData () {

	}

	@Override
	public void showData () {

	}

	@Override
	public void reservasiData () {

	}

	public int getTiketTersisaBerdasarkanTanggalTayang ( int idStudio, String tanggalTayang, String jamTayangValue ) throws SQLException {
		String query = "SELECT tt.id_tanggal_tayang, f.nama_film, s.nama_studio, j.nama_jam_tayang, tt.tanggal_tayang, s.kapasitas - COALESCE(SUM(t.jumlah_seat_terpesan), 0) AS sisa_tiket " +
				"FROM tanggal_tayang tt " +
				"JOIN film f ON tt.id_film = f.id_film " +
				"JOIN studio s ON tt.id_studio = s.id_studio " +
				"JOIN jam_tayang j ON tt.id_jam_tayang = j.id_jam_tayang " +
				"LEFT JOIN transaksi t ON tt.id_tanggal_tayang = t.id_tanggal_tayang " +
				"WHERE tt.id_studio = ? AND tt.tanggal_tayang = ? AND j.nama_jam_tayang = ? " +
				"GROUP BY tt.id_tanggal_tayang, f.nama_film, s.nama_studio, j.nama_jam_tayang, tt.tanggal_tayang, s.kapasitas";

		try ( var connection = connectToDB.getConnection( );
		      var preparedStatement = connection.prepareStatement(query) ) {
			preparedStatement.setInt(1, idStudio);
			preparedStatement.setString(2, tanggalTayang);
			preparedStatement.setString(3, jamTayangValue);

			var resultSet = preparedStatement.executeQuery( );

			int totalSisaTiket = 0;
			while (resultSet.next( )) {
				int sisaTiket = resultSet.getInt("sisa_tiket");
				totalSisaTiket += sisaTiket;
			}

			return totalSisaTiket;
		}
	}
}
