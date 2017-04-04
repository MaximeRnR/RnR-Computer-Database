package com.excilys.formation.persistence;


import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.excilys.formation.model.Company;

public class CompanyDAOTest {
    private Company company;
    private CompanyDAOInterface companyDaoI;

    /**
     */
    @Before
    public void beforeEachTest() {

        company = new Company(0);
        companyDaoI = CompanyDAO.COMPANYDAO;

    }

    /**
     */
    @After
    public void afterEachTest() {

        company = null;
        companyDaoI = null;

    }

    /**
     */
    @Test
    public void findTest() {
        company.setId(1);
        company.setName("Apple Inc.");
        assertEquals(company.getId(), companyDaoI.findById(1).getId());
        assertEquals(company.getName(), companyDaoI.findById(1).getName());

    }

    /**
     */
    @Test
    public void findAllTest() {

        assertEquals(42, companyDaoI.findAll().size());

    }

}
