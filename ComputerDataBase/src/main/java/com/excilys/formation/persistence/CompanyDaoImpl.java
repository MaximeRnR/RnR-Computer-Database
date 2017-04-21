package com.excilys.formation.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.formation.model.Company;
import com.excilys.formation.util.PersistenceException;
import com.zaxxer.hikari.HikariDataSource;

@Repository
public class CompanyDaoImpl implements CompanyDao {
    private Logger logger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);

    @Autowired
    private HikariDataSource hs;

    public void setHs(HikariDataSource hs) {
        this.hs = hs;
    }
    /**
     * @throws PersistenceException cdbex
     */
    CompanyDaoImpl() {

    }

    /**
     * @param id Id
     * @throws PersistenceException cdbex
     * @return Company
     */
    public Company findById(long id) throws PersistenceException {
        final String sql = "SELECT c.id, c.name FROM company c WHERE c.id=?";
        try (Connection conn = hs.getConnection();
                PreparedStatement preparedStmt = conn.prepareStatement(sql);) {

            preparedStmt.setLong(1, id);
            preparedStmt.execute();
            try (ResultSet result = preparedStmt.getResultSet();) {
                Company company = new Company(0);
                if (result.first()) {
                    if (result.getInt("c.id") != 0) {
                        company.setId(result.getInt("c.id"));
                    }
                    if (result.getString("c.name") != null) {
                        company.setName(result.getString("c.name"));
                    }
                }
                logger.info("Company " + company.getId() + " selected");
                return company;
            }
        } catch (SQLException e) {
            logger.error("Company not selected ");
            throw new PersistenceException("This company does'nt exist", e);
        }
    }


    /**
     * @throws PersistenceException cdbex
     * @return List<Company>
     */
    public List<Company> findAll() throws PersistenceException {
        final String sql = "SELECT c.id, c.name FROM company c;";
        try (Connection conn = hs.getConnection();
                ResultSet result = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
                        .executeQuery(sql);) {
            List<Company> companies = new ArrayList<>();
            while (result.next()) {
                Company company = new Company(result.getLong("c.id"), result.getString("c.name"));
                companies.add(company);
            }
            logger.info("Companies selected");
            return companies;
        } catch (SQLException e) {
            logger.error("Company not selected ");
            throw new PersistenceException("Company not selected", e);
        }
    }


    /**
     * @param id Id
     */
    @Override
    public void deleteCompany(long id) {
        final String queryDeleteComputer = "delete from computer where company_id = ?";
        final String queryDeleteCompany = "delete from company where id = ?";

        try (Connection conn = hs.getConnection();) {
            try (PreparedStatement preparedStmt = conn.prepareStatement(queryDeleteComputer);) {
                conn.setAutoCommit(false);
                preparedStmt.setLong(1, id);
                preparedStmt.executeQuery();
                try (
                        PreparedStatement preparedStmtCompany = conn.prepareStatement(queryDeleteCompany)) {
                    preparedStmtCompany.setLong(1, id);
                    preparedStmtCompany.executeQuery();
                    conn.commit();
                    conn.setAutoCommit(true);
                }

            } catch (SQLException e) {
                conn.rollback();
                conn.setAutoCommit(true);
                logger.error("Company not deleted");
                throw new PersistenceException("deleteCompany " + e);
            }
        } catch (SQLException e1) {
            logger.error("Company not deleted");
            throw new PersistenceException("deleteCompany " + e1);
        }

    }

}