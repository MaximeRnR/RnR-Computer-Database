package com.excilys.test.java;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.excilys.model.java.Company;
//import com.excilys.persistence.java.CompanyDAO;
import com.excilys.ui.java.CompanyUI;

public class CompanyUITest {
	private CompanyUI cpui;
	//private CompanyDAO cpDAO;

	@Before
	public void beforeEachTest(){

		cpui = new CompanyUI();
		//cpDAO = new CompanyDAO();
	}

	@After
	public void afterEachTest(){

		cpui = null;
		//cpDAO= null;
	}



	@Test
	public void FindAllCompanyTest(){
		try{
			List<Company> lcp = cpui.FindAllCompany();
			
			for(int i=0; i< lcp.size();i++)
				System.out.println(lcp.get(i).toString());
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}

		@Test
		public void FindCompanyTest(){

			try {
				System.out.println(cpui.FindAssociatedCompany(1).toString());


			} catch (Exception e) {
				e.printStackTrace();
			}


		}

	}
