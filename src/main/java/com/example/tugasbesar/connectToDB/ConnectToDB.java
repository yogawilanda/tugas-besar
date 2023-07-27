package com.example.tugasbesar.connectToDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectToDB {
	public Connection getConnection () throws SQLException {
		String db = "eleanor_db8";
		String url = "jdbc:mysql://localhost:3306/" + db;

		return DriverManager.getConnection(url, "root", null);
	}

	public void connection () {
		try {
			var connection = getConnection( );
			System.out.println("Successfully Connect to DB\nNow verify you are the user!");

		} catch (SQLException e) {
			e.printStackTrace( );
		}
	}
}
