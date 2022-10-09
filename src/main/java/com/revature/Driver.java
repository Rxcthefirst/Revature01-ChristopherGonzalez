package com.revature;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.jetty.http.HttpStatus;

import com.revature.model.Person;

import io.javalin.Javalin;


public class Driver {
	
	public static void main(String[] args) {
		
		
		/*
		 * Connect the database using JDBC and PostgreSQL
		 */
		
		Connection connection = null;
		
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "F501vb12!");
			
			
			if (connection!=null) {
				System.out.println("Connection OK");
			} else {
				System.out.println("Connection Failed");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
		/*
		 * Javalin is consider lightweight framework for quickly building RESTful APIs
		 * It comes with an embedded Jetty server and is an abstraction over an older tech called servlets.
		 */
		
		
		// Starts our server on port 8000 instead of default 8080;
		Javalin app = Javalin.create().start(8000);
		
		app.before(ctx -> {
			System.out.println("This happens before the http requests make it to their first intended resource.");
		});
		
		
		app.get("/person/{id}", ctx -> {
			
			Set<Person> people = new HashSet<>();
			Person person1 = new Person(1, "Sean", 23);
			Person person2 = new Person(2, "Zeke", 22);
			Person person3 = new Person(3, "Jen", 25);
		
			people.add(person1);
			people.add(person2);
			people.add(person3);
			
			Person selectedPerson = null;
			
			for (Person p : people) {
				if (p.getId() == Integer.parseInt(ctx.pathParam("id"))) selectedPerson = p;
			}
			
			ctx.json(selectedPerson);
		
		});
		
		app.post("/new-item", ctx -> {
			/*	
			 * As a matter of abstraction, sometimes we wish to perform a task before an HTTP Request makes it to its desination.
			 * In order to avoid mixing this 
			 * 
			 * If a client is making a POST request, this means that there must be something
			 * in the HTTP request body that we can extract.
			 */
			
			
			Person receivedPerson = ctx.bodyAsClass(Person.class);
			System.out.println(receivedPerson);
			ctx.status(HttpStatus.CREATED_201);
		});
		
		app.after(ctx -> {
			System.out.println("This happens after the request has been completed");
		});
	}

}