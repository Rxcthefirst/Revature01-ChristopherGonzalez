package com.revature.dao;

import java.sql.Connection;
import java.util.List;

import com.revature.model.Ticket;
import com.revature.util.DataAccessObject;

public class TicketDAO extends DataAccessObject<Ticket> {
	
	private static final String INSERT = "INSERT INTO ticketdbtest (ticket_id, employee_id, first_name,"
			+ "last_name, username, user_password, is_manager) VALUES (DEFAULT, ?, ?, ?, ?, DEFAULT)";

	public TicketDAO(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Ticket findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ticket> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ticket update(Ticket dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ticket create(Ticket dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
