package com.excilys.persistence.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


//Singleton Connection
public enum ConnectionDB {
	CONNECTION;
	private Connection conn;
	
	//Constructor privé
	private ConnectionDB(){
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = new String("jdbc:mysql://localhost:3306/computer-database-db-test"+"?zeroDateTimeBehavior=convertToNull");
			this.conn=DriverManager.getConnection(url, "root", "root");
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public Connection getConn() {
		return conn;
	}	
	
}
