package com.excilys.persistence.java;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.excilys.model.java.Company;
import com.excilys.ui.java.App;

//DAO of Company
public enum CompanyDAO implements CompanyDAOInterface{
	COMPANYDAO;
	private Connection conn;
	static Logger logger = LogManager.getLogger();
	private Company company;
	private CompanyDAO() {

		this.conn = ConnectionDB.CONNECTION.getConn();

	}

	public Company find(int id) {
		company = new Company();      

		try {
			String sql = "SELECT c.id, c.name FROM company c WHERE c.id=?;";
			PreparedStatement preparedStmt;
			preparedStmt = this.conn.prepareStatement(sql);
			preparedStmt.setInt(1, id);
			preparedStmt.execute();
			ResultSet result = preparedStmt.getResultSet();
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