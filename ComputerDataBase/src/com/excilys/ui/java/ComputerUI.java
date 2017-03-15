package com.excilys.ui.java;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.time.format.*;

import com.excilys.service.main.java.ComputerService;

//Called in App
public class ComputerUI {
	private ComputerService cpS;
	private ComputerModelUI cp;
	private CompanyModelUI cy;


	public void AddComputer() throws Exception{

		cpS = new ComputerService();
		System.out.println("Add a computer");
		System.out.print("Enter a name : ");
		System.out.println();
		Scanner scan= new Scanner(System.in);
		String name = scan.next();
		if( name == null ) {
			throw new IllegalArgumentException();
		}
		cy = new CompanyModelUI(44);
		cp = new ComputerModelUI.Builder().name(name).di(LocalDate.now()).di(null).cymui(cy).build();
		cpS.createComputer(cp);
	}

	public void RemoveComputer() throws Exception{

		cpS = new ComputerService();
		System.out.println("Remove a computer");
		System.out.print("Enter a id : ");
		System.out.println();
		Scanner scan= new Scanner(System.in);
		int id=1;
		try {
			id = scan.nextInt();
		} catch (Exception e) {
			System.out.println("Not a number");
			App.menu();
		}
		if(cpS.delete(id))
			System.out.println("Deleted");
		else
			System.out.println("Something went wrong");

	}

	public void UpdateComputer() throws Exception{

		cpS = new ComputerService();
		System.out.println("Update a computer");
		System.out.print("Enter a id : ");
		System.out.println();
		Scanner scan= new Scanner(System.in);
		int id=1;
		try {
			id = scan.nextInt();
		} catch (Exception e) {
			System.out.println("Not a number");
			App.menu();
		}
		cp = cpS.find(id);
		System.out.println("What do you want to update");
		System.out.println("\t 1 - Name");
		System.out.println("\t 2 - Discontinued");
		int choice=1;
		try {
			choice = scan.nextInt();
		} catch (Exception e) {
			System.out.println("Not a number");
			App.menu();
		}
		switch(choice){
		case 1:
			System.out.print(cp.getName()+" to : ");
			String name=scan.next();
			if( name == null ) {
				throw new IllegalArgumentException();
			}
			cp.setName(name);
			cpS.update(cp);
			break;
		case 2:
			System.out.print(cp.getdDiscontinued()+" to (yyyy-MM-dd) : ");
			LocalDate dD = null;
			try{
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				dD = LocalDate.parse(scan.next(), formatter);
			}catch(Exception e){
				System.out.println("Not in the right Format");
			}
			cp.setdDiscontinued(dD);
			cpS.update(cp);
			break;

		}
	}




	public ComputerModelUI FindComputer(){

		cpS = new ComputerService();
		System.out.println("Find a computer");
		System.out.print("Enter a id : ");
		Scanner scan= new Scanner(System.in);
		int id=1;
		try {
			id = scan.nextInt();
		} catch (Exception e) {
			System.out.println("Not a number");
			App.menu();
		}
		System.out.println();		
		cp = cpS.find(id);
		return cp;	      

	}
	/*  A MODIFIER 
	public List<Computer> PageComputer(){

	
		ComputerService cpS = new ComputerService();
		Page<Computer> p= new Page<Computer>(cpS.findAll());
		System.out.println("Their are "+ (p.getPages().size()-1)+" pages");
		System.out.print("Enter a page : ");
		Scanner scan= new Scanner(System.in);
		int id=1;
		try {
			id = scan.nextInt();
		} catch (Exception e) {
			System.out.println("Not a page number");
			App.menu();
		}
		if(id> (p.getPages().size()-1) || id<1){

			System.out.println("Not a page number");
			App.menu();

		}
		System.out.println();
		return p.getPages().get(id);

	
	}
	*/

}



