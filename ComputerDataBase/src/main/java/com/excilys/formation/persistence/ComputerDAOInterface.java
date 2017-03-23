package com.excilys.formation.persistence;

import java.util.List;

import com.excilys.formation.model.Computer;

public interface ComputerDAOInterface {
    /**
     * @param computer Computer
     * @return long id
     */
    long createComputer(Computer computer);

    /**
     * @param id id
     * @return Computer
     */
    Computer find(long id);

    /**
     * @param id Id
     */
    void delete(long id);

    /**
     * @param computer Computer
     */
    void update(Computer computer);

    /**
     * @return nbComputer
     */
    int count();

    /**
     * @return List<Computer>
     */
    List<Computer> page();

}
