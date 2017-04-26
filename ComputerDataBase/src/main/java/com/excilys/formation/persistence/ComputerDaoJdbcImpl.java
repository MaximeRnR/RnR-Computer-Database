package com.excilys.formation.persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.excilys.formation.model.Computer;
import com.excilys.formation.ui.Page;
import com.zaxxer.hikari.HikariDataSource;

@Repository
public class ComputerDaoJdbcImpl implements ComputerDaoJdbc {
    final String createQuery = "insert into computer(name,introduced,discontinued,company_id)values (?,?,?,?)";
    final String findByIdQuery = "select cp.id, cp.name, cp.introduced, cp.discontinued, cy.id, cy.name from computer cp "
            + "left join company cy on cp.company_id = cy.id where cp.id=?";
    final String getPageOfComputerQuery = "select cp.id, cp.name, cp.introduced, cp.discontinued, cy.id, cy.name from computer as cp "
            + "left join company as cy on cp.company_id = cy.id LIMIT ? OFFSET ?;";
    final String countQuery = "SELECT count(id) FROM computer c;";
    final String getPageOfComputerByNameQuery = "select cp.id, cp.name, cp.introduced, cp.discontinued, cy.id, cy.name from computer cp USE INDEX(ix_computer_name)"
            + "left join company cy on cp.company_id = cy.id WHERE cp.name LIKE ? LIMIT ? OFFSET ? ;";
    final String updateQuery = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";
    final String countByNameQuery = "SELECT count(c.id) FROM computer c WHERE c.name LIKE ? ;";
    final String getPageOfComputerByCompanyNameQuery = "SELECT cp.id, cp.name, cp.introduced, cp.discontinued, cy.id, cy.name FROM computer cp JOIN company cy on cp.company_id = cy.id WHERE cy.name LIKE ? LIMIT ? OFFSET ? ;";
    final String countByCompanyNameQuery = "SELECT count(c.id) FROM computer c JOIN company cy on c.company_id = cy.id WHERE cy.name LIKE ? ;";
    private Logger logger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);


    @Autowired
    private HikariDataSource hs;
    private JdbcTemplate jdbcTemplate;
    private ComputerRowMapper computerRowMapper = new ComputerRowMapper();


    public void setHs(HikariDataSource hs) {
        this.hs = hs;
    }

    @Override
    public long createComputer(Computer computer) {

        jdbcTemplate = new JdbcTemplate(hs);
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStmt = connection.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS);
                ComputerDaoJdbcImpl.this.prepareComputer(computer, preparedStmt);
                return preparedStmt;
            }
        }, holder);
        Long generatedKey = holder.getKey().longValue();
        return generatedKey;
    }

    @Override
    public Computer findById(long id) {
        jdbcTemplate = new JdbcTemplate(hs);
        Computer computer = (Computer) jdbcTemplate.queryForObject(
                findByIdQuery,
                new Object[] {id},
                computerRowMapper);
        return computer;

    }

    @Override
    public boolean delete(List<Long> idTab) {

        jdbcTemplate = new JdbcTemplate(hs);
        jdbcTemplate.update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {

                String deleteQuery = "DELETE FROM computer WHERE id = ?";
                if (idTab.size() > 1) {
                    for (int i = 0; i < idTab.size() - 1; i++) {
                        deleteQuery = deleteQuery + " OR id = ?";
                    }
                }
                PreparedStatement preparedStmt = connection.prepareStatement(deleteQuery);
                Computer computer = new Computer.Builder().id(idTab.get(0)).build();
                preparedStmt.setLong(1, computer.getId());
                if (idTab.size() > 1) {
                    for (int i = 1; i < idTab.size(); i++) {
                        computer.setId(idTab.get(i));
                        preparedStmt.setLong((i + 1), computer.getId());
                    }
                }
                return preparedStmt;
            }
        });
        return true;
    }

    @Override
    public void update(Computer computer) {
        jdbcTemplate = new JdbcTemplate(hs);
        jdbcTemplate.update(
                updateQuery,
                new Object[] {computer.getName(),
                        computer.getdIntroduced(),
                        computer.getdDiscontinued(),
                        computer.getCy().getId(),
                        computer.getId()});
    }

    @Override
    public int getCountOfAllComputers() {
        jdbcTemplate = new JdbcTemplate(hs);
        return jdbcTemplate.queryForObject(countQuery, Integer.class);
    }

    @Override
    public List<Computer> getPageOfComputers(Page page) {
        jdbcTemplate = new JdbcTemplate(hs);
        List<Computer> computers = jdbcTemplate.query(getPageOfComputerQuery,
                new Object[] {page.maxNumberOfObject,
                        (page.getIndex()) * page.maxNumberOfObject},
        computerRowMapper);
        return computers;
    }

    @Override
    public int getCountOfComputersByName(String search) {
        jdbcTemplate = new JdbcTemplate(hs);
        return jdbcTemplate.queryForObject(countByNameQuery,
                new Object[] {search + "%"},
                Integer.class);
    }

    @Override
    public int getCountOfComputersByCompanyName(String search) {
        jdbcTemplate = new JdbcTemplate(hs);
        return jdbcTemplate.queryForObject(countByCompanyNameQuery,
                new Object[] {search + "%"},
                Integer.class);
    }


    @Override
    public List<Computer> getPageOfComputersByName(String search, Page page) {

        jdbcTemplate = new JdbcTemplate(hs);
        List<Computer> computers = jdbcTemplate.query(getPageOfComputerByNameQuery,
                new Object[] {search + "%",
                        page.maxNumberOfObject,
                        (page.getIndex()) * page.maxNumberOfObject},
        computerRowMapper);
        return computers;

    }

    @Override
    public List<Computer> getPageOfComputersByCompanyName(String search, Page page) {
        jdbcTemplate = new JdbcTemplate(hs);
        List<Computer> computers = jdbcTemplate.query(getPageOfComputerByCompanyNameQuery,
                new Object[] {search + "%",
                        page.maxNumberOfObject,
                        (page.getIndex()) * page.maxNumberOfObject},
        computerRowMapper);
        return computers;
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
