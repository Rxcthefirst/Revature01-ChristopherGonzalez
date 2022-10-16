package com.revature.pages;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

import org.eclipse.jetty.http.HttpStatus;

import com.revature.dao.UserDAO;
import com.revature.model.User;
import com.revature.util.ConnectionFactory;

import io.javalin.Javalin;

public class LoginPage {

	public LoginPage(Javalin app) {

		System.out.println("Welcome to Revature's Reimbusement Ticketing Application:");

		/*
		 * Before the first intended resource is reached
		 */
		app.before(ctx -> {
			System.out.println("START HTTP REQUEST");
		});

		app.post("/login", ctx -> {

			User tempUser = ctx.bodyAsClass(User.class);

			try (Connection conn = ConnectionFactory.getConnection();) {
				UserDAO employeeDAO = new UserDAO(conn);
				Set<User> userList = employeeDAO.findAll();
				boolean match = false;
				for (User user : userList) {
					if (user.getUsername().equals(tempUser.getUsername())) {
						if (user.getPassword().equals(tempUser.getPassword())) {
							System.out.println("Username and password are a match. Login successful!");
							match = true;
							if (employeeDAO.findById(user.getId()).isManager()) {
								System.out.println(user.getPassword().equals(tempUser.getPassword()));
								AdminPage adminPage = new AdminPage(user, app);
							} else {
								EmployeePage loginPage = new EmployeePage(user, app);
								System.out.println(user.getPassword().equals(tempUser.getPassword()));
							}
						}
					}
				}
				if (!match) {
					System.out.println("Login Unsuccessful");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

			ctx.status(HttpStatus.ACCEPTED_202);
		});

		app.post("/register", ctx -> {

			User tempUser = ctx.bodyAsClass(User.class);

			try (Connection conn = ConnectionFactory.getConnection();) {
				UserDAO employeeDAO = new UserDAO(conn);
				User user = employeeDAO.create(tempUser);
				System.out.println("User " + user.getUsername() + " registered successfully");
			} catch (SQLException e) {
				e.printStackTrace();
			}

			ctx.status(HttpStatus.CREATED_201);
		});

		/*
		 * After the last resource is reached
		 */
		app.after(ctx -> {
			System.out.println("HTTP REQUEST COMPLETE");
		});

	}

}
