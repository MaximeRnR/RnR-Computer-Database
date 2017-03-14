package com.excilys.ui.java;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.time.format.*;

import com.excilys.model.java.Computer;
import com.excilys.model.java.Page;
import com.excilys.persistence.java.ComputerDAO;

public class ComputerUI {
	private Computer cp;
	private ComputerDAO cpDAO;

	public Computer getCp() {
		return cp;
	}

	public void setCp(Computer cp) {
		this.cp = cp;
	}

	public void AddComputer() throws Exception{

		cp = new Computer();
		cpDAO = new ComputerDAO();
		System.out.println("Add a computer");
		System.out.print("Enter a name : ");
		System.out.println();
		@SuppressWarnings("resource")
		Scanner scan= new Scanner(System.in);
		String name = scan.next();
		if( name == null ) {
			throw new IllegalArgumentException();
		}
		cp.setName(name);
		cp.setdIntroduced(LocalDate.now());
		cp.setManufacturer(44);
		cpDAO.createComputer(cp);
	}

	public void RemoveComputer() throws Exception{

		cp = new Computer();
		cpDAO = new ComputerDAO();
		System.out.println("Remove a computer");
		System.out.print("Enter a id : ");
		System.out.println();
		@SuppressWarnings("resource")
		Scanner scan= new Scanner(System.in);
		int id = scan.nextInt();
		cp.setId(id);
		if(cpDAO.delete(cp))
			System.out.println("Deleted");
		else
			System.out.print("Something went wrong");

	}

	@SuppressWarnings("resource")
	public void UpdateComputer() throws Exception{

		cpDAO = new ComputerDAO();
		System.out.println("Update a computer");
		System.out.print("Enter a id : ");
		System.out.println();
		Scanner scan= new Scanner(System.in);
		int id = scan.nextInt();
		cp = cpDAO.find(id);
		System.out.println("What do you want to update");
		System.out.println("\t 1 - Name");
		System.out.println("\t 2 - Discontinued");
		int choice = scan.nextInt();
		switch (choice){
		case 1:
			System.out.print(cp.getName()+" to : ");
			String name=scan.next();
			if( name == null ) {
				throw new IllegalArgumentException();
			}
			cp.setName(name);
			cpDAO.update(cp);
			break;
		case 2:
			System.out.print(cp.getdDiscontinued()+" to (yyyy-MM-dd) : ");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate dD = LocalDate.parse(scan.next(), formatter);
			cp.setdDiscontinued(dD);
			cpDAO.update(cp);
			break;

		}
	}



	public List<Computer> FindAllComputer(){

		ComputerDAO cpDAO = new ComputerDAO();
		return cpDAO.findAll();
		

	}


	public Computer FindComputer(){

		ComputerDAO cpDAO = new ComputerDAO();
		System.out.println("Find a computer");
		System.out.print("Enter a id : ");
		@SuppressWarnings("resource")
		Scanner scan= new Scanner(System.in);
		int id = scan.nextInt();
		System.out.println();		
		cp = cpDAO.find(id);
		return cp;	      
		
	}
	
	public List<Computer> PageComputer(){

		ComputerDAO cpDAO = new ComputerDAO();
		Page<Computer> p= new Page<Computer>(cpDAO.findAll());
		System.out.println("Their are "+ (p.getPages().size()-1)+" pages");
		System.out.print("Enter a page : ");
		@SuppressWarnings("resource")
		Scanner scan= new Scanner(System.in);
		int id = scan.nextInt();
		System.out.println();
		return p.getPages().get(id);
		
		
	}
	
}



