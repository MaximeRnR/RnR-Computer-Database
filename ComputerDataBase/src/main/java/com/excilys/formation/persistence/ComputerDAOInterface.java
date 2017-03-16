package com.excilys.formation.persistence;

import java.util.List;

import com.excilys.formation.model.Computer;


public interface ComputerDAOInterface {
	public abstract long createComputer(Computer cp);
	public abstract Computer find(long id);
	public abstract boolean delete(long id);
	public abstract boolean update(Computer cp);
	public abstract List<Computer> page(int index);
	
}
