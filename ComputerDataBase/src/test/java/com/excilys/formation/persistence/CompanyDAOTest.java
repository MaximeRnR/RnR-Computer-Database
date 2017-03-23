package com.excilys.formation.persistence;


import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.excilys.formation.entity.CompanyEntity;

public class CompanyDAOTest {
    private CompanyEntity cp;
    private CompanyDAOInterface cydaoi;

    /**
     */
    @Before
    public void beforeEachTest() {

        cp = new CompanyEntity();
        cydaoi = CompanyDAO.COMPANYDAO;

    }

    /**
     */
    @After
    public void afterEachTest() {

        cp = null;
        cydaoi = null;

    }

    /**
     */
    @Test
    public void findTest() {
        cp.setId(1);
        cp.setName("Apple Inc.");
        assertEquals(cp.getId(), cydaoi.find(1).getId());
        assertEquals(cp.getName(), cydaoi.find(1).getName());

    }

    /**
     */
    @Test
    public void findAllTest() {

        assertEquals(41, cydaoi.findAll().size());

    }

}
