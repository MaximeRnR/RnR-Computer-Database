package com.excilys.test.java;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.excilys.model.java.Computer;
import com.excilys.persistence.ComputerDAO;

public class ComputerDAOTest {
	private Computer cp;
	private ComputerDAO cpDAO;
	
	
	@Before
	public void beforeEachTest(){
		
		cp = new Computer();
		cpDAO = new ComputerDAO();
		
		
	}
	
	@After
	public void afterEachTest(){
		
		cp = null;
		cpDAO = null;
		
	}
	
	@Test
	public void findTest(){
		cp.setId(574);
		cp.setName("iPhone 4S");
		cp.setdIntroduced(LocalDate.of(2011, 10, 14));
		cp.setdDiscontinued(null);
		cp.setManufacturer("Apple Inc.");
		System.out.println(cp.toString());
		assertEquals(cp, cpDAO.find(574));
		
	}

}
