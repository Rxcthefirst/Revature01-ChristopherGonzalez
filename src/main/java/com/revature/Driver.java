package com.revature;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

import org.eclipse.jetty.http.HttpStatus;

import com.revature.dao.TicketDAO;
import com.revature.dao.UserDAO;
import com.revature.model.Ticket;
import com.revature.model.User;
import com.revature.util.ConnectionFactory;

import io.javalin.Javalin;

public class Driver {

	public static void main(String[] args) throws SQLException {

		// Keep track of current user
		User currentUser = new User();

		// Instantiate Javalin Driver
		Javalin app = Javalin.create().start(8000);

		app.before(ctx -> {
			System.out.println("START HTTP REQUEST");
		});

		//Login

		app.post("/login", ctx -> {

			//Read in JSON object
			User tempUser = ctx.bodyAsClass(User.class);

			try (Connection conn = ConnectionFactory.getConnection();) {
				UserDAO employeeDAO = new UserDAO(conn);
				Set<User> userList = employeeDAO.findAll();
				boolean match = false;
				for (User user : userList) {
					if (user.getUsername().equals(tempUser.getUsername())) {
						if (user.getPassword().equals(tempUser.getPassword())) {
							ctx.result("Username and password are a match.Login successful!");
							System.out.println("Username and password are a match. Login successful!");
							match = true;
							currentUser.setId(user.getId());
							currentUser.setFirstName(user.getFirstName());
							currentUser.setLastName(user.getLastName());
							currentUser.setUsername(user.getUsername());
							currentUser.setPassword(user.getPassword());
							currentUser.setManager(user.isManager());
							if (employeeDAO.findById(user.getId()).isManager()) {
								System.out.println("Manager Access Granted");
								ctx.result("Manager Access Granted");

							} else {
								System.out.println("User Access Granted");
								ctx.result("User Access Granted");

							}
						}
					}
				}
				if (!match) {
					// System.out.println("Login Unsuccessful");
					ctx.result("Login Unsuccessful");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

			ctx.status(HttpStatus.ACCEPTED_202);

		});

		//Registration

		app.post("/register", ctx -> {

			User tempUser = ctx.bodyAsClass(User.class);
			System.out.println(tempUser);

			try (Connection conn = ConnectionFactory.getConnection();) {
				UserDAO employeeDAO = new UserDAO(conn);
				Set<User> userList = employeeDAO.findAll();
				boolean match = false;
				for (User user : userList) {
					if (user.getUsername().equals(tempUser.getUsername())) {
						match = true;
					}
				}
				if (match) {
					System.out.println("Username is taken");
					ctx.result("Username is taken");
				} else {
					if (tempUser.getPassword() == null || tempUser.getPassword().length() < 4) {
						ctx.result("Invalid Password");
					} else {

						User user = employeeDAO.create(tempUser);
						System.out.println("User " + user.getUsername() + " registered successfully");
						ctx.result("Registration successful");
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			ctx.status(HttpStatus.CREATED_201);
		});

		//Ticket Viewer

		app.get("/view-tickets", ctx -> {
			
			System.out.println(currentUser.getFirstName());

			try (Connection conn = ConnectionFactory.getConnection();) {
				TicketDAO ticketDAO = new TicketDAO(conn);
				Set<Ticket> tickets = ticketDAO.findAllByUserId(currentUser.getId());
				
				ctx.result(tickets.toString());

			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			

			ctx.status(HttpStatus.ACCEPTED_202);
		});

		//Ticket Submission

		app.post("/submit", ctx -> {

			Ticket tempTicket = ctx.bodyAsClass(Ticket.class);
			tempTicket.setUserID(currentUser.getId());


			try (Connection conn = ConnectionFactory.getConnection();) {
				TicketDAO ticketDAO = new TicketDAO(conn);
				Ticket ticket = ticketDAO.create(tempTicket);
				System.out.println("Ticket: " + ticket.getTicketID() + " created succesfully.");
				ctx.result("Ticket: " + ticket.getTicketID() + " created successfully.");
			} catch (SQLException e) {
				e.printStackTrace();
				ctx.result("Ticket submission not successful.");
			}

			ctx.status(HttpStatus.CREATED_201);
		});

		//Logout

		app.get("/logout", ctx -> {

			ctx.status(HttpStatus.ACCEPTED_202);
		});

		app.after(ctx -> {
			System.out.println("HTTP REQUEST COMPLETE");
		});

	}
}