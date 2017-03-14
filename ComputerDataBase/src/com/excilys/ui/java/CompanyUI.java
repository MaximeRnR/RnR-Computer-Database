package com.excilys.ui.java;

import java.util.List;

import com.excilys.model.java.Company;
import com.excilys.persistence.java.CompanyDAO;

public class CompanyUI {
	private Company cp;
	private CompanyDAO cpDAO;
	
	public Company FindAssociatedCompany(int id){

		CompanyDAO cpDAO = new CompanyDAO();
		cp = cpDAO.find(id);
		return cp;	      
		
	}
	
	public List<Company> FindAllCompany(){

		cpDAO = new CompanyDAO();
		return cpDAO.findAll();
		

	}
}
