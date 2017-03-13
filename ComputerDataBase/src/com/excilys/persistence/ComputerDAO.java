package com.excilys.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.connection.java.ConnectionDB;
import com.excilys.model.java.Computer;

public class ComputerDAO {
	  private Connection conn;
		
	  public ComputerDAO() {
	    
	    this.conn = ConnectionDB.getInstance().getConn();
	    
	  }
	
	  public boolean create(Computer cp) {
	    return false;
	  }

	  public boolean delete(Computer cp) {
	    return false;
	  }
	   
	  public boolean update(Computer cp) {
	    return false;
	  }
	   
	  public Computer find(int id) {
	    Computer computer = new Computer();      
	      
	    try {
	      String sql = "SELECT c.id, c.name, c.introduced, c.discontinued, e.name FROM computer c Left join company e ON c.company_id=e.id WHERE c.id='"+id+"';";
	      System.out.println(sql);
	      ResultSet result = this.conn.createStatement(
	        ResultSet.TYPE_SCROLL_INSENSITIVE,
	        ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
	        
	      if(result.first())
	        computer = new Computer(
	          result.getInt("c.id"),
	          result.getString("c.name"),
	          result.getDate("c.introduced").toLocalDate(),
	          result.getString("e.name")
	          );         
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	    System.out.println(computer.toString());
	    return computer;
	  }
	}