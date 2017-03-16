package com.excilys.formation.service;


import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.excilys.formation.mapper.ComputerMapper;
import com.excilys.formation.model.Computer;
import com.excilys.formation.persistence.ComputerDAO;
import com.excilys.formation.persistence.ComputerDAOInterface;
import com.excilys.formation.ui.ComputerModelUI;


public class ComputerService {
	static Logger logger = LogManager.getLogger();
	private ComputerDAOInterface cpdaoi = ComputerDAO.COMPUTERDAO;
	
	
	public ComputerService() {

	}

	public long createComputer(ComputerModelUI cpmui) {
		
		return cpdaoi.createComputer(new ComputerMapper(cpmui).getCp());
		
	}       

	public boolean delete(long id) {
		
		return cpdaoi.delete(id);
	}

	public boolean update(ComputerModelUI cpmui) {

		return cpdaoi.update(new ComputerMapper(cpmui).getCp());
	}

	public ComputerModelUI find(long id) {
		
		return new ComputerMapper(cpdaoi.find(id)).getCpmui();
	}

	public List<ComputerModelUI>  page(int index){
		List<Computer> lcp = cpdaoi.page(index);
		List<ComputerModelUI> lcpui = new ArrayList<ComputerModelUI>();
		for(int i=0;i<lcp.size();i++){
			lcpui.add(new ComputerMapper(lcp.get(i)).getCpmui());
		}
		return lcpui;
	}
	

}