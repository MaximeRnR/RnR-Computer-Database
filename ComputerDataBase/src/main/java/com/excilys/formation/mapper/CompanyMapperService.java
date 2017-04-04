package com.excilys.formation.mapper;

import com.excilys.formation.dto.CompanyDTO;
import com.excilys.formation.model.Company;
import com.excilys.formation.util.ComputerDBException;

public class CompanyMapperService {
    private Company company;
    private CompanyDTO companyDto;

    /**
     * @param company Company
     * @throws ComputerDBException cdbex
     */
    public CompanyMapperService(Company company) throws ComputerDBException {

        this.company = company;
        this.companyDto = new CompanyDTO(this.company.getId(), this.company.getName());

    }

    /**
     * @param companyDto CompanyDTO
     * @throws ComputerDBException cdbex
     */
    public CompanyMapperService(CompanyDTO companyDto) throws ComputerDBException {

        this.companyDto = companyDto;
        this.company = new Company(this.companyDto.getId(), this.companyDto.getName());

    }

    public Company getCompany() {
        return company;
    }


    public CompanyDTO getCompanyDto() {
        return companyDto;
    }


}