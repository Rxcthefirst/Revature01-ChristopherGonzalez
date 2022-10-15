package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.revature.model.User;
import com.revature.util.DataAccessObject;

public class EmployeeDAO extends DataAccessObject<User>{
	
	private static final String INSERT = "INSERT INTO userdbtest (employee_id, first_name,"
			+ "last_name, username, user_password, is_manager) VALUES (DEFAULT, ?, ?, ?, ?, DEFAULT)";
	
	private static final String GET_ONE = "SELECT employee_id, first_name, last_name, " + 
			"username, user_password, is_manager FROM userdbtest WHERE employee_id=?";
	
	private static final String UPDATE = "UPDATE userdbtest SET first_name = ?, last_name = ?, " +
			"username = ?, user_password = ? WHERE employee_id = ?";
	
	private static final String DELETE = "DELETE FROM userdbtest WHERE employee_id = ?";

	public EmployeeDAO(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public User findById(long id) {
		// TODO Auto-generated method stub
		User user = new User();
		try(PreparedStatement statement = this.connection.prepareStatement(GET_ONE);){
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				user.setId(rs.getLong("employee_id"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("user_password"));
				user.setManager(rs.getBoolean("is_manager"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return user;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User update(User dto) {
		// TODO Auto-generated method stub
		User user = new User();
		try(PreparedStatement statement = this.connection.prepareStatement(UPDATE);){
			statement.setString(1, dto.getFirstName());
			statement.setString(2, dto.getLastName());
            statement.setString(3, dto.getUsername());
            statement.setString(4, dto.getPassword());
            statement.setLong(5, dto.getId());
            statement.execute();
            user = this.findById(dto.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return user;
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
            int id = this.getLastVal(EMPLOYEE_SEQUENCE);
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
	
	


