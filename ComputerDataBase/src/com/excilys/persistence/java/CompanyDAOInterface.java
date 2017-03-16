package com.excilys.persistence.java;

import java.util.List;

import com.excilys.model.java.Company;

public interface CompanyDAOInterface {
	public abstract Company find(long id);
	public abstract List<Company>  findAll(); 
}
