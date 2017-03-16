package com.excilys.test.java;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.excilys.model.java.Computer;
import com.excilys.persistence.java.ComputerDAO;
import com.excilys.ui.java.ComputerUI;
import com.excilys.ui.java.Page;


public class PageTest {
	private Page<Computer> p;
	private ComputerDAO cpDAO;
	private Computer cp;
	private ComputerUI cpui;

	@Before
	public void beforeEachTest(){

		cpDAO = new ComputerDAO();
		cpui = new ComputerUI();
		cp = new Computer("page_test",null,null,44);
		p= new Page<Computer>(cpDAO.findAll());
	}

	@After
	public void afterEachTest(){

		p = null;
		cp = null;
		cpDAO= null;
		cpui= null;
	}
	
	
	@Test
	public void UpdatePageTest(){
		
		
		
		
	}

}
