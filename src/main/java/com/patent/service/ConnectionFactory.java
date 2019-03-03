package com.patent.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
	
	private static final String HOST = "localhost:5432";
	private static final String DATABASE = "patentdata";
	private static final String USERNAME = "postgres";
	private static final String PASSWORD = "postgreSQL@123";
	private static final String USER = "user";

	// ADD OTHERS AS CONSTANT
	public static Connection getConnection() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("PostgreSQL JDBC driver NOT detected in library path.");
		}

		System.out.println("PostgreSQL JDBC driver detected in library path.");

		// Initialize connection object
		try {
			String url = String.format("jdbc:postgresql://%s/%s", HOST, DATABASE);
			// Set connection properties.
			Properties properties = new Properties();
			properties.setProperty(USER, USERNAME);
			properties.setProperty("password", PASSWORD);
			// get connection
			return DriverManager.getConnection(url, properties);

		} catch (SQLException e) {
			System.out.println("Failed to create connection to database." + e.getMessage());
		}
		
		return null;
	}
}
