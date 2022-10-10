package com.revature;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {

	/*
	 * Connect the database using JDBC and PostgreSQL
	 */
	
	ConnectDB() {
		
	
	
	Connection connection = null;

	try
	{
		Class.forName("org.postgresql.Driver");
		connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/postgres", "postgres", "root");

		if (connection != null) {
			System.out.println("Connection OK");
		} else {
			System.out.println("Connection Failed");
		}
	}catch(Exception e)
	{
		// TODO: handle exception
		//e.printStackTrace();
		System.out.println(e);
	}

}
}