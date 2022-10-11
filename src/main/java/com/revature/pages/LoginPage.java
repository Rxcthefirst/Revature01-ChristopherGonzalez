package com.revature.pages;

import java.sql.Connection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.jetty.http.HttpStatus;

import com.revature.model.User;

import io.javalin.Javalin;

public class LoginPage {

	public LoginPage(Connection connection, Javalin app, Set<User> users) {

		/*
		 * Before the first intended resource is reached
		 */
		app.before(ctx -> {
			System.out.println("This happens before the http requests make it to their first intended resource.");
		});

		/*
		 * First get request
		 * 
		 * Description: We want the get request to function as a login attempt the valid
		 * get initializes the welcome user page and allows the control flow to move
		 * forward
		 */
		app.get("/user/{username}", ctx -> {

			
			//TODO: Fix the exception being caused by this null object.
			User selectedUser = null;
			System.out.println("The parameter inserted into the HTTP get request is : " + ctx.pathParam("username"));

			for (User u : users) {
				

				if (u.getUsername().equals(ctx.pathParam("username"))) {
					selectedUser = u;
					System.out.println("We have found a match: Selected user is " + selectedUser);
				}
				}
			
			if (selectedUser != null) {
				
				ctx.json(selectedUser);
			} else {
				System.out.println("Parameter entered did not match any users");
			}
		
		});

		/*
		 * Post request
		 * 
		 * This is intended to function as a "register" feature. It allows the client to
		 * create a user in the database. Need to find a way to add functionality to
		 * inform the user when a username is taken.
		 * 
		 */

		app.post("/new-item", ctx -> {

			/*
			 * As a matter of abstraction, sometimes we wish to perform a task before an
			 * HTTP Request makes it to its destination. In order to avoid mixing this
			 * 
			 * If a client is making a POST request, this means that there must be something
			 * in the HTTP request body that we can extract.
			 * 
			 * - Christina
			 */

			User tempUser = ctx.bodyAsClass(User.class);
			

			System.out.println(tempUser);
			ctx.status(HttpStatus.CREATED_201);
		});

		/*
		 * After the last resource is reached
		 */
		app.after(ctx -> {
			System.out.println("This happens after the request has been completed");
		});

	}

}
