package com.revature;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jetty.http.HttpStatus;

import com.revature.model.User;

import io.javalin.Javalin;


public class Driver {
	
	Driver(){
		
		
		
		
		/*
		 * Javalin is consider lightweight framework for quickly building RESTful APIs
		 * It comes with an embedded Jetty server and is an abstraction over an older tech called servlets.
		 */
		
		
		// Starts our server on port 8000 instead of default 8080;
		Javalin app = Javalin.create().start(8000);
		
		app.before(ctx -> {
			System.out.println("This happens before the http requests make it to their first intended resource.");
		});
		
		
		app.get("/user/{id}", ctx -> {
			
			Set<User> users = new HashSet<>();
			User user1 = new User(1, "Sean", "Mendelson", "Bossman007","Password", true);
			User user2 = new User(2, "Zeke","","", "", false);
			User user3 = new User(3, "Jen", "","","", false);
		
			users.add(user1);
			users.add(user2);
			users.add(user3);
			
			User selectedUser = null;
			
			for (User u : users) {
				if (u.getId() == Integer.parseInt(ctx.pathParam("id"))) selectedUser = u;
			}
			
			ctx.json(selectedUser);
		
		});
		
		app.post("/new-item", ctx -> {
			/*	
			 * As a matter of abstraction, sometimes we wish to perform a task before an HTTP Request makes it to its destination.
			 * In order to avoid mixing this 
			 * 
			 * If a client is making a POST request, this means that there must be something
			 * in the HTTP request body that we can extract.
			 */
			
			
			User receivedUser = ctx.bodyAsClass(User.class);
			
			System.out.println(receivedUser);
			ctx.status(HttpStatus.CREATED_201);
		});
		
		app.after(ctx -> {
			System.out.println("This happens after the request has been completed");
		});
	
	}
}