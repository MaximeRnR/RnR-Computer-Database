package com.excilys.formation.service;

import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.mapper.ComputerMapper;
import com.excilys.formation.model.Computer;
import com.excilys.formation.persistence.ComputerDAO;
import com.excilys.formation.persistence.ComputerDAOInterface;
import com.excilys.formation.ui.Page;

public class ComputerService {
    private ComputerDAOInterface cpdaoi = ComputerDAO.COMPUTERDAO;

    /**
     */
    public ComputerService() {

    }

    /**
     * @param cpdto cpdto
     * @return long generatedKey
     */
    public long createComputer(ComputerDTO cpdto) {

        return cpdaoi.createComputer(new ComputerMapper(cpdto).getCp());

    }

    /**
     * @param id id
     */
    public void delete(long id) {

        cpdaoi.delete(id);
    }

    /**
     * @param cpdto cpdto
     */
    public void update(ComputerDTO cpdto) {

        cpdaoi.update(new ComputerMapper(cpdto).getCp());
    }

    /**
     * @param id id
     * @return cpdto cpdto
     */
    public ComputerDTO find(long id) {

        return new ComputerMapper(cpdaoi.find(id)).getCpdto();
    }

    /**
     * @return int pageNumber
     */
    public int pageNumber() {
        return cpdaoi.count() / Page.mAXNUMBEROFOBJECTS;
    }

    /**
     * @return int nbComputer
     */
    public int count() {
        return cpdaoi.count();
    }

    /**
     * @return List<ComputerDTO> lcpdto
     */
    public List<ComputerDTO> page() {
        List<Computer> lcp = cpdaoi.page();
        List<ComputerDTO> lcpdto = new ArrayList<ComputerDTO>();
        for (int i = 0; i < lcp.size(); i++) {
            lcpdto.add(new ComputerMapper(lcp.get(i)).getCpdto());
        }
        return lcpdto;
    }

}