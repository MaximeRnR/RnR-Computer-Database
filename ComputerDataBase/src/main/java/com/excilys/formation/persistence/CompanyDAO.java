package com.excilys.formation.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.excilys.formation.model.Company;
import com.excilys.formation.util.ComputerDBException;

//DAO of Company
public enum CompanyDAO implements CompanyDAOInterface {
    COMPANYDAO;
    private Connection conn;
    static Logger logger = LogManager.getRootLogger();
    private Company company;

    /**
     * @throws ComputerDBException cdbex
     */
    CompanyDAO() throws ComputerDBException {

        this.conn = ConnectionDB.CONNECTION.getConn();

    }

    /**
    * @param id Id
    * @throws ComputerDBException cdbex
    * @return Company
    */
    public Company find(long id) throws ComputerDBException {
        company = new Company();
        String sql = "SELECT c.id, c.name FROM company c WHERE c.id=?";

        try (PreparedStatement preparedStmt = this.conn.prepareStatement(sql);) {

            preparedStmt.setLong(1, id);
            preparedStmt.execute();
            try (ResultSet result = preparedStmt.getResultSet();) {

                if (result.first()) {
                    if (result.getInt("c.id") != 0) {
                        company.setId(result.getInt("c.id"));
                    }
                    if (result.getString("c.name") != null) {
                        company.setName(result.getString("c.name"));
                    }
                }
                logger.info("Company " + company.getId() + " selected");
            }
        } catch (ComputerDBException | SQLException e) {
            logger.error("Company not selected ");
            throw new ComputerDBException("This company does'nt exist", e);
        }

        return company;
    }


    /**
     * @throws ComputerDBException cdbex
     * @return List<Company>
     */
    public List<Company> findAll() throws ComputerDBException {
        List<Company> lcp = new ArrayList<Company>();
        Company cp;
        String sql = "SELECT c.id, c.name FROM company c;";
        try (ResultSet result = this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
                .executeQuery(sql);) {
            if (!result.first()) {
                throw new IllegalArgumentException();
            }
            while (result.next()) {
                cp = new Company(result.getLong("c.id"), result.getString("c.name"));
                lcp.add(cp);
            }
            logger.info("Companies selected");
        } catch (ComputerDBException | SQLException e) {
            logger.error("Company not selected ");
            throw new ComputerDBException("Company not selected", e);
        }

        return lcp;
    }

}