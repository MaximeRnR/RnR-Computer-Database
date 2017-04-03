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
     * @param ids Ids
     */
    void delete(String ids);

    /**
     * @param computer Computer
     */
    void update(Computer computer);

    /**
     * @return nbComputer
     */
    int count();

    /**
     * @param search String
     * @param by String
     * @return nbComputer
     */
    int countLike(String search, String by);

    /**
     * @return List<Computer>
     */
    List<Computer> page();

    /**
     * @param search String
     * @param by String
     * @return List<Computer>
     */
    List<Computer> like(String search, String by);

}
