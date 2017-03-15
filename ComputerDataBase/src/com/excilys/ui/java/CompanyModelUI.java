package com.excilys.ui.java;
//Model company
public class CompanyModelUI {
	private int id;
	private String name;

	public CompanyModelUI(int id, String name) {
		this.id = id;
		this.name = name;
	}
	public CompanyModelUI(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + "]";
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


}
