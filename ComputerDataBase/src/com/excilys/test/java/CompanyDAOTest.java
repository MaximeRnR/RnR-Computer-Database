package com.excilys.test.java;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.excilys.model.java.Company;
import com.excilys.persistence.java.CompanyDAO;

public class CompanyDAOTest {
	private Company cp;
	private CompanyDAO cpDAO;


	@Before
	public void beforeEachTest(){

		cp = new Company();
		cpDAO = new CompanyDAO();


	}

	@After
	public void afterEachTest(){

		cp = null;
		cpDAO = null;

	}

	@Test
	public void findTest(){
		cp.setId(1);
		cp.setName("Apple Inc.");
		assertEquals(cp.getId(), cpDAO.find(1).getId());
		assertEquals(cp.getName(), cpDAO.find(1).getName());

	}
	
	
	@Test
	public void updateTest(){
		cp.setName("Test");
		int generateKey = cpDAO.createCompany(cp);
		cp.setId(generateKey);
		cp.setName("Test1");
		cpDAO.update(cp);
		cp = cpDAO.find(generateKey);
		assertEquals("Test1",cp.getName());
	}
	
	
	@Test
	public void deleteTest(){
		cp.setId(48);
		assertTrue( cpDAO.delete(cp));
	}


	@Test
	public void createCompanyTest(){
		cp.setName("Test");
		int generateKey = cpDAO.createCompany(cp);
		assertEquals(generateKey, cpDAO.find(generateKey).getId());

	}

}
