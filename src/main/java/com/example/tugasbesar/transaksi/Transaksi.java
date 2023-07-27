package com.example.tugasbesar.transaksi;

import java.time.LocalDateTime;


public class Transaksi {

	private int idTransaksi;
	private int idReservasi;
	private LocalDateTime tanggalTransaksi;

	public Transaksi () {
	}

	public Transaksi ( int idTransaksi, int idReservasi, LocalDateTime tanggalTransaksi ) {
		this.idTransaksi = idTransaksi;
		this.idReservasi = idReservasi;
		this.tanggalTransaksi = tanggalTransaksi;
	}

	public int getIdTransaksi () {
		return idTransaksi;
	}

	public void setIdTransaksi ( int idTransaksi ) {
		this.idTransaksi = idTransaksi;
	}

	public int getIdReservasi () {
		return idReservasi;
	}

	public void setIdReservasi ( int idReservasi ) {
		this.idReservasi = idReservasi;
	}

	public LocalDateTime getTanggalTransaksi () {
		return tanggalTransaksi;
	}

	public void setTanggalTransaksi ( LocalDateTime tanggalTransaksi ) {
		this.tanggalTransaksi = tanggalTransaksi;
	}
}


