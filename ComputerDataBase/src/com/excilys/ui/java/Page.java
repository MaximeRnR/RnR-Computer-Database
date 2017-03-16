package com.excilys.ui.java;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.excilys.service.main.java.ComputerService;
import com.excilys.util.java.ComputerDBException;
//Class Pagination 
public class Page {
	private List<ComputerModelUI> pages;
	public static final int MAX_NUMBER_OF_OBJECT = 10;
	private int index=0;
	private ComputerService cpS;

	public Page(){
	}


	public void getCurrent() throws ComputerDBException{
		cpS = new ComputerService();
		System.out.print("Enter a page : ");
		Scanner scan= new Scanner(System.in);
		try {
			this.index = scan.nextInt();
		} catch (ComputerDBException | InputMismatchException e) {
			throw new ComputerDBException("Not a Number", e);
		}
		try{
			pages = cpS.page(this.index);
			for(int i=0; i<pages.size();i++){

				System.out.println(pages.get(i).getId() +" "+ pages.get(i).getName() +" "+ pages.get(i).getdIntroduced() +" "+ pages.get(i).getdDiscontinued() +" "+ pages.get(i).getCymui().getName() );


			}
		}catch(ComputerDBException e ){

		}
	}

	public void next(){

		this.index++;
		try{
			pages = cpS.page(this.index);
			for(int i=0; i<pages.size();i++){

				System.out.println(pages.get(i).getId() +" "+ pages.get(i).getName() +" "+ pages.get(i).getdIntroduced() +" "+ pages.get(i).getdDiscontinued() +" "+ pages.get(i).getCymui().getName() );


			}
		}catch(ComputerDBException e ){

		}

	}

	public void previous(){

		this.index--;
		try{
			pages = cpS.page(this.index);
			for(int i=0; i<pages.size();i++){

				System.out.println(pages.get(i).getId() +" "+ pages.get(i).getName() +" "+ pages.get(i).getdIntroduced() +" "+ pages.get(i).getdDiscontinued() +" "+ pages.get(i).getCymui().getName() );


			}
		}catch(ComputerDBException e ){

		}

	}


	public List<ComputerModelUI> getPages() {
		return pages;
	}






}
