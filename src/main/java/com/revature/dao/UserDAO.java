package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.revature.model.User;
import com.revature.util.DataAccessObject;

public class UserDAO extends DataAccessObject<User>{
	
	private static final String INSERT = "INSERT INTO userdb (user_id, first_name,"
			+ "last_name, username, user_password, is_manager) VALUES (DEFAULT, ?, ?, ?, ?, DEFAULT)";
	
	private static final String GET_ONE = "SELECT user_id, first_name, last_name, " + 
			"username, user_password, is_manager FROM userdb WHERE user_id = ?";
	
	private static final String GET_USERINFO = "SELECT user_id, first_name, last_name, " + 
			"username, user_password, is_manager FROM userdb WHERE username = ?";
	
	private static final String UPDATE = "UPDATE userdb SET first_name = ?, last_name = ?, " +
			"username = ?, user_password = ? WHERE user_id = ?";
	
	private static final String DELETE = "DELETE FROM userdb WHERE user_id = ?";

	private static final String FINDALL = "SELECT * FROM userdb";

	public UserDAO(Connection connection) {
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
				user.setId(rs.getLong("user_id"));
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
	
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		User user = new User();
		try(PreparedStatement statement = this.connection.prepareStatement(GET_USERINFO);){
			statement.setString(1, "username");
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				user.setId(rs.getLong("user_id"));
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
	public Set<User> findAll() {
		// TODO Auto-generated method stub
		Set<User> userList = new HashSet<User>();
		
		try(PreparedStatement statement = this.connection.prepareStatement(FINDALL);){
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
            	userList.add(new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getBoolean(6)));
            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return userList;
		
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
            statement.executeUpdate();
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
            statement.executeUpdate();
            int id = this.getLastVal(USER_SEQUENCE);
            return this.findById(id);
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		try(PreparedStatement statement = this.connection.prepareStatement(DELETE);){
			statement.setLong(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public User update(User dto, String result) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
	
	


