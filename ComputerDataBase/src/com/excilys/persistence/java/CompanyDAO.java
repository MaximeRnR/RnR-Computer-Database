package com.excilys.persistence.java;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.excilys.connection.java.ConnectionDB;
import com.excilys.main.java.App;
import com.excilys.model.java.Company;

public class CompanyDAO {
	private Connection conn;
	static Logger logger = LogManager.getLogger();
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
				logger.info("Company "+ rs.getInt(1) +" created");
			return rs.getInt(1);
			}
		}catch (SQLException e) {
			e.printStackTrace();
			logger.error("Company create SQL ERROR" );
		}logger.error("Company did'nt get created" );
		return -1;
	}       

	public boolean delete(Company cp) {
		try {
		  String query = "DELETE FROM company WHERE id = ?";
		  
	      PreparedStatement preparedStmt = this.conn.prepareStatement(query);
	      preparedStmt.setInt(1, cp.getId());
	      preparedStmt.execute();
	      logger.info("Company "+ cp.getId() +" created");
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
			logger.info("Company "+ cp.getId() +" updated");
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
			if(company.getId()==0){
				throw new IllegalArgumentException();
			}
			logger.info("Company "+ company.getId() +" selected");
		} catch (Exception e) {
			logger.error("Company not selected ");
			System.out.println("This company does'nt exist");
			App.menu();
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
			if(!result.first())
				throw new IllegalArgumentException();
			while(result.next()){
				cp = new Company(
						result.getInt("c.id"),
						result.getString("c.name")
						); 
				lcp.add(cp);
			}
			logger.info("Companies selected");
		} catch (Exception e) {
			logger.error("Companies not selected ");
			App.menu();
		}

		return lcp;
	}


}