package com.excilys.formation.service;

import java.util.ArrayList;
import java.util.List;


import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.mapper.ComputerMapperService;
import com.excilys.formation.model.Computer;
import com.excilys.formation.persistence.ComputerDao;
import com.excilys.formation.ui.Page;

public class ComputerService {

    private ComputerDao computerDao;

    public void setComputerDao(ComputerDao computerDao) {
        this.computerDao = computerDao;
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
        return computerDao.createComputer(ComputerMapperService.INSTANCE.toComputer(computerDto));

    }

    /**
     * @param ids ids
     */
    public void delete(List<Long> ids) {
        computerDao.delete(ids);
    }

    /**
     * @param computerDto computerDto
     */
    public void update(ComputerDTO computerDto) {

        computerDao.update(ComputerMapperService.INSTANCE.toComputer(computerDto));
    }

    /**
     * @param id id
     * @return computerDto computerDto
     */
    public ComputerDTO findById(long id) {

        return ComputerMapperService.INSTANCE.toComputerDto(computerDao.findById(id));
    }

    /**
     * @return int pageNumber
     * @param page page
     */
    public int getNumberOfPageOfAllComputers(Page page) {

        int count = computerDao.getCountOfAllComputers();
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
        int count = computerDao.getCountOfComputersByName(search);
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
        int count = computerDao.getCountOfComputersByCompanyName(search);
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
        return computerDao.getCountOfAllComputers();
    }


    /**
     * @param search String
     * @return int nbComputer
     */
    public int getCountOfComputersByName(String search) {
        return computerDao.getCountOfComputersByName(search);
    }

    /**
     * @param search String
     * @return int nbComputer
     */
    public int getCountOfComputersByCompanyName(String search) {
        return computerDao.getCountOfComputersByCompanyName(search);
    }

    /**
     * @return List<ComputerDTO> lcpdto
     * @param page page
     */
    public List<ComputerDTO> getPageOfComputers(Page page) {
        List<Computer> computers = computerDao.getPageOfComputers(page);
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
        List<Computer> computers = computerDao.getPageOfComputersByName(search, page);
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
        List<Computer> computers = computerDao.getPageOfComputersByCompanyName(search, page);
        List<ComputerDTO> computersDto = new ArrayList<>();
        for (int i = 0; i < computers.size(); i++) {
            computersDto.add(ComputerMapperService.INSTANCE.toComputerDto(computers.get(i)));
        }
        return computersDto;
    }

}