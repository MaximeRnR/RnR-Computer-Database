package com.excilys.formation.persistence;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.excilys.formation.model.Company;
import com.excilys.formation.model.Computer;

public class ComputerDAOTest {
    private Computer cp;
    private ComputerDAO cpDAO;

    /**
     */
    @Before
    public void beforeEachTest() {

        cp = new Computer.Builder().build();
        cpDAO = ComputerDAO.COMPUTERDAO;

    }

    /**
     */
    @After
    public void afterEachTest() {

        cp = null;
        cpDAO = null;

    }

    /**
     */
    @Test
    public void findTest() {
        cp = new Computer.Builder()
                .id(574)
                .name("iPhone 4S")
                .di(LocalDate.of(2011, 10, 14))
                .dd(null)
                .cy(new Company(1))
                .build();
        assertEquals(cp.getId(), cpDAO.find(574).getId());
        assertEquals(cp.getName(), cpDAO.find(574).getName());
        assertEquals(cp.getdDiscontinued(), cpDAO.find(574).getdDiscontinued());
        assertEquals(cp.getCy().getId(), cpDAO.find(574).getCy().getId());

    }

    /**
     */
    @Test
    public void deleteTest() {
        cp.setName("Test_delete");
        cp.setCy(new Company(1));
        cp.setId(cpDAO.createComputer(cp));
        cpDAO.delete("" + cp.getId());
        assertTrue(cpDAO.find(cp.getId()) == null);

        cp.setId(cpDAO.createComputer(cp));

        String ids = "";
        long id1 = cp.getId();
        ids = ids + cp.getId();

        cp.setName("Test_delete2");
        cp.setCy(new Company(1));
        cp.setId(cpDAO.createComputer(cp));
        long id2 = cp.getId();
        ids = ids + "," + cp.getId();
        cpDAO.delete(ids);
        assertTrue(cpDAO.find(id1) == null);
        assertTrue(cpDAO.find(id2) == null);
    }

    /**
     */
    @Test
    public void updateTest() {
        cp.setName("Test");
        cp.setdIntroduced(LocalDate.now());
        cp.setdDiscontinued(null);
        cp.setCy(new Company(1));
        long generateKey = cpDAO.createComputer(cp);
        cp.setId(generateKey);
        cp.setName("Test_modif");
        cpDAO.update(cp);
        cp = cpDAO.find(generateKey);
        assertEquals("Test_modif", cp.getName());
        cpDAO.delete("" + generateKey);
        cp.setId(810);
        cpDAO.update(cp);
    }

    /**
     */
    @Test
    public void createComputerTest() {
        cp.setName("Test");
        cp.setdIntroduced(LocalDate.now());
        cp.setdDiscontinued(LocalDate.now());
        cp.setCy(new Company(1));
        long generateKey = cpDAO.createComputer(cp);
        assertEquals(generateKey, cpDAO.find(generateKey).getId());
        cpDAO.delete("" + generateKey);

    }

}
