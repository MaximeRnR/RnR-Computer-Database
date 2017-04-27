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

    private static QCompany qCompany = QCompany.company;
    private static QComputer qComputer = QComputer.computer;

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

        Computer computerResult = (Computer) queryFactory.get().select(qComputer)
        .from(qComputer)
        .leftJoin(qCompany).on(qComputer.cy.id.eq(qCompany.id))
        .where(qComputer.id.eq((Long) id))
        .fetchOne();

        return computerResult;
    }

    @Override
    @Transactional
    public boolean delete(List<Long> ids) {

        for (int i = 0; i < ids.size(); i++) {
            queryFactory.get().delete(qComputer)
            .where(qComputer.id.eq(ids.get(i))).execute();
        }
        return true;
    }

    @Override
    @Transactional
    public void update(Computer computer) {

        queryFactory.get().update(qComputer)
        .where(qComputer.id.eq(computer.getId()))
        .set(qComputer.name, computer.getName())
        .set(qComputer.dateIntroduced, computer.getDateIntroduced())
        .set(qComputer.dateDiscontinued, computer.getDateIntroduced())
        .set(qComputer.cy, computer.getCy())
        .execute();
    }

    @Override
    @Transactional
    public int getCountOfAllComputers() {
        return  (int) queryFactory.get().from(qComputer).fetchCount();
    }

    @Override
    @Transactional
    public List<Computer> getPageOfComputers(Page page) {
        List<Computer> computersResult =  queryFactory.get()
                .select(qComputer)
                .from(qComputer)
                .leftJoin(qCompany)
                .on(qCompany.id.eq(qComputer.cy.id))
                .limit(page.maxNumberOfObject)
                .offset(page.maxNumberOfObject * page.getIndex())
                .fetch();
        return computersResult;
    }

    @Override
    @Transactional
    public int getCountOfComputersByName(String search) {
        return  (int) queryFactory.get().from(qComputer).fetchCount();
    }

    @Override
    @Transactional
    public int getCountOfComputersByCompanyName(String search) {
        return  (int) queryFactory.get().from(qComputer).where(qComputer.cy.name.like(search + "%")).fetchCount();
    }

    @Override
    @Transactional
    public List<Computer> getPageOfComputersByName(String search, Page page) {
        List<Computer> computersResult =  queryFactory.get()
                .select(qComputer)
                .from(qComputer)
                .leftJoin(qCompany)
                .on(qCompany.id.eq(qComputer.cy.id))
                .where(qComputer.name.like(search + "%"))
                .limit(page.maxNumberOfObject)
                .offset(page.maxNumberOfObject * page.getIndex())
                .fetch();
        return computersResult;
    }

    @Override
    @Transactional
    public List<Computer> getPageOfComputersByCompanyName(String search, Page page) {
        List<Computer> computersResult =  queryFactory.get()
                .select(qComputer)
                .from(qComputer)
                .leftJoin(qCompany)
                .on(qCompany.id.eq(qComputer.cy.id))
                .where(qComputer.cy.name.like(search + "%"))
                .limit(page.maxNumberOfObject)
                .offset(page.maxNumberOfObject * page.getIndex())
                .fetch();
        return computersResult;
    }

}
