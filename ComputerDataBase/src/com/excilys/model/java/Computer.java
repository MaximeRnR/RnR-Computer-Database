package com.excilys.model.java;

import java.time.LocalDate;
//Model Computer
public class Computer {
	private long id;
	private String name;
	private LocalDate dIntroduced;
	private LocalDate dDiscontinued;
	private long manufacturer;


	//Constructor with id
	private Computer(long id, String name, LocalDate dIntroduced, LocalDate dDiscontinued, long manufacturer) {
		this.id = id;
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

	public long getId() {
		return id;
	}
	public void setId(long id) {
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
	public long getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(long manufacturer) {
		this.manufacturer = manufacturer;
	}

	public static class Builder{
		private long id;
		private String name;
		private LocalDate dIntroduced;
		private LocalDate dDiscontinued;
		private long manufacturer;
	
		public Builder(){
			
			
		}
		
		public Builder id(long id){
			this.id = id;
			return this;
		}
		
		public Builder name(String name){
			this.name=name;
			return this;
			
		}
		public Builder di(LocalDate di){
			this.dIntroduced=di;
			return this;
			
		}
		
		public Builder dd(LocalDate dd){
			this.dIntroduced=dd;
			return this;
			
		}
		
		public Builder manufacturer(long manu){
			this.manufacturer=manu;
			return this;
			
		}
		
		public Computer build(){
			return new Computer(this.id,this.name,this.dIntroduced,this.dDiscontinued,this.manufacturer);
			
		}
	}

}
