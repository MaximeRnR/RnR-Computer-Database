package com.excilys.mapper.java;

import com.excilys.model.java.Company;
import com.excilys.ui.java.CompanyModelUI;
import com.excilys.util.java.ComputerDBException;

public class CompanyMapper {
	private Company cy;
	private CompanyModelUI cymui;
	
	public CompanyMapper(Company cy) throws ComputerDBException{
		
		this.cy = cy;
		this.cymui = new CompanyModelUI(this.cy.getId(), this.cy.getName());
		
	}
	
	public CompanyMapper(CompanyModelUI cymui) throws ComputerDBException{
		
		this.cymui = cymui;
		this.cy = new Company(this.cymui.getId(), this.cymui.getName());
		
	}

	public Company getCy() {
		return cy;
	}

	public void setCy(Company cy) {
		this.cy = cy;
	}

	public CompanyModelUI getCymui() {
		return cymui;
	}

	public void setCymui(CompanyModelUI cymui) {
		this.cymui = cymui;
	}
	
	
}
