package com.revature;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestConnectionMethod {
	
	public static void TestMethodConnection(String tableName, String condition) {
		
		String sqlCommand = "SELECT * FROM " + tableName + " " +  condition;
		
		try(Connection conn = DriverManager.getConnection(
                System.getenv("url"), 
                System.getenv("db_username"), 
                System.getenv("db_password")
            );
        		PreparedStatement stmt = conn.prepareStatement(sqlCommand);) {
           
            stmt.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
        }
	}
	
	public static void deleteRecord(String tableName, String condition)
    {
        String sqlCommand = "DELETE FROM" + tableName + " " + condition;

        try(Connection conn = DriverManager.getConnection(
                System.getenv("url"), 
                System.getenv("db_username"), 
                System.getenv("db_password")
            );
        		PreparedStatement stmt = conn.prepareStatement(sqlCommand);) {
           
            stmt.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

}
