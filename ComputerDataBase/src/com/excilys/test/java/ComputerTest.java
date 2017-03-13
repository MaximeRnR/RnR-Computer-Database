package com.excilys.test.java;

import org.junit.*;

import com.excilys.model.java.Computer;



public class ComputerTest {
	private Computer cp;
	
	
	@Before
	public void beforeEachTest(){
		
		cp = new Computer();
		
		
	}
	
	@After
	public void afterEachTest(){
		
		cp = null;
		
		
	}
	
	
	
}
