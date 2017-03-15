package com.excilys.service.main.java;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.excilys.mapper.java.CompanyMapper;
import com.excilys.model.java.Company;
import com.excilys.persistence.java.CompanyDAO;
import com.excilys.persistence.java.CompanyDAOInterface;
import com.excilys.ui.java.CompanyModelUI;

public class CompanyService {
	static Logger logger = LogManager.getLogger();
	private CompanyDAOInterface cydaoi = CompanyDAO.COMPANYDAO;
	
	public  CompanyService(){
			
	}

	public CompanyModelUI find(int id) {
		
		return new CompanyMapper(cydaoi.find(id)).getCymui();
	}

	public List<CompanyModelUI>  findAll(){
		List<Company> lcp = cydaoi.findAll();
		List<CompanyModelUI> lcy = new ArrayList<CompanyModelUI>();
		for(int i=0;i<lcp.size();i++){
			lcy.add(new CompanyMapper(lcp.get(i)).getCymui());
		}
		return lcy;
	}
	
	
	
	
}
