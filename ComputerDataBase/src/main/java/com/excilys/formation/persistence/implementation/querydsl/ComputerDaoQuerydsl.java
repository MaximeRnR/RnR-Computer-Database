package com.excilys.formation.persistence.implementation.querydsl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.formation.model.Computer;
import com.excilys.formation.model.QCompany;
import com.excilys.formation.model.QComputer;
import com.excilys.formation.persistence.ComputerDao;
import com.excilys.formation.ui.Page;
import com.querydsl.sql.Configuration;
import com.querydsl.sql.MySQLTemplates;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.SQLTemplates;
import com.zaxxer.hikari.HikariDataSource;

@Repository
public class ComputerDaoQuerydsl implements ComputerDao {

    @Autowired
    private HikariDataSource hs;

    public void setHs(HikariDataSource hs) {
        this.hs = hs;
    }

    SQLTemplates templates = new MySQLTemplates();
    Configuration configuration = new Configuration(templates);


    @Override
    public long createComputer(Computer computer) {

        final String createQuery = "insert into computer(name,introduced,discontinued,company_id)values (?,?,?,?)";

        return 0;
    }

    @Override
    public Computer findById(long id) {

        SQLQueryFactory queryFactory = new SQLQueryFactory(configuration, hs);
        QComputer computer = QComputer.computer;
        QCompany company = QCompany.company;
        System.out.println(queryFactory.select(computer.id, computer.name, computer.dateIntroduced, computer.dateDiscontinued, company.id, company.name)
        .from(computer)
        .leftJoin(company).on(computer.cy.id.eq(company.id))
        .where(computer.id.eq((Long) id))
        .fetch().toString());

        return null;
    }

    @Override
    public boolean delete(List<Long> ids) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void update(Computer computer) {
        // TODO Auto-generated method stub
    }

    @Override
    public int getCountOfAllComputers() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<Computer> getPageOfComputers(Page page) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getCountOfComputersByName(String search) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getCountOfComputersByCompanyName(String search) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<Computer> getPageOfComputersByName(String search, Page page) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Computer> getPageOfComputersByCompanyName(String search, Page page) {
        // TODO Auto-generated method stub
        return null;
    }

}
