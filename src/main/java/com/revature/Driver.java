package com.revature;

import java.sql.SQLException;

import com.revature.pages.LoginPage;

import io.javalin.Javalin;

public class Driver {
	
	public static void main(String[] args) throws SQLException {

		// Instantiate Javalin Driver
		Javalin app = Javalin.create().start(8000);
		// Create Login Page
		LoginPage loginPage = new LoginPage(app);



	}
}