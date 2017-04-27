package com.excilys.formation.mapper;

import com.excilys.formation.dto.CompanyDTO;
import com.excilys.formation.model.Company;
import com.excilys.formation.util.ComputerDBException;

public enum CompanyMapperService {
   INSTANCE;

    /**
     * @param company Company
     * @throws ComputerDBException cdbex
     * @return CompanyDTO companyDto
     */
    public CompanyDTO toCompanyDto(Company company) throws ComputerDBException {
        if (company == null || company.getId() == 0) {
            return new CompanyDTO(0, null);
        } else {
            return new CompanyDTO(company.getId(), company.getName());
        }
    }

    /**
     * @param companyDto CompanyDTO
     * @throws ComputerDBException cdbex
     * @return Company company
     */
    public Company toCompany(CompanyDTO companyDto) throws ComputerDBException {

        if (companyDto == null || companyDto.getId() == 0) {
            return null;
        } else {
            return new Company(companyDto.getId(), companyDto.getName());
        }
    }

}