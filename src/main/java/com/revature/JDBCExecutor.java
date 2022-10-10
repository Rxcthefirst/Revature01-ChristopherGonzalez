package com.revature;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * LinkedIn tutorial (Frank P. Moley III) on using JDBC to integrate RDBMS into Java applications.
 */

public class JDBCExecutor {
	
	JDBCExecutor(){
		
		DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost:5433", "revature", "postgres", "root");
		
		try {
			Connection connection = dcm.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT COUNT (*) FROM customer WHERE state LIKE 'MN'");
			while(resultSet.next()){
				System.out.println(resultSet.getInt(1));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}

