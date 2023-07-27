package com.example.tugasbesar.dao;

public interface DAO {
	public void getConnection ();

	public void setConnection ();

	public void updateData ();

	public void updateData ( int jumlahSeat );


	public void deleteData ();

	public void addData ();

	public void showData ();

	public void reservasiData ();
}
