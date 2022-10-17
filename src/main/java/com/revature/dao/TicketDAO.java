package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import com.revature.model.Ticket;
import com.revature.util.DataAccessObject;

public class TicketDAO extends DataAccessObject<Ticket> {
	
	private static final String INSERT = "INSERT INTO ticketdb (ticket_id, user_id, amount,"
			+ "description, ticket_status) VALUES (DEFAULT, ?, ?, ?, DEFAULT)";
	
	private static final String GET_ONE = "SELECT ticket_id, user_id, amount, description, " + 
			"ticket_status FROM ticketdb WHERE ticket_id = ?";
	
	private static final String UPDATE = "UPDATE ticketdb SET amount = ?, description = ?, " +
			"ticket_status = ? WHERE ticket_id = ?";
	
	private static final String DELETE = "DELETE FROM ticketdb WHERE ticket_id = ?";

	public TicketDAO(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Ticket findById(long id) {
		// TODO Auto-generated method stub
		Ticket ticket = new Ticket();
		try(PreparedStatement statement = this.connection.prepareStatement(GET_ONE);){
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				ticket.setTicketID(rs.getLong("ticket_id"));
				ticket.setUserID(rs.getLong("user_id"));
				ticket.setAmount(rs.getDouble("amount"));
				ticket.setDescription(rs.getString("description"));
				ticket.setStatus(rs.getString("ticket_status"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return ticket;
	}

	@Override
	public Set<Ticket> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ticket update(Ticket dto) {
		// TODO Auto-generated method stub
		Ticket ticket = new Ticket();
		try(PreparedStatement statement = this.connection.prepareStatement(UPDATE);){
			statement.setDouble(1, dto.getAmount());
			statement.setString(2, dto.getDescription());
            statement.setString(3, dto.getStatus());
            statement.setLong(4, dto.getId());
            statement.execute();
            ticket = this.findById(dto.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return ticket;
	}

	@Override
	public Ticket create(Ticket dto) {
		// TODO Auto-generated method stub
		try(PreparedStatement statement = this.connection.prepareStatement(INSERT);){
			statement.setLong(1, dto.getUserID());
			statement.setDouble(2, dto.getAmount());
            statement.setString(3, dto.getDescription());
            statement.execute();
            int id = this.getLastVal(TICKET_SEQUENCE);
            System.out.println(id);
            return this.findById(id);
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		try(PreparedStatement statement = this.connection.prepareStatement(DELETE);){
			statement.setLong(1, id);
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	
	

}
