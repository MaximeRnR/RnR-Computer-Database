package com.excilys.formation.console.cli;

import java.util.List;

import com.excilys.formation.console.dto.CompanyDTO;

//CAlled by App
public class CompanyUI {
    private CompanyDTO cy;
    private List<CompanyDTO> lcy;
    /**
     * @param id id
     * @return cy company
     */
    public CompanyDTO findAssociatedCompany(long id) {
        return cy;

    }

    /**
     * @return lcy List<Company>
     */
    public List<CompanyDTO> findAllCompany() {
        return lcy;

    }
}