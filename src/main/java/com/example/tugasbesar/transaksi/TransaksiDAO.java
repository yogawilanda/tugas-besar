package com.example.tugasbesar.transaksi;

import com.example.tugasbesar.LoginController;
import com.example.tugasbesar.connectToDB.ConnectToDB;
import com.example.tugasbesar.dao.DAO;
import com.example.tugasbesar.film.Film;
import com.example.tugasbesar.querySQL.QuerySQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TransaksiDAO implements DAO {
	ConnectToDB connectToDB = new ConnectToDB();
	@Override
	public void getConnection () {

	}

	@Override
	public void setConnection () {

	}

	
	public ObservableList<Transaksi> getAllTransaksi () throws SQLException {
		ObservableList<Transaksi> transaksiObservableList = FXCollections.observableArrayList( );
		try ( var connection = connectToDB.getConnection( );
		      Statement statement = connection.createStatement( );

//			  todo: get all transaksi
		      ResultSet resultSet = statement.executeQuery(QuerySQL.getQueryShowFilm( )) ) {
			while (resultSet.next( )) {
				Transaksi transaksi = new Transaksi( );
				transaksi.setIdTransaksi(resultSet.getInt("id_transaksi"));
//				transaksi.set(resultSet.getInt("harga"));
//				transaksi.setNama_film(resultSet.getString("nama_film"));
//				transaksi.setDeskripsi_film(resultSet.getString("deskripsi_film"));
				transaksiObservableList.add(transaksi);
			}
		}

		return transaksiObservableList;
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
}
