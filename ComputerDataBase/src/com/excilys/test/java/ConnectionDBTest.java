package com.excilys.test.java;

import org.junit.After;
import org.junit.Before;

import com.excilys.connection.java.ConnectionDB;
import com.excilys.model.java.Computer;

public class ConnectionDBTest {
	public ConnectionDB connDB;
	
	@Before
	public void beforeEachTest(){
		
		connDB = ConnectionDB.getInstance();
		
		
	}
	
	@After
	public void afterEachTest(){
		
		connDB = null;
		
	}
	
	

}
