package com.excilys.formation.persistence;

import java.util.List;

import com.excilys.formation.model.Company;

public interface CompanyDAOInterface {
    public abstract Company find(long id);

    public abstract List<Company> findAll();
}
