package com.example.tugasbesar;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FilmDAO {
    public ObservableList<Film> getAllFilms() throws SQLException {
        ObservableList<Film> films = FXCollections.observableArrayList();
        String query = "SELECT * FROM films";

        try (Connection connection = Controller.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Film film = new Film();
                film.setId_film(resultSet.getInt("id_film"));
                film.setNama_film(resultSet.getString("nama_film"));
                film.setDeskripsi_film(resultSet.getString("deskripsi_film"));
                films.add(film);
            }
        }

        return films;
    }
}
