package com.excilys.test.java;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.excilys.persistence.java.ComputerDAO;
//import com.excilys.persistence.java.ComputerDAO;
import com.excilys.ui.java.ComputerUI;

public class ComputerUITest {
	private ComputerUI cpui;
	private ComputerDAO cpDAO;

	@Before
	public void beforeEachTest(){

		cpui = new ComputerUI();
		cpDAO = new ComputerDAO();
	}

	@After
	public void afterEachTest(){

		cpui = null;
		cpDAO= null;
	}


	@Test
	public void AddComputerTest(){


	}

	@Test
	public void RemoveComputerTest(){
		
	}

	@Test
	public void UpdateComputerTest(){

		
	}

	@Test
	public void FindAllComputerTest(){
		
	}
	
	@Test
	public void FindComputerTest(){
		
	}
	
	@Test
	public void PageComputerTest(){

		

	}
	
}
