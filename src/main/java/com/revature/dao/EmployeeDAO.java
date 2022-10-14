package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.revature.model.User;
import com.revature.util.DataAccessObject;

public class EmployeeDAO extends DataAccessObject<User>{
	
	private static final String INSERT = "INSERT INTO userdbtest (employee_id, first_name,"
			+ "last_name, username, user_password, is_manager) VALUES (DEFAULT, ?, ?, ?, ?, DEFAULT)";
	
	private static final String GET_ONE = "SELECT employee_id, first_name, last_name, " + 
			"username, password, is_manager FROM userdbtest WHERE employee_id=?";

	public EmployeeDAO(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public User findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User update(User dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User create(User dto) {
		// TODO Auto-generated method stub
		try(PreparedStatement statement = this.connection.prepareStatement(INSERT);){
			statement.setString(1, dto.getFirstName());
			statement.setString(2, dto.getLastName());
            statement.setString(3, dto.getUsername());
            statement.setString(4, dto.getPassword());
            statement.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}
	
	

}
