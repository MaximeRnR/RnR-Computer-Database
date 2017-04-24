package com.excilys.formation.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.excilys.formation.model.Company;
import com.excilys.formation.model.Computer;


public class ComputerRowMapper implements RowMapper<Computer> {
    /**
     * @param result result
     * @param rowNum rowNum
     * @throws SQLException SQLEXC
     * @return computer computer
     */
    public Computer mapRow(ResultSet result, int rowNum) throws SQLException {

        Computer.Builder computerBuild = new Computer.Builder()
                .id(result.getLong("cp.id"))
                .name(result.getString("cp.name"))
                .di(null)
                .dd(null)
                .cy(new Company(result.getInt("cy.id"), result.getString("cy.name")));

        if (result.getTimestamp("cp.introduced") != null) {
            computerBuild.di(result.getTimestamp("cp.introduced").toLocalDateTime().toLocalDate());
        }
        if (result.getTimestamp("cp.discontinued") != null) {
            computerBuild.dd(result.getTimestamp("cp.discontinued").toLocalDateTime().toLocalDate());
        }
        Computer computer = computerBuild.build();
        return computer;
    }

}
