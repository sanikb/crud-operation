package com.sprk.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CreateConnection {

	public static Connection getConnection() throws SQLException  {
		String username = "root";
		String password = "sani@2001";
		String url = "jdbc:mysql://localhost:3306/Studenntdb";
		
		Connection conn = DriverManager.getConnection(url, username, password);
		return conn;
	}
}
