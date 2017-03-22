package com.excilys.formation.service;

import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.dto.CompanyDTO;
import com.excilys.formation.mapper.CompanyMapper;
import com.excilys.formation.model.Company;
import com.excilys.formation.persistence.CompanyDAO;
import com.excilys.formation.persistence.CompanyDAOInterface;
import com.excilys.formation.util.ComputerDBException;

public class CompanyService {
    private CompanyDAOInterface cydaoi = CompanyDAO.COMPANYDAO;

    /**
     * @throws ComputerDBException cdbex
     */
    public CompanyService() throws ComputerDBException {

    }

    /**
     * @param id id
     * @return CompanyDTO cydto
     */
    public CompanyDTO find(long id) {

        return new CompanyMapper(cydaoi.find(id)).getCydto();
    }

    /**
     * @return List<CompanyDTO> lcy
     */
    public List<CompanyDTO> findAll() {
        List<Company> lcp = cydaoi.findAll();
        List<CompanyDTO> lcy = new ArrayList<CompanyDTO>();
        for (int i = 0; i < lcp.size(); i++) {
            lcy.add(new CompanyMapper(lcp.get(i)).getCydto());
        }
        return lcy;
    }

}
