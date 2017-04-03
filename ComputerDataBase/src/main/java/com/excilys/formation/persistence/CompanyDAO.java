package com.excilys.formation.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.excilys.formation.entity.CompanyEntity;
import com.excilys.formation.mapper.CompanyMapperEntity;
import com.excilys.formation.model.Company;
import com.excilys.formation.util.ComputerDBException;

//DAO of Company
public enum CompanyDAO implements CompanyDAOInterface {
    COMPANYDAO;
    static Logger logger = LogManager.getRootLogger();
    private CompanyEntity cyE;

    /**
     * @throws ComputerDBException cdbex
     */
    CompanyDAO() throws ComputerDBException {

    }

    /**
     * @param id Id
     * @throws ComputerDBException cdbex
     * @return Company
     */
    public Company find(long id) throws ComputerDBException {
        cyE = new CompanyEntity();
        final String sql = "SELECT c.id, c.name FROM company c WHERE c.id=?";

        try (Connection conn = ConnectionDB.CONNECTION.getConn();
                PreparedStatement preparedStmt = conn.prepareStatement(sql);) {

            preparedStmt.setLong(1, id);
            preparedStmt.execute();
            try (ResultSet result = preparedStmt.getResultSet();) {

                if (result.first()) {
                    if (result.getInt("c.id") != 0) {
                        cyE.setId(result.getInt("c.id"));
                    }
                    if (result.getString("c.name") != null) {
                        cyE.setName(result.getString("c.name"));
                    }
                }
                logger.info("Company " + cyE.getId() + " selected");
            }
        } catch (ComputerDBException | SQLException e) {
            logger.error("Company not selected ");
            throw new ComputerDBException("This company does'nt exist", e);
        }

        return new CompanyMapperEntity(cyE).getCy();
    }


    /**
     * @throws ComputerDBException cdbex
     * @return List<Company>
     */
    public List<Company> findAll() throws ComputerDBException {
        List<CompanyEntity> lcyE = new ArrayList<CompanyEntity>();
        final String sql = "SELECT c.id, c.name FROM company c;";
        try (Connection conn = ConnectionDB.CONNECTION.getConn();
                ResultSet result = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
                        .executeQuery(sql);) {
            while (result.next()) {
                cyE = new CompanyEntity(result.getLong("c.id"), result.getString("c.name"));
                lcyE.add(cyE);
            }
            logger.info("Companies selected");
        } catch (ComputerDBException | SQLException e) {
            logger.error("Company not selected ");
            throw new ComputerDBException("Company not selected", e);
        }
        List<Company> lcy = new ArrayList<Company>();
        for (int i = 0; i < lcyE.size(); i++) {
            lcy.add(new CompanyMapperEntity(lcyE.get(i)).getCy());
        }
        return lcy;
    }


    /**
     * @param id Id
     */
    @Override
    public void deleteCompany(long id) {
        final String queryDeleteComputer = "delete from computer where company_id = ?";
        final String queryDeleteCompany = "delete from company where id = ?";

        try (Connection conn = ConnectionDB.CONNECTION.getConn()) {
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
                logger.error("Companies not deleted");
                throw new ComputerDBException("deleteCompany " + e);
            }
        } catch (SQLException e1) {
            logger.error("Companies not deleted");
            throw new ComputerDBException("deleteCompany " + e1);
        }

    }

}