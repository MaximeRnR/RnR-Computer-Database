package com.excilys.formation.persistence.implementation.querydsl;

import java.util.List;
import java.util.function.Supplier;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.formation.model.Computer;
import com.excilys.formation.model.QCompany;
import com.excilys.formation.model.QComputer;
import com.excilys.formation.persistence.ComputerDao;
import com.excilys.formation.ui.Page;
import com.querydsl.jpa.hibernate.HibernateQueryFactory;
import com.zaxxer.hikari.HikariDataSource;

@Repository
@Transactional
public class ComputerDaoQuerydsl implements ComputerDao {

    @Autowired
    private HikariDataSource hs;

    public void setHs(HikariDataSource hs) {
        this.hs = hs;
    }

    private static QCompany company = QCompany.company;
    private static QComputer computer = QComputer.computer;

    private SessionFactory sessionFactory;

    private Supplier<HibernateQueryFactory> queryFactory =
            () -> new HibernateQueryFactory(sessionFactory.getCurrentSession());

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public long createComputer(Computer computer) {

        final String createQuery = "insert into computer(name,introduced,discontinued,company_id)values (?,?,?,?)";

        return 0;
    }

    @Override
    @Transactional
    public Computer findById(long id) {




        Computer computerResult = (Computer) queryFactory.get().select(computer)
        .from(computer)
        .leftJoin(company).on(computer.cy.id.eq(company.id))
        .where(computer.id.eq((Long) id))
        .fetchOne();

        System.out.println("flag1");
        System.out.println(computerResult.toString());
        System.out.println("flag2");
        return computerResult;
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
