package com.excilys.ui.java;

import java.time.LocalDate;
import java.util.Scanner;

import com.excilys.model.java.Computer;
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
		Scanner scan= new Scanner(System.in);
		String name = scan.next();
		scan.close();
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
		Scanner scan= new Scanner(System.in);
		int id = scan.nextInt();
		scan.close();
		cp.setId(id);
		if(cpDAO.delete(cp))
			System.out.println("Deleted");
		else
			System.out.print("Something went wrong");
			
	}
	
	
	
	
	

}
