package com.excilys.main.java;

import java.util.Date;

public class Computer {
	private String name;
	private Date dIntroduced;
	private Date dDiscontinued;
	private String manufacturer;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getdIntroduced() {
		return dIntroduced;
	}
	public void setdIntroduced(Date dIntroduced) {
		this.dIntroduced = dIntroduced;
	}
	public Date getdDiscontinued() {
		return dDiscontinued;
	}
	public void setdDiscontinued(Date dDiscontinued) {
		this.dDiscontinued = dDiscontinued;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	

}
