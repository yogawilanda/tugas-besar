package com.example.tugasbesar.reservasi;

import com.example.tugasbesar.connectToDB.ConnectToDB;
import com.example.tugasbesar.dao.DAO;

import java.sql.SQLException;

public class ReservationDAO implements DAO {

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
	public void updateData ( int x ) {

	}

	public int getTotalTiketTerbeliBerdasarkanFilm ( int idReservasi ) throws SQLException {
		String query = "SELECT jumlah_seat FROM reservasi WHERE id_film = ?";

		var connection = connectToDB.getConnection( );
		var preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, idReservasi);

		var resultSet = preparedStatement.executeQuery( );

		if (resultSet.next( )) {
			return resultSet.getInt("jumlah_seat");
		}

		return 0;
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
}
