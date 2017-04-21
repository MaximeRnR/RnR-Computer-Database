package com.excilys.formation.persistence;


import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.formation.model.Company;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class CompanyDAOTest {
    private Company company;

    @Autowired
    private CompanyDao companyDaoI;

    /**
     */
    @Before
    public void beforeEachTest() {

        company = new Company(0);

    }

    /**
     */
    @After
    public void afterEachTest() {

        company = null;

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
