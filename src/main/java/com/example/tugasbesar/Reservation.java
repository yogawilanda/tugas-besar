package com.example.tugasbesar;

public class Reservation {

    int id_transaksi;
    int id_film;
    int id_studio;
    int id_jadwalTayang;

    int kursi_duduk;
    public int getId_transaksi () {
        return id_transaksi;
    }

    public void setId_transaksi (int id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public int getId_film () {
        return id_film;
    }

    public void setId_film (int id_film) {
        this.id_film = id_film;
    }

    public int getId_studio () {
        return id_studio;
    }

    public void setId_studio (int id_studio) {
        this.id_studio = id_studio;
    }

    public int getId_jadwalTayang () {
        return id_jadwalTayang;
    }

    public void setId_jadwalTayang (int id_jadwalTayang) {
        this.id_jadwalTayang = id_jadwalTayang;
    }

    public int getKursi_duduk () {
        return kursi_duduk;
    }

    public void setKursi_duduk (int kursi_duduk) {
        this.kursi_duduk = kursi_duduk;
    }



}
