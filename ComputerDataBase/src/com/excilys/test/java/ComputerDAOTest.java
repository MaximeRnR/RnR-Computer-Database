package com.excilys.test.java;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.excilys.model.java.Computer;
import com.excilys.persistence.java.ComputerDAO;

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
		cp.setManufacturer(1);
		assertEquals(cp.getId(), cpDAO.find(574).getId());
		assertEquals(cp.getName(), cpDAO.find(574).getName());
		assertEquals(cp.getdDiscontinued(), cpDAO.find(574).getdDiscontinued());
		assertEquals(cp.getManufacturer(), cpDAO.find(574).getManufacturer());

	}

	@Test
	public void deleteTest(){
		cp.setId(575);
		assertTrue( cpDAO.delete(cp));
	}
	
	@Test
	public void updateTest(){
		cp.setName("Test");
		cp.setdIntroduced(LocalDate.now());
		cp.setdDiscontinued(LocalDate.now());
		cp.setManufacturer(1);
		int generateKey = cpDAO.createComputer(cp);
		cp.setId(generateKey);
		cp.setName("Test1");
		cpDAO.update(cp);
		cp = cpDAO.find(generateKey);
		assertEquals("Test1",cp.getName());
	}
	
	@Test
	public void createComputerTest(){
		cp.setName("Test");
		cp.setdIntroduced(LocalDate.now());
		cp.setdDiscontinued(LocalDate.now());
		cp.setManufacturer(1);
		int generateKey = cpDAO.createComputer(cp);
		assertEquals(generateKey, cpDAO.find(generateKey).getId());

	}

}
