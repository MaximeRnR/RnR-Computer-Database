package com.excilys.formation.service;

import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.dto.CompanyDTO;
import com.excilys.formation.mapper.CompanyMapperService;
import com.excilys.formation.model.Company;
import com.excilys.formation.persistence.CompanyDAO;
import com.excilys.formation.persistence.CompanyDAOInterface;



public enum CompanyService {
    COMPANYSERVICE;
    private CompanyDAOInterface CompanyDaoI = CompanyDAO.COMPANYDAO;

    /**
     */
    CompanyService() {

    }

    /**
     * @param id id
     * @return CompanyDTO cydto
     */
    public CompanyDTO findById(long id) {

        return new CompanyMapperService(CompanyDaoI.findById(id)).getCompanyDto();
    }

    /**
     * @return List<CompanyDTO> lcy
     */
    public List<CompanyDTO> findAll() {
        List<Company> companies = CompanyDaoI.findAll();
        List<CompanyDTO> companiesDto = new ArrayList<>();
        for (int i = 0; i < companies.size(); i++) {
            companiesDto.add(new CompanyMapperService(companies.get(i)).getCompanyDto());
        }
        return companiesDto;
    }

}
