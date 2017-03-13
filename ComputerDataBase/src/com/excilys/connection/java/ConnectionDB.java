package com.excilys.connection.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionDB {
	private static ConnectionDB connDB;
	private Connection conn;

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

	public static ConnectionDB getInstance(){
		if(connDB == null){
			connDB = new ConnectionDB();

		}
		return connDB;


	}




}
