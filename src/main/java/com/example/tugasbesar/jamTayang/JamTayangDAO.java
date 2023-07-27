package com.example.tugasbesar.jamTayang;

import com.example.tugasbesar.connectToDB.ConnectToDB;
import com.example.tugasbesar.dao.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JamTayangDAO implements DAO {
	ConnectToDB connectToDB = new ConnectToDB();

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

	// This method returns a list of distinct nama_jam_tayang values from the database
	public List<String> getDistinctJamTayang() throws SQLException {
		List<String> jamTayangOptions = new ArrayList<>();
		String query = "SELECT DISTINCT nama_jam_tayang FROM jam_tayang";
		try ( Connection connection = connectToDB.getConnection();
		      PreparedStatement statement = connection.prepareStatement(query);
		      ResultSet resultSet = statement.executeQuery()) {
			while (resultSet.next()) {
				jamTayangOptions.add(resultSet.getString("nama_jam_tayang"));
			}
		}
		return jamTayangOptions;
	}
}
