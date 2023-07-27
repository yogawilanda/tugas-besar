package com.example.tugasbesar.querySQL;

public class QuerySQL {

	//Mengurangi data dari jumlah stock
	private String updateQuery = "UPDATE film SET stok_seat = stok_seat - 1 WHERE id_film = 1;";
	private static String queryShowFilm = "SELECT id_film, nama_film, deskripsi_film, harga_pokok_penjualan_film FROM film";

	private static String queryShowTicket = "SELECT jumlah_seat from studio";

	String queryCheckFilm = "SELECT tt.id_tanggal_tayang, f.nama_film, s.nama_studio, j.nama_jam_tayang, tt.tanggal_tayang, s.kapasitas - COALESCE(SUM(t.jumlah_seat_terpesan), 0) AS sisa_tiket " +
			"FROM tanggal_tayang tt " +
			"JOIN film f ON tt.id_film = f.id_film " +
			"JOIN studio s ON tt.id_studio = s.id_studio " +
			"JOIN jam_tayang j ON tt.id_jam_tayang = j.id_jam_tayang " +
			"LEFT JOIN transaksi t ON tt.id_tanggal_tayang = t.id_tanggal_tayang " +
			"WHERE tt.tanggal_tayang = ? " +
			"GROUP BY tt.id_tanggal_tayang, f.nama_film, s.nama_studio, j.nama_jam_tayang, tt.tanggal_tayang, s.kapasitas";

	public static String getQueryShowTicket () {
		return queryShowTicket;
	}

	public static void setQueryShowTicket ( String queryShowTicket ) {
		QuerySQL.queryShowTicket = queryShowTicket;
	}

	public String getUpdateQuery () {
		return updateQuery;
	}

	public static String getQueryShowFilm () {
		return queryShowFilm;
	}

	public void setUpdateQuery ( String updateQuery ) {
		this.updateQuery = updateQuery;
	}
	//	try (
//	var connection = LoginController.getConnection();
//	var statement;
//) {
//	    statement = connection.prepareStatement("UPDATE film SET stok_seat = stok_seat - ? WHERE id_film = ?;");
//		statement.setInt(1, 1);
//		statement.setInt(2, 1);
//		statement.executeUpdate();
//
//		statement = connection.prepareStatement("UPDATE studio SET jumlah_seat = jumlah_seat - ? WHERE id_studio = ?;");
//		statement.setInt(1, 1);
//		statement.setInt(2, 1);
//		statement.executeUpdate();
//
//		statement = connection.prepareStatement("UPDATE customer SET jumlah_tiket = jumlah_tiket + ? WHERE id_customer = ?;");
//		statement.setInt(1, 1);
//		statement.setInt(2, 1);
//		statement.executeUpdate();
//	} catch (SQLException e) {
//		throw new RuntimeException(e);
//	}


	//todo buat view untuk pembelian, transaksi

//	String createViewQuery = "CREATE VIEW combined_view AS SELECT film.id_film, film.nama_film, studio.jumlah_seat, customer.jumlah_tiket FROM film, studio, customer WHERE film.id_film = studio.id_film AND studio.id_studio = customer.id_studio;";
//
//try (
//	var connection = LoginController.getConnection();
//	var statement = connection.createStatement();
//) {
//		statement.executeUpdate(createViewQuery);
//	} catch (SQLException e) {
//		throw new RuntimeException(e);
//	}
//
//try (
//	var connection = LoginController.getConnection();
//	var statement = connection.createStatement();
//	var resultSet = statement.executeQuery("SELECT nama_film, jumlah_seat, jumlah_tiket FROM combined_view;");
//) {
//		while (resultSet.next()) {
//			System.out.println(resultSet.getString("nama_film") + " " + resultSet.getInt("jumlah_seat") + " " + resultSet.getInt("jumlah_tiket"));
//		}
//	} catch (SQLException e) {
//		throw new RuntimeException(e);
//	}

}
