package com.excilys.formation.persistence;

import java.util.List;

import com.excilys.formation.model.Computer;
import com.excilys.formation.ui.Page;

public interface ComputerDaoJdbc {

    /**
     * @param computer Computer
     * @return long id
     */
    long createComputer(Computer computer);

    /**
     * @param id id
     * @return Computer
     */
    Computer findById(long id);

    /**
     * @param ids Ids
     * @return boolean boolean
     */
    boolean delete(List<Long> ids);

    /**
     * @param computer Computer
     */
    void update(Computer computer);

    /**
     * @return nbComputer
     */
    int getCountOfAllComputers();

    /**
     * @return List<Computer>
     * @param page page
     */
    List<Computer> getPageOfComputers(Page page);

    /**
     * @param search String
     * @return nbComputer
     */
    int getCountOfComputersByName(String search);

    /**
     * @param search String
     * @return nbComputer
     */
    int getCountOfComputersByCompanyName(String search);

    /**
     * @param search String
     * @param page page
     * @return List<Computer>
     */
    List<Computer> getPageOfComputersByName(String search, Page page);

    /**
     * @param search String
     * @param page page
     * @return List<Computer>
     */
    List<Computer> getPageOfComputersByCompanyName(String search, Page page);
}
