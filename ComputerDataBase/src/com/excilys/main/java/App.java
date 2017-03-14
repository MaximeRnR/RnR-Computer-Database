package com.excilys.main.java;

import java.util.List;
import java.util.Scanner;

import com.excilys.model.java.Company;
import com.excilys.model.java.Computer;
import com.excilys.ui.java.CompanyUI;
import com.excilys.ui.java.ComputerUI;

public class App {

	public App() {}

	public static void main(String args[]){

		menu();

	}

	public static void menu(){
		CompanyUI cyui = new CompanyUI();
		ComputerUI cpui = new ComputerUI();
		System.out.println("CDB - What do you want to do ?");
		System.out.println("\t 1 - Show all Companies");
		System.out.println("\t 2 - Show all Computers");
		System.out.println("\t 3 - Show details for one computer");
		System.out.println("\t 4 - Create a Computer");
		System.out.println("\t 5 - Update a Computer");
		System.out.println("\t 6 - Delete a Computer");
		System.out.println("\t 7 - Show a Page of 10 computers");
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		int choice = 0;
		try{
				choice = scan.nextInt();
				
		}catch(Exception e){
			System.out.println("Pick a menu number");
			App.menu();
		}

		if(choice > 7 || choice < 1 ){
			System.out.println("Pick a menu number");
			App.menu();
		}
		
		switch (choice){
		case 1:
			List<Company> lcy = cyui.FindAllCompany();
			for(int i=0; i<lcy.size();i++){

				System.out.println(lcy.get(i).toString());

			}
			break;
		case 2:
			List<Computer> lcp = cpui.FindAllComputer();
			for(int i=0; i<lcp.size();i++){

				System.out.println(lcp.get(i).toString());

			}
			break;
		case 3:
			Computer cp = cpui.FindComputer();
			Company cy = cyui.FindAssociatedCompany(cp.getManufacturer());
			System.out.println(cp.getId() +" "+ cp.getName() +" "+ cp.getdIntroduced() +" "+ cp .getdDiscontinued() +" "+ cy.getName() );

			break;
		case 4:
			try {
				cpui.AddComputer();
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case 5:
			try {
				cpui.UpdateComputer();
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case 6:
			try {
				cpui.RemoveComputer();
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case 7:

			List<Computer> lcpP = cpui.PageComputer();
			for(int i=0; i<lcpP.size();i++){

				System.out.println(lcpP.get(i).toString());

			}
			break;
		}
		pressAnyKeyToContinue();

	}


	public static void pressAnyKeyToContinue()
	{ 
		System.out.println("Press Enter to continue...");
		try
		{
			System.in.read();
			menu();
		}  
		catch(Exception e)
		{}  
	}

}
