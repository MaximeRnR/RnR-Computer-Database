package com.excilys.formation.persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.excilys.formation.model.Company;
import com.excilys.formation.model.Computer;
import com.excilys.formation.ui.Page;
import com.excilys.formation.util.PersistenceException;


public enum ComputerDAO implements ComputerDAOInterface {
    COMPUTERDAO;
    final String createQuery = "insert into computer(name,introduced,discontinued,company_id)values (?,?,?,?)";
    final String findByIdQuery = "select cp.id, cp.name, cp.introduced, cp.discontinued, cy.id, cy.name from computer cp "
            + "left join company cy on cp.company_id = cy.id where cp.id=?";
    final String getPageOfComputerQuery = "select cp.id, cp.name, cp.introduced, cp.discontinued, cy.id, cy.name from computer cp "
            + "left join company cy on cp.company_id = cy.id LIMIT ? OFFSET ?;";
    final String countQuery = "SELECT count(*) FROM computer c;";
    final String getPageOfComputerByNameQuery = "select cp.id, cp.name, cp.introduced, cp.discontinued, cy.id, cy.name from computer cp "
            + "left join company cy on cp.company_id = cy.id WHERE cp.name LIKE ? LIMIT ? OFFSET ? ;";
    final String updateQuery = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";
    static Logger logger = LogManager.getRootLogger();

    /**
     */
    ComputerDAO() {

    }

    /**
     * @param computer Computer
     * @return long id
     * @throws PersistenceException cdbex
     */
    public long createComputer(Computer computer) throws PersistenceException {

        try (Connection conn = ConnectionDB.CONNECTION.getConn();
                PreparedStatement preparedStmt = conn.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS);
                ) {

            this.prepareComputer(computer, preparedStmt);
            preparedStmt.execute();
            try (ResultSet rs = preparedStmt.getGeneratedKeys();) {
                if (rs.next()) {
                    logger.info("Computer " + rs.getInt(1) + " created");
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            logger.error("Computer not created" + e);
            throw new PersistenceException("Computer not created", e);
        }
        throw new PersistenceException("id not generated");

    }

    /**
     * @param ids Ids
     * @return boolean boolean
     * @throws PersistenceException cdbex
     */
    public boolean delete(String ids) throws PersistenceException {
        String query = "DELETE FROM computer WHERE id = ?";
        String[] idTab = ids.split(",");
        if (idTab.length > 1) {
            for (int i = 0; i < idTab.length - 1; i++) {
                query = query + " OR id = ?";
            }
        }

        try (Connection conn = ConnectionDB.CONNECTION.getConn();
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                ) {
            Computer computer = findById(Integer.parseInt(idTab[0]));
            preparedStmt.setLong(1, computer.getId());
            if (idTab.length > 1) {
                for (int i = 1; i < idTab.length; i++) {
                    computer = findById(Integer.parseInt(idTab[i]));
                    preparedStmt.setLong((i + 1), computer.getId());
                }
            }

            preparedStmt.execute();
            logger.info("Computer " + computer.getId() + " deleted");
            return true;
        } catch (NumberFormatException | SQLException e) {
            logger.error("Computer not deleted ");
            throw new PersistenceException("Computer not deleted ", e);
        }
    }

    /**
     * @param computer Computer
     * @throws PersistenceException cdbex
     */
    public void update(Computer computer) throws PersistenceException {

        try (Connection conn = ConnectionDB.CONNECTION.getConn();
                PreparedStatement preparedStmt = conn.prepareStatement(updateQuery);
                ) {
            this.prepareComputer(computer, preparedStmt);
            preparedStmt.setLong(5, computer.getId());
            preparedStmt.executeUpdate();
            logger.info("Computer " + computer.getId() + " updated ");
        } catch (SQLException e) {
            logger.error("Computer not updated ");
            throw new PersistenceException("Computer not updated ", e);
        }
    }

    /**
     * @param id id
     * @throws PersistenceException cdbex
     * @return Computer
     */
    public Computer findById(long id) throws PersistenceException {
        try (Connection conn = ConnectionDB.CONNECTION.getConn();
                PreparedStatement preparedStmt = conn.prepareStatement(findByIdQuery);
                ) {

            preparedStmt.setLong(1, id);
            preparedStmt.execute();
            try (ResultSet result = preparedStmt.getResultSet();) {
                if (result.first()) {
                    Computer.Builder computerBuild = new Computer.Builder()
                            .id(result.getLong("cp.id"))
                            .name(result.getString("cp.name"))
                            .di(null)
                            .dd(null)
                            .cy(new Company(result.getInt("cy.id"), result.getString("cy.name")));

                    if (result.getTimestamp("cp.introduced") != null) {
                        computerBuild.di(result.getTimestamp("cp.introduced").toLocalDateTime().toLocalDate());
                    }
                    if (result.getDate("cp.discontinued") != null) {
                        computerBuild.dd(result.getTimestamp("cp.discontinued").toLocalDateTime().toLocalDate());
                    }
                    Computer computer = computerBuild.build();
                    logger.info("Computer " + computer.getId() + " selected ");
                    return computer;
                }
            }
        } catch (SQLException e) {
            logger.error("Computer not found ");
            throw new PersistenceException("Computer not found ", e);
        }

        throw new PersistenceException("Computer not found ");

    }
    /**
     * @return List<Computer>
     * @param page page
     * @throws PersistenceException cdbex
     */
    public List<Computer> getPageOfComputers(Page page) throws PersistenceException {

        try (Connection conn = ConnectionDB.CONNECTION.getConn();
                PreparedStatement preparedStmt = conn.prepareStatement(getPageOfComputerQuery);
                ) {

            preparedStmt.setInt(1, page.maxNumberOfObject);
            preparedStmt.setInt(2, (page.getIndex()) * page.maxNumberOfObject);
            preparedStmt.execute();

            try (ResultSet result = preparedStmt.getResultSet();) {
                List<Computer> computers = new ArrayList<>();
                Computer.Builder computerBuild = new Computer.Builder();
                while (result.next()) {
                    computerBuild.id(result.getLong("cp.id"))
                    .name(result.getString("cp.name"))
                    .di(null)
                    .dd(null)
                    .cy(new Company(result.getInt("cy.id"), result.getString("cy.name")));

                    if (result.getTimestamp("cp.introduced") != null) {
                        computerBuild.di(result.getTimestamp("cp.introduced")
                                .toLocalDateTime()
                                .toLocalDate());
                    }
                    if (result.getTimestamp("cp.discontinued") != null) {
                        computerBuild.dd(result.getTimestamp("cp.discontinued")
                                .toLocalDateTime()
                                .toLocalDate());
                    }

                    computers.add(computerBuild.build());
                }
                logger.info("Computers selected ");
                return computers;
            }
        } catch (SQLException e) {
            logger.error("Computers not found ");
            throw new PersistenceException("Computers not found ", e);
        }
    }

    /**
     * @return nbComputer
     */
    @Override
    public int getCountOfAllComputers() throws PersistenceException {

        try (Connection conn = ConnectionDB.CONNECTION.getConn();
                PreparedStatement preparedStmt = conn.prepareStatement(countQuery);
                ) {

            preparedStmt.execute();
            try (ResultSet result = preparedStmt.getResultSet();) {
                while (result.next()) {

                    return result.getInt(1);
                }
            }
        } catch (SQLException e) {
            logger.error("Computer count not found ");
            throw new PersistenceException("Computer count not found ", e);
        }
        throw new PersistenceException("Computer count not found ");
    }

    /**
     * @return nbComputer
     */
    @Override
    public int getCountOfComputersByName(String search) throws PersistenceException {

        String sql = "SELECT count(*) FROM computer c WHERE c.name LIKE ? ;";
        try (Connection conn = ConnectionDB.CONNECTION.getConn();
                PreparedStatement preparedStmt = conn.prepareStatement(sql);
                ) {
            preparedStmt.setString(1, "%" + search + "%");
            preparedStmt.execute();
            try (ResultSet result = preparedStmt.getResultSet();) {
                while (result.next()) {
                    return result.getInt(1);
                }
            }
        } catch (SQLException e) {
            logger.error("Computer Count not found ");
            throw new PersistenceException("Computer Count By Name not found ", e);
        }
        throw new PersistenceException("Computer Count By Name not found ");
    }


    @Override
    public int getCountOfComputersByCompanyName(String search) throws PersistenceException {

        String companyQuery = "SELECT c.id FROM company c WHERE c.name LIKE ?";
        try (Connection conn = ConnectionDB.CONNECTION.getConn();
                PreparedStatement preparedStmt = conn.prepareStatement(companyQuery);
                ) {
            preparedStmt.setString(1, "%" + search + "%");
            preparedStmt.execute();
            try (ResultSet result = preparedStmt.getResultSet();) {
                String sql = "SELECT count(*) FROM computer c WHERE c.company_id = ?";
                boolean first = true;
                while (result.next()) {
                    if (!first) {
                        sql += " OR c.company_id = ?";
                    }
                    first = false;
                }
                sql += ";";
                try (PreparedStatement preparedStmt2 = conn.prepareStatement(sql);) {
                    result.absolute(0);
                    int i = 1;
                    while (result.next()) {
                        preparedStmt2.setInt(i, result.getInt(1));
                        i++;
                    }
                    preparedStmt2.execute();
                    try (ResultSet result2 = preparedStmt2.getResultSet();) {
                        while (result2.next()) {
                            return result2.getInt(1);
                        }
                    }

                }
            }
        } catch (SQLException e) {
            logger.error("Computer Count By Company Name not found");
            throw new PersistenceException("Computer Count By Company Name not found", e);
        }
        throw new PersistenceException("Computer Count By Company Name not found");
    }


    @Override
    public List<Computer> getPageOfComputersByName(String search, Page page) throws PersistenceException  {

        try (Connection conn = ConnectionDB.CONNECTION.getConn();
                PreparedStatement preparedStmt = conn.prepareStatement(getPageOfComputerByNameQuery);
                ) {

            preparedStmt.setString(1, "%" + search + "%");
            preparedStmt.setInt(2, page.maxNumberOfObject);
            preparedStmt.setInt(3, (page.getIndex()) * page.maxNumberOfObject);
            preparedStmt.execute();

            try (ResultSet result = preparedStmt.getResultSet();) {
                List<Computer> computers = new ArrayList<Computer>();
                while (result.next()) {
                    Computer.Builder computerBuild = new Computer.Builder()
                            .id(result.getLong("cp.id"))
                            .name(result.getString("cp.name"))
                            .di(null)
                            .dd(null)
                            .cy(new Company(result.getInt("cy.id"), result.getString("cy.name")));

                    if (result.getDate("cp.introduced") != null) {
                        computerBuild.di(result.getDate("cp.introduced").toLocalDate());
                    }
                    if (result.getDate("cp.discontinued") != null) {
                        computerBuild.dd(result.getDate("cp.discontinued").toLocalDate());
                    }

                    computers.add(computerBuild.build());
                }
                logger.info("Computers selected ");
                return computers;
            }
        } catch (SQLException e) {
            logger.error("Computers not found ");
            throw new PersistenceException("Computers not found ", e);
        }

    }


    @Override
    public List<Computer> getPageOfComputersByCompanyName(String search, Page page) throws PersistenceException  {

        String companyQuery = "SELECT c.id FROM company c WHERE c.name LIKE ?";
        try (Connection conn = ConnectionDB.CONNECTION.getConn();
                PreparedStatement preparedStmt = conn.prepareStatement(companyQuery);
                ) {
            preparedStmt.setString(1, "%" + search + "%");
            preparedStmt.execute();
            try (ResultSet result = preparedStmt.getResultSet();) {
                String sql = "select cp.id, cp.name, cp.introduced, cp.discontinued, cy.id, cy.name from computer cp "
                        + "left join company cy on cp.company_id = cy.id WHERE cy.id = ?";
                boolean first = true;
                while (result.next()) {
                    if (!first) {
                        sql += " OR cy.id = ?";
                    }
                    first = false;
                }
                sql += " LIMIT ? OFFSET ?;";
                try (PreparedStatement preparedStmt2 = conn.prepareStatement(sql);) {
                    result.absolute(0);
                    int i = 1;
                    while (result.next()) {
                        preparedStmt2.setInt(i, result.getInt(1));
                        i++;
                    }
                    preparedStmt2.setInt(i, page.maxNumberOfObject);
                    preparedStmt2.setInt(i + 1, (page.getIndex()) * page.maxNumberOfObject);
                    preparedStmt2.execute();
                    try (ResultSet result2 = preparedStmt2.getResultSet();) {
                        List<Computer> computers = new ArrayList<Computer>();
                        while (result2.next()) {
                            Computer.Builder computerBuild = new Computer.Builder()
                                    .id(result2.getLong("cp.id"))
                                    .name(result2.getString("cp.name"))
                                    .di(null)
                                    .dd(null)
                                    .cy(new Company(result2.getInt("cy.id"), result2.getString("cy.name")));

                            if (result2.getDate("cp.introduced") != null) {
                                computerBuild.di(result2.getDate("cp.introduced").toLocalDate());
                            }
                            if (result2.getDate("cp.discontinued") != null) {
                                computerBuild.dd(result2.getDate("cp.discontinued").toLocalDate());
                            }

                            computers.add(computerBuild.build());
                        }
                        logger.info("Computers selected ");
                        return computers;
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("Computers not found");
            throw new PersistenceException("Computers not found", e);
        }
    }


    /**
     * @param computer computer
     * @param preparedStmt PreparetStatement
     * @throws SQLException sql
     * @return preparedStatement
     */
    private PreparedStatement prepareComputer(Computer computer, PreparedStatement preparedStmt) throws SQLException {

        preparedStmt.setString(1, computer.getName());
        if (computer.getdIntroduced() != null) {
            preparedStmt.setTimestamp(2, new Timestamp(Date.valueOf(computer.getdIntroduced()).getTime()));
        } else {
            preparedStmt.setNull(2, java.sql.Types.TIMESTAMP);
        }
        if (computer.getdDiscontinued() != null) {
            preparedStmt.setTimestamp(3, new Timestamp(Date.valueOf(computer.getdDiscontinued()).getTime()));
        } else {
            preparedStmt.setNull(3, java.sql.Types.TIMESTAMP);
        }
        if (computer.getCy().getId() != 0) {
            preparedStmt.setLong(4, computer.getCy().getId());
        } else {
            preparedStmt.setNull(4, java.sql.Types.BIGINT);
        }
        return preparedStmt;
    }

}