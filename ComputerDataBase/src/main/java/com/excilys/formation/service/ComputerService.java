package com.excilys.formation.service;


import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.excilys.formation.mapper.CompanyMapper;
import com.excilys.formation.mapper.ComputerMapper;
import com.excilys.formation.model.Company;
import com.excilys.formation.model.Computer;
import com.excilys.formation.persistence.ComputerDAO;
import com.excilys.formation.persistence.ComputerDAOInterface;
import com.excilys.formation.ui.CompanyModelUI;
import com.excilys.formation.ui.ComputerModelUI;


public class ComputerService {
	static Logger logger = LogManager.getLogger();
	private ComputerDAOInterface cpdaoi = ComputerDAO.COMPUTERDAO;
	
	
	public ComputerService() {

	}

	public long createComputer(ComputerModelUI cpmui) {
		
		return cpdaoi.createComputer(new ComputerMapper(cpmui).getCp());
		
	}       

	public void delete(long id) {
		
		cpdaoi.delete(id);
	}

	public void update(ComputerModelUI cpmui) {

		cpdaoi.update(new ComputerMapper(cpmui).getCp());
	}

	public ComputerModelUI find(long id) {
		
		return new ComputerMapper(cpdaoi.find(id)).getCpmui();
	}

	public int pageNumber(){
		return cpdaoi.pageNumber();
	}

	public List<ComputerModelUI> Page() {
		List<Computer> lcp = cpdaoi.page();
		List<ComputerModelUI> lcpmui = new ArrayList<ComputerModelUI>();
		for(int i=0;i<lcp.size();i++){
			lcpmui.add(new ComputerMapper(lcp.get(i)).getCpmui());
		}
		return lcpmui;
	}
	

}