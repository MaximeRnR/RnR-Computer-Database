package com.excilys.formation.persistence;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.formation.model.Company;
import com.excilys.formation.model.Computer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class ComputerDAOTest {

    private Computer cp;

    @Autowired
    private ComputerDao cpDAOi;

    /**
     */
    @Before
    public void beforeEachTest() {

        cp = new Computer.Builder().build();

    }

    /**
     */
    @After
    public void afterEachTest() {

        cp = null;

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
        Computer test = cpDAOi.findById(574);
        assertEquals(cp.getId(), test.getId());
        assertEquals(cp.getName(), test.getName());
        assertEquals(cp.getdDiscontinued(), test.getdDiscontinued());
        assertEquals(cp.getCy().getId(), test.getCy().getId());

    }

    /**
     */
    @Test
    public void deleteTest() {
        cp.setName("Test_delete");
        cp.setCy(new Company(1));
        cp.setId(cpDAOi.createComputer(cp));
        List<Long> ids = new ArrayList<>();
        ids.add(0, cp.getId());
        cp.setName("Test_delete2");
        cp.setCy(new Company(1));
        cp.setId(cpDAOi.createComputer(cp));
        ids.add(1, cp.getId());
        assertTrue(cpDAOi.delete(ids));
    }

    /**
     */
    @Test
    public void updateTest() {
        cp.setName("Test");
        cp.setdIntroduced(LocalDate.now());
        cp.setdDiscontinued(null);
        cp.setCy(new Company(1));
        long generateKey = cpDAOi.createComputer(cp);
        cp.setId(generateKey);
        cp.setName("Test_modif");
        cpDAOi.update(cp);
        cp = cpDAOi.findById(generateKey);
        assertEquals("Test_modif", cp.getName());
        List<Long> id = new ArrayList<>();
        id.add(0, generateKey);
        cpDAOi.delete(id);
        cp.setId(810);
        cpDAOi.update(cp);
    }
    /**
     */
    @Test
    public void createComputerTest() {
        cp.setName("Test");
        cp.setdIntroduced(LocalDate.now());
        cp.setdDiscontinued(LocalDate.now());
        cp.setCy(new Company(1));
        long generateKey = cpDAOi.createComputer(cp);
        assertEquals(generateKey, cpDAOi.findById(generateKey).getId());
        List<Long> id = new ArrayList<>();
        id.add(0, generateKey);
        cpDAOi.delete(id);

    }

}
