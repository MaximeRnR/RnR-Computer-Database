package com.excilys.formation.service;

import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.mapper.CompanyMapper;
import com.excilys.formation.model.Company;
import com.excilys.formation.persistence.CompanyDAO;
import com.excilys.formation.persistence.CompanyDAOInterface;
import com.excilys.formation.ui.CompanyModelUI;
import com.excilys.formation.util.ComputerDBException;

public class CompanyService {
    private CompanyDAOInterface cydaoi = CompanyDAO.COMPANYDAO;

    public CompanyService() throws ComputerDBException {

    }

    public CompanyModelUI find(long id) {

        return new CompanyMapper(cydaoi.find(id)).getCymui();
    }

    public List<CompanyModelUI> findAll() {
        List<Company> lcp = cydaoi.findAll();
        List<CompanyModelUI> lcy = new ArrayList<CompanyModelUI>();
        for (int i = 0; i < lcp.size(); i++) {
            lcy.add(new CompanyMapper(lcp.get(i)).getCymui());
        }
        return lcy;
    }

}
