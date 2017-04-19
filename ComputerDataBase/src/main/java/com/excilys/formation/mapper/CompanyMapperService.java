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
        if (company == null) {
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

        if (companyDto == null) {
            return new Company(0, null);
        } else {
            return new Company(companyDto.getId(), companyDto.getName());
        }
    }

}