package com.excilys.persistence.java;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.connection.java.ConnectionDB;
import com.excilys.model.java.Company;

public class CompanyDAO {
	private Connection conn;

	public CompanyDAO() {

		this.conn = ConnectionDB.getInstance().getConn();

	}

	public int createCompany(Company cp) {
		ResultSet rs = null;
		try{

			String query = "insert into company"+"(name)"+"values (?)";

			PreparedStatement preparedStmt = this.conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			preparedStmt.setString(1,cp.getName());

			
			preparedStmt.execute();

			rs = preparedStmt.getGeneratedKeys();
			if(rs.next()){
			return rs.getInt(1);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}return -1;

	}       

	public boolean delete(Company cp) {
		try {
		  String query = "DELETE FROM company WHERE id = ?";
		  
	      PreparedStatement preparedStmt = this.conn.prepareStatement(query);
	      preparedStmt.setInt(1, cp.getId());
	      preparedStmt.execute();
	      
	      return true;
		}catch(SQLException e){
			
			e.printStackTrace();
			
		}return false;
	}

	public boolean update(Company cp) {
		String query = "UPDATE company SET name = ? WHERE id = ?";
		PreparedStatement preparedStmt;
		try {
			preparedStmt = this.conn.prepareStatement(query);
			preparedStmt.setString(1, cp.getName());
			preparedStmt.setInt(2,cp.getId());
			preparedStmt.executeUpdate();
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}return false;
	}

	public Company find(int id) {
		Company company = new Company();      

		try {
			String sql = "SELECT c.id, c.name FROM company c WHERE c.id='"+id+"';";
			ResultSet result = this.conn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
			if(result.first())
				company = new Company(
						result.getInt("c.id"),
						result.getString("c.name")
						); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return company;
	}
	
	public List<Company>  findAll() {
		List<Company> lcp = new ArrayList<Company>();    
		Company cp;
		try {
			String sql = "SELECT c.id, c.name FROM company c;";
			ResultSet result = this.conn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
			while(result.next()){
				cp = new Company(
						result.getInt("c.id"),
						result.getString("c.name")
						); 
				lcp.add(cp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lcp;
	}


}