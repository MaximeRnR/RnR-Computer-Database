package com.excilys.formation.service;

import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.dto.CompanyDTO;
import com.excilys.formation.mapper.CompanyMapperService;
import com.excilys.formation.model.Company;
import com.excilys.formation.persistence.CompanyDAO;
import com.excilys.formation.persistence.CompanyDAOInterface;
import com.excilys.formation.util.ComputerDBException;



public enum CompanyService {
    COMPANYSERVICE;
    private CompanyDAOInterface cydaoi = CompanyDAO.COMPANYDAO;

    /**
     * @throws ComputerDBException cdbex
     */
    CompanyService() throws ComputerDBException {

    }

    /**
     * @param id id
     * @return CompanyDTO cydto
     */
    public CompanyDTO find(long id) {

        return new CompanyMapperService(cydaoi.find(id)).getCydto();
    }

    /**
     * @return List<CompanyDTO> lcy
     */
    public List<CompanyDTO> findAll() {
        List<Company> lcp = cydaoi.findAll();
        List<CompanyDTO> lcy = new ArrayList<CompanyDTO>();
        for (int i = 0; i < lcp.size(); i++) {
            lcy.add(new CompanyMapperService(lcp.get(i)).getCydto());
        }
        return lcy;
    }

}
