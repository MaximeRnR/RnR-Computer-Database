package com.excilys.persistence.java;

import com.excilys.model.java.Computer;

public interface ComputerDAOInterface {
	public abstract long createComputer(Computer cp);
	public abstract Computer find(int id);
	public abstract boolean delete(int id);
	public abstract boolean update(Computer cp);
	
}
