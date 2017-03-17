package com.excilys.formation.ui;

import java.util.List;

import com.excilys.formation.service.CompanyService;

//CAlled by App
public class CompanyUI {
    private CompanyModelUI cy;
    private CompanyService cyS;

    public CompanyModelUI FindAssociatedCompany(long id) {
        cyS = new CompanyService();
        cy = cyS.find(id);
        return cy;

    }

    public List<CompanyModelUI> FindAllCompany() {
        cyS = new CompanyService();
        return cyS.findAll();

    }
}
