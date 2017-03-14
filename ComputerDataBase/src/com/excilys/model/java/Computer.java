package com.excilys.model.java;

import java.time.LocalDate;

public class Computer {
	private int id;
	private String name;
	private LocalDate dIntroduced;
	private LocalDate dDiscontinued;
	private int manufacturer;


	public Computer(){};

	public Computer(int id, String name, LocalDate dIntroduced, LocalDate dDiscontinued, int manufacturer) {
		this.id = id;
		this.name = name;
		this.dIntroduced = dIntroduced;
		this.dDiscontinued = dDiscontinued;
		this.manufacturer = manufacturer;
	}
	
	public Computer(String name, LocalDate dIntroduced, LocalDate dDiscontinued, int manufacturer) {
		this.name = name;
		this.dIntroduced = dIntroduced;
		this.dDiscontinued = dDiscontinued;
		this.manufacturer = manufacturer;
	}


	@Override
	public String toString() {
		return "Computer [id=" + id + ", name=" + name + ", dIntroduced=" + dIntroduced + ", dDiscontinued="
				+ dDiscontinued + ", manufacturer=" + manufacturer + "]";
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getdIntroduced() {
		return dIntroduced;
	}
	public void setdIntroduced(LocalDate dIntroduced) {
		this.dIntroduced = dIntroduced;
	}
	public LocalDate getdDiscontinued() {
		return dDiscontinued;
	}
	public void setdDiscontinued(LocalDate dDiscontinued) {
		this.dDiscontinued = dDiscontinued;
	}
	public int getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(int manufacturer) {
		this.manufacturer = manufacturer;
	}


}
