package com.excilys.formation.service;

import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.mapper.ComputerMapperService;
import com.excilys.formation.model.Computer;
import com.excilys.formation.persistence.ComputerDAO;
import com.excilys.formation.persistence.ComputerDAOInterface;
import com.excilys.formation.ui.Page;

public enum ComputerService {
    COMPUTERSERVICE;
    private ComputerDAOInterface computerDaoI = ComputerDAO.COMPUTERDAO;
    private int nbComputers = 0;

    /**
     */
    ComputerService() {

    }

    /**
     * @param computerDto computerDto
     * @return long generatedKey
     */
    public long create(ComputerDTO computerDto) {
        nbComputers++;
        return computerDaoI.createComputer(new ComputerMapperService(computerDto).getComputer());

    }

    /**
     * @param ids ids
     */
    public void delete(List<Long> ids) {
        nbComputers--;
        computerDaoI.delete(ids);
    }

    /**
     * @param computerDto computerDto
     */
    public void update(ComputerDTO computerDto) {

        computerDaoI.update(new ComputerMapperService(computerDto).getComputer());
    }

    /**
     * @param id id
     * @return computerDto computerDto
     */
    public ComputerDTO findById(long id) {

        return new ComputerMapperService(computerDaoI.findById(id)).getComputerDto();
    }

    /**
     * @return int pageNumber
     * @param page page
     */
    public int getNumberOfPageOfAllComputers(Page page) {
        int count = 0;
        if (nbComputers != 0) {
            count = nbComputers;
        } else {
            count = computerDaoI.getCountOfAllComputers();
        }
        if (count %  page.maxNumberOfObject == 0) {
            return (count / page.maxNumberOfObject) - 1;
        } else {
            return  count / page.maxNumberOfObject;
        }
    }

    /**
     * @param search String
     * @param page page
     * @return int pageNumber
     */
    public int getNumberOfPageOfComputersByName(String search, Page page) {
        int count = computerDaoI.getCountOfComputersByName(search);
        if (count %  page.maxNumberOfObject == 0) {
            return count / page.maxNumberOfObject - 1;
        } else {
            return  count / page.maxNumberOfObject;
        }
    }

    /**
     * @param search String
     * @param page page
     * @return int pageNumber
     */
    public int getNumberOfPageOfComputersByCompanyName(String search, Page page) {
        int count = computerDaoI.getCountOfComputersByCompanyName(search);
        if (count % page.maxNumberOfObject == 0) {
            return count / page.maxNumberOfObject - 1;
        } else {
            return  count / page.maxNumberOfObject;
        }

    }

    /**
     * @return int nbComputer
     */
    public int getCountOfAllComputers() {
        if (nbComputers != 0) {
            return nbComputers;
        } else {
        nbComputers = computerDaoI.getCountOfAllComputers();
        return  nbComputers;
        }
    }

    /**
     * @param search String
     * @return int nbComputer
     */
    public int getCountOfComputersByName(String search) {
        return computerDaoI.getCountOfComputersByName(search);
    }

    /**
     * @param search String
     * @return int nbComputer
     */
    public int getCountOfComputersByCompanyName(String search) {
        return computerDaoI.getCountOfComputersByCompanyName(search);
    }

    /**
     * @return List<ComputerDTO> lcpdto
     * @param page page
     */
    public List<ComputerDTO> getPageOfComputers(Page page) {
        List<Computer> computers = computerDaoI.getPageOfComputers(page);
        List<ComputerDTO> computersDto = new ArrayList<>();
        System.out.println();
        for (int i = 0; i < computers.size(); i++) {
            computersDto.add(new ComputerMapperService(computers.get(i)).getComputerDto());
        }
        return computersDto;
    }
    /**
     * @param search String
     * @param page page
     * @return List<ComputerDTO>
     */
    public List<ComputerDTO> getPageOfComputersByName(String search, Page page) {
        List<Computer> computers = computerDaoI.getPageOfComputersByName(search, page);
        List<ComputerDTO> computersDto = new ArrayList<>();
        for (int i = 0; i < computers.size(); i++) {
            computersDto.add(new ComputerMapperService(computers.get(i)).getComputerDto());
        }

        return computersDto;
    }

    /**
     * @param search String
     * @param page page
     * @return List<ComputerDTO>
     */
    public List<ComputerDTO> getPageOfComputersByCompanyName(String search, Page page) {
        List<Computer> computers = computerDaoI.getPageOfComputersByCompanyName(search, page);
        List<ComputerDTO> computersDto = new ArrayList<>();
        for (int i = 0; i < computers.size(); i++) {
            computersDto.add(new ComputerMapperService(computers.get(i)).getComputerDto());
        }
        return computersDto;
    }

}