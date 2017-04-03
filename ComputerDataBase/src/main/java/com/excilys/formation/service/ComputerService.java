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
    private ComputerDAOInterface cpdaoi = ComputerDAO.COMPUTERDAO;

    /**
     */
    ComputerService() {

    }

    /**
     * @param cpdto cpdto
     * @return long generatedKey
     */
    public long createComputer(ComputerDTO cpdto) {

        return cpdaoi.createComputer(new ComputerMapperService(cpdto).getCp());

    }

    /**
     * @param ids ids
     */
    public void delete(String ids) {

        cpdaoi.delete(ids);
    }

    /**
     * @param cpdto cpdto
     */
    public void update(ComputerDTO cpdto) {

        cpdaoi.update(new ComputerMapperService(cpdto).getCp());
    }

    /**
     * @param id id
     * @return cpdto cpdto
     */
    public ComputerDTO find(long id) {

        return new ComputerMapperService(cpdaoi.find(id)).getCpdto();
    }

    /**
     * @return int pageNumber
     */
    public int pageNumber() {
        return cpdaoi.count() / Page.mAXNUMBEROFOBJECTS;
    }

    /**
     * @param search String
     * @param by String
     * @return int pageNumber
     */
    public int pageNumberSearch(String search, String by) {
        return cpdaoi.countLike(search, by) / Page.mAXNUMBEROFOBJECTS;
    }

    /**
     * @return int nbComputer
     */
    public int count() {
        return cpdaoi.count();
    }

    /**
     * @param search String
     * @param by String
     * @return int nbComputer
     */
    public int countLike(String search, String by) {
        return cpdaoi.countLike(search, by);
    }

    /**
     * @return List<ComputerDTO> lcpdto
     */
    public List<ComputerDTO> page() {
        List<Computer> lcp = cpdaoi.page();
        List<ComputerDTO> lcpdto = new ArrayList<ComputerDTO>();
        for (int i = 0; i < lcp.size(); i++) {
            lcpdto.add(new ComputerMapperService(lcp.get(i)).getCpdto());
        }
        return lcpdto;
    }
    /**
     * @param search String
     * @param by String
     * @return List<ComputerDTO>
     */
    public List<ComputerDTO> like(String search, String by) {
        List<Computer> lcp = cpdaoi.like(search, by);
        List<ComputerDTO> lcpdto = new ArrayList<ComputerDTO>();
        for (int i = 0; i < lcp.size(); i++) {
            lcpdto.add(new ComputerMapperService(lcp.get(i)).getCpdto());
        }
        return lcpdto;
    }

}