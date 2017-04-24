package com.excilys.formation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.mapper.ComputerMapperService;
import com.excilys.formation.model.Computer;
import com.excilys.formation.persistence.ComputerDaoJdbc;
import com.excilys.formation.ui.Page;

@Service
public class ComputerService {

    @Autowired
    private ComputerDaoJdbc computerDaoJdbc;

    public void setComputerDaoJdbc(ComputerDaoJdbc computerDaoJdbc) {
        this.computerDaoJdbc = computerDaoJdbc;
    }
    /**
     */
    ComputerService() {

    }

    /**
     * @param computerDto computerDto
     * @return long generatedKey
     */
    public long create(ComputerDTO computerDto) {
        return computerDaoJdbc.createComputer(ComputerMapperService.INSTANCE.toComputer(computerDto));

    }

    /**
     * @param ids ids
     */
    public void delete(List<Long> ids) {
        computerDaoJdbc.delete(ids);
    }

    /**
     * @param computerDto computerDto
     */
    public void update(ComputerDTO computerDto) {

        computerDaoJdbc.update(ComputerMapperService.INSTANCE.toComputer(computerDto));
    }

    /**
     * @param id id
     * @return computerDto computerDto
     */
    public ComputerDTO findById(long id) {

        return ComputerMapperService.INSTANCE.toComputerDto(computerDaoJdbc.findById(id));
    }

    /**
     * @return int pageNumber
     * @param page page
     */
    public int getNumberOfPageOfAllComputers(Page page) {

        int count = computerDaoJdbc.getCountOfAllComputers();
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
        int count = computerDaoJdbc.getCountOfComputersByName(search);
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
        int count = computerDaoJdbc.getCountOfComputersByCompanyName(search);
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
        return computerDaoJdbc.getCountOfAllComputers();
    }


    /**
     * @param search String
     * @return int nbComputer
     */
    public int getCountOfComputersByName(String search) {
        return computerDaoJdbc.getCountOfComputersByName(search);
    }

    /**
     * @param search String
     * @return int nbComputer
     */
    public int getCountOfComputersByCompanyName(String search) {
        return computerDaoJdbc.getCountOfComputersByCompanyName(search);
    }

    /**
     * @return List<ComputerDTO> lcpdto
     * @param page page
     */
    public List<ComputerDTO> getPageOfComputers(Page page) {
        List<Computer> computers = computerDaoJdbc.getPageOfComputers(page);
        List<ComputerDTO> computersDto = new ArrayList<>();
        System.out.println();
        for (int i = 0; i < computers.size(); i++) {
            computersDto.add(ComputerMapperService.INSTANCE.toComputerDto(computers.get(i)));
        }
        return computersDto;
    }
    /**
     * @param search String
     * @param page pagem.excilys.formation.util.CountTotal.COUNTTOTAL.<init>(CountTotal.COUNTTOTAL.java:1
     * @return List<ComputerDTO>
     */
    public List<ComputerDTO> getPageOfComputersByName(String search, Page page) {
        List<Computer> computers = computerDaoJdbc.getPageOfComputersByName(search, page);
        List<ComputerDTO> computersDto = new ArrayList<>();
        for (int i = 0; i < computers.size(); i++) {
            computersDto.add(ComputerMapperService.INSTANCE.toComputerDto(computers.get(i)));
        }

        return computersDto;
    }

    /**
     * @param search String
     * @param page page
     * @return List<ComputerDTO>
     */
    public List<ComputerDTO> getPageOfComputersByCompanyName(String search, Page page) {
        List<Computer> computers = computerDaoJdbc.getPageOfComputersByCompanyName(search, page);
        List<ComputerDTO> computersDto = new ArrayList<>();
        for (int i = 0; i < computers.size(); i++) {
            computersDto.add(ComputerMapperService.INSTANCE.toComputerDto(computers.get(i)));
        }
        return computersDto;
    }

}