package com.excilys.formation.persistence;

import java.util.List;

import com.excilys.formation.model.Company;


public interface CompanyDao {
    /**
     * @param id Id
     * @return Company
     */
    Company findById(long id);

    /**
     * @return List<Company>
     */
    List<Company> findAll();
    /**
     * @param id Id
     */
    void deleteCompany(long id);
}
