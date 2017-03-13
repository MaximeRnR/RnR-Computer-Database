package com.excilys.test.java;

import org.junit.After;
import org.junit.Before;

import com.excilys.connection.java.ConnectionDB;

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
