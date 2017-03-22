package com.excilys.formation.persistence;

import java.util.List;

import com.excilys.formation.model.Computer;

public interface ComputerDAOInterface {
    /**
     * @param cp Computer
     * @return long id
     */
    long createComputer(Computer cp);

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
     * @param cp Computer
     */
    void update(Computer cp);

    /**
     * @return nbComputer
     */
    int count();

    /**
     * @return List<Computer>
     */
    List<Computer> page();

}
