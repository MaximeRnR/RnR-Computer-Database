package com.excilys.formation.service;

import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.dto.CompanyDTO;
import com.excilys.formation.mapper.CompanyMapperService;
import com.excilys.formation.model.Company;
import com.excilys.formation.persistence.CompanyDao;



public class CompanyService {
    private CompanyDao companyDao;

    public void setCompanyDao(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }
    /**
     */
    CompanyService() {

    }

    /**
     * @param id id
     * @return CompanyDTO cydto
     */
    public CompanyDTO findById(long id) {

        return CompanyMapperService.INSTANCE.toCompanyDto(companyDao.findById(id));
    }

    /**
     * @return List<CompanyDTO> lcy
     */
    public List<CompanyDTO> findAll() {
        List<Company> companies = companyDao.findAll();
        List<CompanyDTO> companiesDto = new ArrayList<>();
        for (int i = 0; i < companies.size(); i++) {
            companiesDto.add(CompanyMapperService.INSTANCE.toCompanyDto(companies.get(i)));
        }
        return companiesDto;
    }

}
