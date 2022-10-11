package com.revature;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * LinkedIn tutorial (Frank P. Moley III) on using JDBC to integrate RDBMS into Java applications.
 */

public class JDBCExecutor {
	
	private Connection connection;

	JDBCExecutor(){
				
		DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost:5433", "revature", "postgres", "root");
		
		try {
			this.connection = dcm.getConnection();
			/*
			 * Statement statement = connection.createStatement(); ResultSet resultSet =
			 * statement.executeQuery("SELECT username, u_password FROM usertest2");
			 * //System.out.println(resultSet); while(resultSet.next()){
			 * System.out.println(resultSet.getString(1) + " " + resultSet.getString(2) ); }
			 */
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() throws SQLException{
		return this.connection;
	}

}

