package com.revature;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Queue;
import java.util.Set;

import org.eclipse.jetty.http.HttpStatus;

import com.revature.dao.TicketDAO;
import com.revature.dao.UserDAO;
import com.revature.model.Ticket;
import com.revature.model.User;
import com.revature.util.ConnectionFactory;

import io.javalin.Javalin;
import jakarta.servlet.http.Cookie;

public class Driver {

	public static void main(String[] args) throws SQLException {

		// Keep track of current user
		User currentUser = new User();

		// Instantiate Javalin Driver
		Javalin app = Javalin.create().start(8000);

		app.before(ctx -> {
			System.out.println("START HTTP REQUEST");
		});

		// Login

		app.post("/login", ctx -> {

			// Read in JSON object
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
							Cookie cookie = new Cookie("authenticated", "true");
							if (employeeDAO.findById(user.getId()).isManager()) {
								System.out.println("Manager Access Granted");
								ctx.result("Manager Access Granted");
								ctx.res().addCookie(cookie);

							} else {
								System.out.println("User Access Granted");
								ctx.result("User Access Granted");
								ctx.res().addCookie(cookie);

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

		// Registration

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

		// Ticket Viewer

		app.get("/view-tickets", ctx -> {

			Cookie[] cookies = ctx.req().getCookies();

			if (cookies != null) {

				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("authenticated") && cookie.getValue().equals("true")) {

						try (Connection conn = ConnectionFactory.getConnection();) {
							TicketDAO ticketDAO = new TicketDAO(conn);
							Set<Ticket> tickets = ticketDAO.findAllByUserId(currentUser.getId());

							ctx.result(tickets.toString());

						} catch (SQLException e) {
							e.printStackTrace();
						}

						ctx.status(HttpStatus.ACCEPTED_202);
					} else {
						ctx.result("User not authenticated. Please log in.");
						ctx.status(HttpStatus.UNAUTHORIZED_401);
					}

				}
			} else {
				ctx.result("User not authenticated. Please log in.");
				ctx.status(HttpStatus.UNAUTHORIZED_401);
			}

		});

		// Ticket Submission

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

		// Logout

		app.get("/logout", ctx -> {

			Cookie[] cookies = ctx.req().getCookies();

			if (cookies != null) {
				boolean match = false;

				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("authenticated") && cookie.getValue().equals("true")) {
						match = true;
						cookie.setValue("false");
						ctx.res().addCookie(cookie);
						ctx.result("Logout Successful");

						if (currentUser.getUsername() != null) {
							currentUser.setId(0);
							currentUser.setFirstName(null);
							currentUser.setLastName(null);
							currentUser.setUsername(null);
							currentUser.setPassword(null);
							currentUser.setManager(false);
						}
					}
					
					if (!match) {
						ctx.result("User not previously authenticated. Are you sure that you have already logged in?");
						ctx.status(HttpStatus.BAD_REQUEST_400);
					}

				}
			} else {
				ctx.result("User not previously authenticated. Are you sure that you have already logged in?");
				ctx.status(HttpStatus.BAD_REQUEST_400);
			}

		});

		// Ticket Viewer

		app.get("/view-pending-tickets", ctx -> {
			
			Cookie[] cookies = ctx.req().getCookies();

			if (cookies != null) {
				boolean match = false;

				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("authenticated") && cookie.getValue().equals("true")) {
						match = true;
					}
					
					if (match) {
						
						if (currentUser.isManager()) {


							try (Connection conn = ConnectionFactory.getConnection();) {
								TicketDAO ticketDAO = new TicketDAO(conn);
								Queue<Ticket> tickets = ticketDAO.findAllPending();
								for (Ticket ticket : tickets) {
									System.out.println(ticket);
								}

								ctx.result(tickets.toString());
							} catch (SQLException e) {
								e.printStackTrace();
							}

							ctx.status(HttpStatus.ACCEPTED_202);
						} else {
							ctx.status(HttpStatus.FORBIDDEN_403);
							ctx.result("You do not have authorization to perform this operation");
						}
						
					} else {
						ctx.result("User not previously authenticated. Are you sure that you have already logged in?");
						ctx.status(HttpStatus.BAD_REQUEST_400);
					}
				}
			} else {
				ctx.result("User not previously authenticated. Are you sure that you have already logged in?");
				ctx.status(HttpStatus.BAD_REQUEST_400);
			}

			
		});

		app.put("/result-ticket", ctx -> {
			
			
			Cookie[] cookies = ctx.req().getCookies();

			if (cookies != null) {
				boolean match = false;

				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("authenticated") && cookie.getValue().equals("true")) {
						match = true;
					}
					
					if (match) {
						
						Ticket resultedTicket = ctx.bodyAsClass(Ticket.class);

						if (currentUser.isManager()) {
							if (resultedTicket.getStatus().toLowerCase().equals("Denied".toLowerCase())
									|| resultedTicket.getStatus().toLowerCase().equals("Approved".toLowerCase())) {
								System.out.println("Validation Test");

								try (Connection conn = ConnectionFactory.getConnection();) {
									TicketDAO ticketDAO = new TicketDAO(conn);
									Ticket actualTicket = ticketDAO.findById(resultedTicket.getTicketID());
									System.out.println("Actual Ticket: " + actualTicket);
									System.out.println("resultedTicket: " + resultedTicket);

									actualTicket.setStatus(resultedTicket.getStatus());
									System.out.println("Actual Ticket after setting status: " + actualTicket);

									resultedTicket = ticketDAO.update(actualTicket, resultedTicket.getStatus());
									Ticket testTicket = ticketDAO.findById(resultedTicket.getTicketID());

									System.out.println(testTicket);

									System.out.println(actualTicket);
									System.out.println(resultedTicket);

									ctx.result("Ticket updated Succesfully" + resultedTicket.toString());
								} catch (SQLException e) {
									e.printStackTrace();
								}

								ctx.status(HttpStatus.ACCEPTED_202);
							} else {
								System.out.println("Invalid Result Type");
								ctx.result("Invalid Result Type");
							}
						} else {
							ctx.status(HttpStatus.FORBIDDEN_403);
							ctx.result("You do not have authorization to perform this operation");

						}
						
					} else {
						ctx.result("User not previously authenticated. Are you sure that you have already logged in?");
						ctx.status(HttpStatus.BAD_REQUEST_400);
					}
				}
			} else {
				ctx.result("User not previously authenticated. Are you sure that you have already logged in?");
				ctx.status(HttpStatus.BAD_REQUEST_400);
			}

		});



		app.after(ctx -> {
			System.out.println("HTTP REQUEST COMPLETE");
		});

	}
}