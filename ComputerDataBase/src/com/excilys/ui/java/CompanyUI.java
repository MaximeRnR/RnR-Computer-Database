package com.excilys.ui.java;

import java.util.List;


import com.excilys.service.main.java.CompanyService;
//CAlled by App
public class CompanyUI {
	private CompanyModelUI cy;
	private CompanyService cyS;

	public CompanyModelUI FindAssociatedCompany(int id){
		cyS = new CompanyService();
		cy = cyS.find(id);
		return cy;	      

	}

	public List<CompanyModelUI> FindAllCompany(){
		cyS = new CompanyService();
		return cyS.findAll();


	}
}
