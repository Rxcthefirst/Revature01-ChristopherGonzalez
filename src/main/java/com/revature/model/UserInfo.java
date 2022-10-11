package com.revature.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.HashSet;
import java.util.Set;

public class UserInfo {

	Set<User> loginInfo = new HashSet<>();

	public UserInfo(Connection connection) {

		try {

			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM userdb");
			// System.out.println(resultSet);
			while (resultSet.next()) {
				//System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
				loginInfo.add(new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getBoolean(6)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public Set<User> getLoginInfo() {
		return loginInfo;
	}

}
