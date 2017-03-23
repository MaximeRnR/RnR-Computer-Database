package com.excilys.formation.mapper;

import com.excilys.formation.entity.CompanyEntity;
import com.excilys.formation.model.Company;
import com.excilys.formation.util.ComputerDBException;

public class CompanyMapperEntity {
    private CompanyEntity cyE;
    private Company cy;

    /**
     * @param cyE CompanyEntity
     * @throws ComputerDBException cdbex
     */
    public CompanyMapperEntity(CompanyEntity cyE) throws ComputerDBException {

        this.cyE = cyE;
        this.cy = new Company(this.cyE.getId(), this.cyE.getName());

    }

    /**
     * @param cy Company
     * @throws ComputerDBException cdbex
     */
    public CompanyMapperEntity(Company cy) throws ComputerDBException {

        this.cy = cy;
        this.cyE = new CompanyEntity(this.cy.getId(), this.cy.getName());

    }

    public CompanyEntity getCyE() {
        return cyE;
    }


    public Company getCy() {
        return cy;
    }


}
