package com.excilys.formation.mapper;

import com.excilys.formation.dto.CompanyDTO;
import com.excilys.formation.model.Company;
import com.excilys.formation.util.ComputerDBException;

public class CompanyMapper {
    private Company cy;
    private CompanyDTO cydto;

    /**
     * @param cy Company
     * @throws ComputerDBException cdbex
     */
    public CompanyMapper(Company cy) throws ComputerDBException {

        this.cy = cy;
        this.cydto = new CompanyDTO(this.cy.getId(), this.cy.getName());

    }

    /**
     * @param cydto CompanyDTO
     * @throws ComputerDBException cdbex
     */
    public CompanyMapper(CompanyDTO cydto) throws ComputerDBException {

        this.cydto = cydto;
        this.cy = new Company(this.cydto.getId(), this.cydto.getName());

    }

    public Company getCy() {
        return cy;
    }

    public void setCy(Company cy) {
        this.cy = cy;
    }

    public CompanyDTO getCydto() {
        return cydto;
    }

    public void setCydto(CompanyDTO cydto) {
        this.cydto = cydto;
    }

}
