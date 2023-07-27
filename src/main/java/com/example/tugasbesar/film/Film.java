package com.example.tugasbesar.film;

public class Film {
    private int id_film, harga;
    private String nama_film, deskripsi_film;

    public int getId_film () {
        return id_film;
    }

    public void setId_film (int id_film) {
        this.id_film = id_film;
    }

    public String getNama_film () {
        return nama_film;
    }

    public void setNama_film (String nama_film) {
        this.nama_film = nama_film;
    }

    public String getDeskripsi_film () {
        return deskripsi_film;
    }

    public void setDeskripsi_film (String deskripsi_film) {
        this.deskripsi_film = deskripsi_film;
    }

    public int getHarga () {
        return harga;
    }

    public void setHarga ( int harga ) {
        this.harga = harga;
    }
}
