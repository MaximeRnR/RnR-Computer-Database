package com.excilys.formation.persistence.implementation.querydsl;

import java.util.List;
import java.util.function.Supplier;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.formation.model.Company;
import com.excilys.formation.model.QCompany;
import com.excilys.formation.persistence.CompanyDao;
import com.querydsl.jpa.hibernate.HibernateQueryFactory;
import com.zaxxer.hikari.HikariDataSource;

@Repository
@Transactional
public class CompanyDaoQuerydslImpl implements CompanyDao {

    @Autowired
    private HikariDataSource hs;

    public void setHs(HikariDataSource hs) {
        this.hs = hs;
    }

    private static QCompany qCompany = QCompany.company;

    private SessionFactory sessionFactory;

    private Supplier<HibernateQueryFactory> queryFactory =
            () -> new HibernateQueryFactory(sessionFactory.getCurrentSession());

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public Company findById(long id) {
        Company companyResult = (Company) queryFactory.get().select(qCompany)
                .from(qCompany)
                .where(qCompany.id.eq((Long) id))
                .fetchOne();

                return companyResult;
    }

    @Override
    @Transactional
    public List<Company> findAll() {
       List<Company> companiesResult =  queryFactory.get().select(qCompany)
                .from(qCompany)
                .fetch();

                return companiesResult;
    }

    @Override
    @Transactional
    public void deleteCompany(long id) {
        // TODO Auto-generated method stub
    }
}

