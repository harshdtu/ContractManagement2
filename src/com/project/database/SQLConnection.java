package com.project.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {
	private static Connection conn = null;
	final private static String USERNAME = "liybotsvyembvp";
	final private static String PASSWORD = "e40dd8cba8730c3a7b167d3648acbb0d442bdef9c92cb9062110193cf126b37a";
	final private static String URI = "jdbc:postgresql://ec2-23-21-85-76.compute-1.amazonaws.com:5432/d11rrktmmgd00t?sslmode=require";
	static {
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(URI, USERNAME, PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private SQLConnection() {}
	public static Connection getConnection() {
		if (conn != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}
		return conn;
	}
	
}
