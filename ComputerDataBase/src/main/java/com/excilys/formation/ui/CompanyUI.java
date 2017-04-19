package com.excilys.formation.ui;

import java.util.List;

import com.excilys.formation.dto.CompanyDTO;
import com.excilys.formation.service.CompanyService;

//CAlled by App
public class CompanyUI {
    private CompanyDTO cy;
    private CompanyService cyS;

    /**
     * @param id id
     * @return cy company
     */
    public CompanyDTO findAssociatedCompany(long id) {
        cyS = CompanyService.INSTANCE;
        cy = cyS.findById(id);
        return cy;

    }

    /**
     * @return lcy List<Company>
     */
    public List<CompanyDTO> findAllCompany() {
        cyS = CompanyService.INSTANCE;
        return cyS.findAll();

    }
}
