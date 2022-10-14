package com.revature;

/*
 * Main file in Java API application using Javalin and JDBC with PostgreSQL
 */

import java.sql.SQLException;

import com.revature.pages.LoginPage;

public class App {
	
	public static void main(String[] args) throws SQLException {
		
		// Instantiate Javalin Driver
		Driver driver = new Driver();

		// Establish connection to PostgreSQL server using JDBC
		
		
		LoginPage loginPage = new LoginPage(driver.getApp());
		
		
	}

}
