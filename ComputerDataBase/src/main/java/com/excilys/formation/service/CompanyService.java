package com.excilys.formation.service;

import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.dto.CompanyDTO;
import com.excilys.formation.mapper.CompanyMapperService;
import com.excilys.formation.model.Company;
import com.excilys.formation.persistence.CompanyDAO;



public enum CompanyService {
    INSTANCE;

    /**
     */
    CompanyService() {

    }

    /**
     * @param id id
     * @return CompanyDTO cydto
     */
    public CompanyDTO findById(long id) {

        return CompanyMapperService.INSTANCE.toCompanyDto(CompanyDAO.INSTANCE.findById(id));
    }

    /**
     * @return List<CompanyDTO> lcy
     */
    public List<CompanyDTO> findAll() {
        List<Company> companies = CompanyDAO.INSTANCE.findAll();
        List<CompanyDTO> companiesDto = new ArrayList<>();
        for (int i = 0; i < companies.size(); i++) {
            companiesDto.add(CompanyMapperService.INSTANCE.toCompanyDto(companies.get(i)));
        }
        return companiesDto;
    }

}
