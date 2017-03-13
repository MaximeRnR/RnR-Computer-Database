package com.excilys.test.java;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.excilys.ui.java.ComputerUI;

public class ComputerUITest {
	private ComputerUI cpui;
	
	@Before
	public void beforeEachTest(){

		cpui = new ComputerUI();

	}

	@After
	public void afterEachTest(){

		cpui = null;
	}
	
	
	@Test
	public void AddComputerTest(){
			
		
	}
	
	@Test
	public void RemoveComputerTest(){
			
		try {
			cpui.RemoveComputer();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
