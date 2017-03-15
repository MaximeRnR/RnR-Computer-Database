package com.excilys.service.main.java;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.excilys.mapper.java.ComputerMapper;
import com.excilys.persistence.java.ComputerDAO;
import com.excilys.persistence.java.ComputerDAOInterface;
import com.excilys.ui.java.ComputerModelUI;

public class ComputerService {
	static Logger logger = LogManager.getLogger();
	private ComputerDAOInterface cpdaoi = ComputerDAO.COMPUTERDAO;
	
	
	public ComputerService() {

	}

	public long createComputer(ComputerModelUI cpmui) {
		
		return cpdaoi.createComputer(new ComputerMapper(cpmui).getCp());
		
	}       

	public boolean delete(int id) {
		
		return cpdaoi.delete(id);
	}

	public boolean update(ComputerModelUI cpmui) {

		return cpdaoi.update(new ComputerMapper(cpmui).getCp());
	}

	public ComputerModelUI find(int id) {
		
		return new ComputerMapper(cpdaoi.find(id)).getCpmui();
	}

	

}