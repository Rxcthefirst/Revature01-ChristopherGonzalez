package com.revature;

/*
 * Main file in Java API application using Javalin and JDBC with PostgreSQL
 */

import java.sql.SQLException;

import com.revature.model.UserInfo;
import com.revature.pages.LoginPage;

public class App {
	
	public static void main(String[] args) throws SQLException {
		
		
		// Establish connection to PostgreSQL server using JDBC
		JDBCExecutor connection = new JDBCExecutor();
		
		// Instantiate Javalin Driver
		Driver driver = new Driver();
		
		UserInfo userInfo = new UserInfo(connection.getConnection());
		
		LoginPage loginPage = new LoginPage(connection.getConnection(), driver.getApp(), userInfo.getLoginInfo());
		
		
	}

}
