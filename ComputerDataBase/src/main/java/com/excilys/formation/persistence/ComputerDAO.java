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

import com.excilys.formation.entity.ComputerEntity;
import com.excilys.formation.mapper.ComputerMapperEntity;
import com.excilys.formation.model.Computer;
import com.excilys.formation.ui.Page;
import com.excilys.formation.util.ComputerDBException;

//DAO of Computer
public enum ComputerDAO implements ComputerDAOInterface {
    COMPUTERDAO;
    private ComputerEntity cpE;
    static Logger logger = LogManager.getRootLogger();
    /**
     * @throws ComputerDBException cdbex
     */
    ComputerDAO() throws ComputerDBException {

    }

    /**
     * @param cp Computer
     * @return long id
     * @throws ComputerDBException cdbex
     */
    public long createComputer(Computer cp) throws ComputerDBException {
        cpE = new ComputerMapperEntity(cp).getCpE();
        final String query = "insert into computer" + "(name,introduced,discontinued,company_id)" + "values (?,?,?,?)";
        try (Connection conn = ConnectionDB.CONNECTION.getConn();
                PreparedStatement preparedStmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                ) {

            preparedStmt.setString(1, cpE.getName());
            if (cpE.getdIntroduced() != null) {
                preparedStmt.setTimestamp(2, new Timestamp(Date.valueOf(cpE.getdIntroduced()).getTime()));
            } else {
                preparedStmt.setNull(2, java.sql.Types.TIMESTAMP);
            }
            if (cpE.getdDiscontinued() != null) {
                preparedStmt.setTimestamp(3, new Timestamp(Date.valueOf(cpE.getdDiscontinued()).getTime()));
            } else {
                preparedStmt.setNull(3, java.sql.Types.TIMESTAMP);
            }
            if (cpE.getManufacturer() != 0) {
                preparedStmt.setLong(4, cpE.getManufacturer());
            } else {
                preparedStmt.setNull(4, java.sql.Types.BIGINT);
            }
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

    /**
     * @param ids Ids
     * @throws ComputerDBException cdbex
     */
    public void delete(String ids) throws ComputerDBException {
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
            this.cpE = new ComputerMapperEntity(find(Integer.parseInt(idTab[0]))).getCpE();
            preparedStmt.setLong(1, cpE.getId());
            if (idTab.length > 1) {
                for (int i = 1; i < idTab.length; i++) {
                    this.cpE = new ComputerMapperEntity(find(Integer.parseInt(idTab[i]))).getCpE();
                    preparedStmt.setLong((i + 1), cpE.getId());
                }
            }

            preparedStmt.execute();
            logger.info("Computer " + cpE.getId() + " deleted");
        } catch (SQLException e) {
            logger.error("Computer not deleted ");
            e.printStackTrace();
            throw new ComputerDBException("Computer not deleted ", e);
        }
    }

    /**
     * @param cp Computer
     * @throws ComputerDBException cdbex
     */
    public void update(Computer cp) throws ComputerDBException {
        cpE = new ComputerMapperEntity(cp).getCpE();
        final String query = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";

        try (Connection conn = ConnectionDB.CONNECTION.getConn();
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                ) {
            preparedStmt.setString(1, cpE.getName());
            if (cpE.getdIntroduced() != null) {
                preparedStmt.setTimestamp(2, new Timestamp(Date.valueOf(cpE.getdIntroduced()).getTime()));
            } else {
                preparedStmt.setNull(2, java.sql.Types.TIMESTAMP);
            }
            if (cpE.getdDiscontinued() != null) {
                preparedStmt.setTimestamp(3, new Timestamp(Date.valueOf(cpE.getdDiscontinued()).getTime()));
            } else {
                preparedStmt.setNull(3, java.sql.Types.TIMESTAMP);
            }
            if (cpE.getManufacturer() != 0) {
                preparedStmt.setLong(4, cpE.getManufacturer());
            } else {
                preparedStmt.setNull(4, java.sql.Types.BIGINT);
            }
            preparedStmt.setLong(5, cpE.getId());
            preparedStmt.executeUpdate();
            logger.info("Computer " + cpE.getId() + " updated ");
        } catch (SQLException e) {
            logger.error("Computer not updated ");
            e.printStackTrace();
            throw new ComputerDBException("Computer not updated ", e);
        }
    }

    /**
     * @param id id
     * @throws ComputerDBException cdbex
     * @return Computer
     */
    public Computer find(long id) throws ComputerDBException {
        cpE = null;
        final String sql = "SELECT c.id, c.name, c.introduced, c.discontinued, c.company_id FROM computer c WHERE c.id=?;";
        try (Connection conn = ConnectionDB.CONNECTION.getConn();
                PreparedStatement preparedStmt = conn.prepareStatement(sql);
                ) {

            preparedStmt.setLong(1, id);
            preparedStmt.execute();
            try (ResultSet result = preparedStmt.getResultSet();) {
                while (result.next()) {
                    cpE = new ComputerEntity.Builder()
                            .id(result.getLong("c.id"))
                            .name(result.getString("c.name"))
                            .di(null)
                            .dd(null)
                            .manufacturer(result.getInt("c.company_id"))
                            .build();

                    if (result.getTimestamp("c.introduced") != null) {
                        cpE.setdIntroduced(result.getTimestamp("c.introduced").toLocalDateTime().toLocalDate());
                    }
                    if (result.getDate("c.discontinued") != null) {
                        cpE.setdDiscontinued(result.getTimestamp("c.discontinued").toLocalDateTime().toLocalDate());
                    }
                    logger.info("Computer " + cpE.getId() + " selected ");
                    return new ComputerMapperEntity(cpE).getCp();
                }
            }
        } catch (SQLException e) {
            logger.error("Computer not found ");
            throw new ComputerDBException("Computer not found ", e);
        }
        return new ComputerMapperEntity(cpE).getCp();

    }
    /**
     * @return List<Computer>
     * @throws ComputerDBException cdbex
     */
    public List<Computer> page() throws ComputerDBException {

        List<ComputerEntity> lcpE = new ArrayList<ComputerEntity>();
        final String sql = "SELECT c.id, c.name, c.introduced, c.discontinued, c.company_id FROM computer c LIMIT ? OFFSET ?;";

        try (Connection conn = ConnectionDB.CONNECTION.getConn();
                PreparedStatement preparedStmt = conn.prepareStatement(sql);
                ) {

            preparedStmt.setInt(1, Page.mAXNUMBEROFOBJECTS);
            preparedStmt.setInt(2, (Page.PAGE.getIndex()) * Page.mAXNUMBEROFOBJECTS);
            preparedStmt.execute();

            try (ResultSet result = preparedStmt.getResultSet();) {
                while (result.next()) {
                    cpE = new ComputerEntity.Builder().id(result.getLong("c.id")).name(result.getString("c.name")).di(null)
                            .dd(null).manufacturer(result.getInt("c.company_id")).build();

                    if (result.getDate("c.introduced") != null) {
                        cpE.setdIntroduced(result.getDate("c.introduced").toLocalDate());
                    }
                    if (result.getDate("c.discontinued") != null) {
                        cpE.setdDiscontinued(result.getDate("c.discontinued").toLocalDate());
                    }

                    lcpE.add(cpE);
                }
                logger.info("Computers selected ");
            }
        } catch (SQLException e) {
            logger.error("Computers not found ");
            throw new ComputerDBException("Computers not found ", e);
        }
        List<Computer> lcp = new ArrayList<Computer>();
        for (int i = 0; i < lcpE.size(); i++) {
            lcp.add(new ComputerMapperEntity(lcpE.get(i)).getCp());
        }
        return lcp;
    }

    /**
     * @return nbComputer
     */
    @Override
    public int count() throws ComputerDBException {

        final String sql = "SELECT count(*) FROM computer c;";
        try (Connection conn = ConnectionDB.CONNECTION.getConn();
                PreparedStatement preparedStmt = conn.prepareStatement(sql);
                ) {

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

    /**
     * @return nbComputer
     */
    @Override
    public int countLike(String search) throws ComputerDBException {

        final String sql = "SELECT count(*) FROM computer c WHERE c.name LIKE ? ;";
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
            logger.error("Computer not found ");
            throw new ComputerDBException("Computer not found ", e);
        }
        return -1;
    }

    /**
     * @return nbComputer
     */
    @Override
    public List<Computer> like(String search) throws ComputerDBException {

        List<ComputerEntity> lcpE = new ArrayList<ComputerEntity>();
        final String sql = "SELECT c.id, c.name, c.introduced, c.discontinued, c.company_id FROM computer c WHERE c.name LIKE ? LIMIT ? OFFSET ? ;";

        try (Connection conn = ConnectionDB.CONNECTION.getConn();
                PreparedStatement preparedStmt = conn.prepareStatement(sql);
                ) {

            preparedStmt.setString(1, "%" + search + "%");
            preparedStmt.setInt(2, Page.mAXNUMBEROFOBJECTS);
            preparedStmt.setInt(3, (Page.PAGE.getIndex()) * Page.mAXNUMBEROFOBJECTS);
            preparedStmt.execute();

            try (ResultSet result = preparedStmt.getResultSet();) {
                while (result.next()) {
                    cpE = new ComputerEntity.Builder().id(result.getLong("c.id")).name(result.getString("c.name")).di(null)
                            .dd(null).manufacturer(result.getInt("c.company_id")).build();

                    if (result.getDate("c.introduced") != null) {
                        cpE.setdIntroduced(result.getDate("c.introduced").toLocalDate());
                    }
                    if (result.getDate("c.discontinued") != null) {
                        cpE.setdDiscontinued(result.getDate("c.discontinued").toLocalDate());
                    }

                    lcpE.add(cpE);
                }
                logger.info("Computers selected ");
            }
        } catch (SQLException e) {
            logger.error("Computers not found ");
            throw new ComputerDBException("Computers not found ", e);
        }
        List<Computer> lcp = new ArrayList<Computer>();
        for (int i = 0; i < lcpE.size(); i++) {
            lcp.add(new ComputerMapperEntity(lcpE.get(i)).getCp());
        }
        return lcp;
    }

}