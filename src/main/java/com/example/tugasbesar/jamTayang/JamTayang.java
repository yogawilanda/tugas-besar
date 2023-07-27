package com.example.tugasbesar.jamTayang;

import com.example.tugasbesar.connectToDB.ConnectToDB;
import com.example.tugasbesar.dao.DAO;

public class JamTayang {
	int id_jam_tayang;
	String nama_jam_tayang;

	public int getId_jam_tayang () {
		return id_jam_tayang;
	}

	public void setId_jam_tayang ( int id_jam_tayang ) {
		this.id_jam_tayang = id_jam_tayang;
	}

	public String getNama_jam_tayang () {
		return nama_jam_tayang;
	}

	public void setNama_jam_tayang ( String nama_jam_tayang ) {
		this.nama_jam_tayang = nama_jam_tayang;
	}
}
