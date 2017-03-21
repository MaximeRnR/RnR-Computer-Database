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

import com.excilys.formation.model.Computer;
import com.excilys.formation.ui.Page;
import com.excilys.formation.util.ComputerDBException;

//DAO of Computer
public enum ComputerDAO implements ComputerDAOInterface {
    COMPUTERDAO;
    private Connection conn;
    private Computer cp;
    static Logger logger = LogManager.getRootLogger();

    private ComputerDAO() throws ComputerDBException {

        this.conn = ConnectionDB.CONNECTION.getConn();
    }

    public long createComputer(Computer cp) throws ComputerDBException {
        String query = "insert into computer" + "(name,introduced,discontinued,company_id)" + "values (?,?,?,?)";
        try (PreparedStatement preparedStmt = this.conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);) {

            preparedStmt.setString(1, cp.getName());
            if (cp.getdIntroduced() != null) {
                preparedStmt.setTimestamp(2, new Timestamp(Date.valueOf(cp.getdIntroduced()).getTime()));
            } else
                preparedStmt.setNull(2, java.sql.Types.TIMESTAMP);
            if (cp.getdDiscontinued() != null) {
                preparedStmt.setTimestamp(3, new Timestamp(Date.valueOf(cp.getdDiscontinued()).getTime()));
            } else
                preparedStmt.setNull(3, java.sql.Types.TIMESTAMP);
            if (cp.getManufacturer() != 0) {
                preparedStmt.setLong(4, cp.getManufacturer());
            } else
            preparedStmt.setNull(4, java.sql.Types.BIGINT);
            preparedStmt.execute();
            try (ResultSet rs = preparedStmt.getGeneratedKeys();) {
                if (rs.next()) {
                    logger.info("Computer " + rs.getInt(1) + " created");
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            logger.error("Computer not created");
            e.printStackTrace();
            throw new ComputerDBException("Computer not created", e);
        }
        return -1;

    }

    public void delete(long id) throws ComputerDBException {
        String query = "DELETE FROM computer WHERE id = ?";

        try (PreparedStatement preparedStmt = this.conn.prepareStatement(query);) {

            this.cp = find(id);
            preparedStmt.setLong(1, cp.getId());
            preparedStmt.execute();
            logger.info("Computer " + cp.getId() + " deleted");
        } catch (SQLException e) {
            logger.error("Computer not deleted ");
            throw new ComputerDBException("Computer not deleted ", e);
        }
    }

    public void update(Computer cp) throws ComputerDBException {

        find(cp.getId());

        String query = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";

        try (PreparedStatement preparedStmt = this.conn.prepareStatement(query);) {
            preparedStmt.setString(1, cp.getName());
            if (cp.getdIntroduced() != null) {
                preparedStmt.setTimestamp(2, new Timestamp(Date.valueOf(cp.getdIntroduced()).getTime()));
            } else
                preparedStmt.setNull(2, java.sql.Types.TIMESTAMP);
            if (cp.getdDiscontinued() != null) {
                preparedStmt.setTimestamp(3, new Timestamp(Date.valueOf(cp.getdDiscontinued()).getTime()));
            } else
                preparedStmt.setNull(3, java.sql.Types.TIMESTAMP);
            preparedStmt.setLong(4, cp.getManufacturer());
            preparedStmt.setLong(5, cp.getId());
            preparedStmt.executeUpdate();
            logger.info("Computer " + cp.getId() + " updated ");
        } catch (SQLException e) {
            logger.error("Computer not updated ");
            throw new ComputerDBException("Computer not updated ", e);
        }
    }

    public Computer find(long id) throws ComputerDBException {
        cp = null;
        String sql = "SELECT c.id, c.name, c.introduced, c.discontinued, c.company_id FROM computer c WHERE c.id=?;";
        try (PreparedStatement preparedStmt = this.conn.prepareStatement(sql);) {

            preparedStmt.setLong(1, id);
            preparedStmt.execute();
            try (ResultSet result = preparedStmt.getResultSet();) {
                while (result.next()) {
                    cp = new Computer.Builder().id(result.getLong("c.id")).name(result.getString("c.name")).di(null)
                            .dd(null).manufacturer(result.getInt("c.company_id")).build();

                    if (result.getTimestamp("c.introduced") != null)
                        cp.setdIntroduced(result.getTimestamp("c.introduced").toLocalDateTime().toLocalDate());
                    if (result.getDate("c.discontinued") != null)
                        cp.setdDiscontinued(result.getTimestamp("c.discontinued").toLocalDateTime().toLocalDate());
                    logger.info("Computer " + cp.getId() + " selected ");
                    return cp;
                }
            }
        } catch (SQLException e) {
            logger.error("Computer not found ");
            throw new ComputerDBException("Computer not found ", e);
        }
        return cp;

    }

    public List<Computer> page() throws ComputerDBException {

        List<Computer> lcp = new ArrayList<Computer>();
        Computer cp;
        String sql = "SELECT c.id, c.name, c.introduced, c.discontinued, c.company_id FROM computer c LIMIT ? OFFSET ?;";

        try (PreparedStatement preparedStmt = this.conn.prepareStatement(sql);) {

            preparedStmt.setInt(1, Page.MAX_NUMBER_OF_OBJECT);
            preparedStmt.setInt(2, (Page.PAGE.getIndex()) * Page.MAX_NUMBER_OF_OBJECT);
            preparedStmt.execute();

            try (ResultSet result = preparedStmt.getResultSet();) {
                while (result.next()) {
                    cp = new Computer.Builder().id(result.getLong("c.id")).name(result.getString("c.name")).di(null)
                            .dd(null).manufacturer(result.getInt("c.company_id")).build();

                    if (result.getDate("c.introduced") != null)
                        cp.setdIntroduced(result.getDate("c.introduced").toLocalDate());
                    if (result.getDate("c.discontinued") != null)
                        cp.setdDiscontinued(result.getDate("c.discontinued").toLocalDate());

                    lcp.add(cp);
                }
                logger.info("Computers selected ");
            }
        } catch (SQLException e) {
            logger.error("Computers not found ");
            throw new ComputerDBException("Computers not found ", e);
        }

        return lcp;
    }

    @Override
    public int count() throws ComputerDBException {

        String sql = "SELECT count(*) FROM computer c;";
        try (PreparedStatement preparedStmt = this.conn.prepareStatement(sql);) {

            preparedStmt.execute();
            try (ResultSet result = preparedStmt.getResultSet();) {
                while (result.next()) {

                    return result.getInt(1);
                }
            }
        } catch (SQLException e) {
            logger.error("Computer not found ");
            throw new ComputerDBException("Computer not found ", e);
        }
        return -1;
    }

}