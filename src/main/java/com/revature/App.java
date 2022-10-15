package com.revature;

import java.sql.Connection;

/*
 * Main file in Java API application using Javalin and JDBC with PostgreSQL
 */

import java.sql.SQLException;

import com.revature.dao.EmployeeDAO;
import com.revature.model.User;
import com.revature.pages.LoginPage;
import com.revature.util.ConnectionFactory;

public class App {

	public static void main(String[] args) throws SQLException {

		// Instantiate Javalin Driver
		Driver driver = new Driver();

		// Establish connection to PostgreSQL server using JDBC

		LoginPage loginPage = new LoginPage(driver.getApp());

		try (Connection connection = ConnectionFactory.getConnection();) {
			// Establish connection to PostGreSQL server
			EmployeeDAO employeeDAO = new EmployeeDAO(connection);
			User user = new User();
			user.setFirstName("John");
			user.setLastName("Adams");
			user.setUsername("jadams");
			user.setPassword("1234 Main St");
			user.setManager(false);

			User dbUser = employeeDAO.create(user);
			System.out.println(dbUser);
			dbUser = employeeDAO.findById(dbUser.getId());
			System.out.println(dbUser);
			dbUser.setPassword("indahouse");
			dbUser = employeeDAO.update(dbUser);
			System.out.println(dbUser);

			employeeDAO.delete(dbUser.getId());

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}


	}

}
