package com.excilys.formation.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.excilys.formation.util.ComputerDBException;



//Singleton Connection
public enum ConnectionDB {
	CONNECTION;
	private Connection conn;
	Logger logger = LogManager.getLogger();
	
	//Constructor privé
	private ConnectionDB() throws ComputerDBException{
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = new String("jdbc:mysql://localhost:3306/computer-database-db-test"+"?zeroDateTimeBehavior=convertToNull");
			this.conn=DriverManager.getConnection(url, "admincdb", "qwerty1234");
			logger.info("ConnectionDB instantiated");
		}catch( ComputerDBException | InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e){
			logger.error("ConnectionDB can not be instantiated " );
			throw new ComputerDBException("ConnectionDB can not be instantiated", e);
		}
	}


	public Connection getConn() {
		return conn;
	}	
	
}
