package com.excilys.formation.mapper;

import com.excilys.formation.model.Company;
import com.excilys.formation.ui.CompanyModelUI;
import com.excilys.formation.util.ComputerDBException;

public class CompanyMapper {
    private Company cy;
    private CompanyModelUI cymui;

    public CompanyMapper(Company cy) throws ComputerDBException {

        this.cy = cy;
        this.cymui = new CompanyModelUI(this.cy.getId(), this.cy.getName());

    }

    public CompanyMapper(CompanyModelUI cymui) throws ComputerDBException {

        this.cymui = cymui;
        this.cy = new Company(this.cymui.getId(), this.cymui.getName());

    }

    public Company getCy() {
        return cy;
    }

    public void setCy(Company cy) {
        this.cy = cy;
    }

    public CompanyModelUI getCymui() {
        return cymui;
    }

    public void setCymui(CompanyModelUI cymui) {
        this.cymui = cymui;
    }

}
