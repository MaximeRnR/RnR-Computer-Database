package com.excilys.formation.persistence;

import org.junit.After;
import org.junit.Before;


public class ConnectionDBTest {
    public ConnectionDB connDB;

    /**
     */
    @Before
    public void beforeEachTest() {
        connDB = connDB.INSTANCE;

    }

    /**
     */
    @After
    public void afterEachTest() {

        connDB = null;

    }

}
