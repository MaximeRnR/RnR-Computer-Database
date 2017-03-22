package com.excilys.formation.persistence;

import java.util.List;

import com.excilys.formation.model.Company;

public interface CompanyDAOInterface {
    /**
     * @param id Id
     * @return Company
     */
    Company find(long id);

    /**
     * @return List<Company>
     */
    List<Company> findAll();
}
