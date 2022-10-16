package com.revature.pages;

import java.sql.Connection;
import java.sql.SQLException;

import org.eclipse.jetty.http.HttpStatus;

import com.revature.dao.TicketDAO;
import com.revature.model.Ticket;
import com.revature.model.User;
import com.revature.util.ConnectionFactory;

import io.javalin.Javalin;

public class EmployeePage {

	EmployeePage(User currentUser, Javalin app) {
		
		
		System.out.println("Welcome " + currentUser.getFirstName());
		System.out.println(currentUser);

		app.before(ctx -> {
			System.out.println("This happens before the http requests make it to their first intended resource.");
		});

		app.get("/user/{username}", ctx -> {

			User selectedUser = null;
			System.out.println("The parameter inserted into the HTTP get request is : " + ctx.pathParam("username"));

		});

		app.get("/view-tickets", ctx -> {

			Ticket tempTicket = ctx.bodyAsClass(Ticket.class);

			try (Connection conn = ConnectionFactory.getConnection();) {
				TicketDAO ticketDAO = new TicketDAO(conn);
				Ticket ticket = ticketDAO.create(tempTicket);
				System.out.println("Ticket " + ticket.getId() + " TicketID successfully");
			} catch (SQLException e) {
				e.printStackTrace();
			}

			ctx.status(HttpStatus.CREATED_201);
		});

		app.post("/submit", ctx -> {

			Ticket tempTicket = ctx.bodyAsClass(Ticket.class);

			try (Connection conn = ConnectionFactory.getConnection();) {
				TicketDAO ticketDAO = new TicketDAO(conn);
				Ticket ticket = ticketDAO.create(tempTicket);
				System.out.println("Ticket " + ticket.getId() + " TicketID successfully");
			} catch (SQLException e) {
				e.printStackTrace();
			}

			ctx.status(HttpStatus.CREATED_201);
		});

		app.get("/logout", ctx -> {

			LoginPage loginPage = new LoginPage(app);

			ctx.status(HttpStatus.ACCEPTED_202);
		});

		app.after(ctx -> {
			System.out.println("This happens after the request has been completed");
		});

	}

}
