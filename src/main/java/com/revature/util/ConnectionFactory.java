package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public static Connection getConnection() throws SQLException {
		
		/*
		 * This is a utility that allows us to abstract out using the DriverManager to get a connection
		 * to our DB. This class follows a "Factory" design pattern. A factory produces instances (usually
		 * customizable with pre-configured properties) to the caller.
		 */
		
		return DriverManager.getConnection(
				System.getenv("url"),
				System.getenv("db_username"),
				System.getenv("db_password"));
	}

}
