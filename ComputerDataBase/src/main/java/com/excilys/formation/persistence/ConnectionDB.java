package com.excilys.formation.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.excilys.formation.util.ComputerDBException;

//Singleton Connection
public enum ConnectionDB {
    CONNECTION;
    private Connection conn;

    // Constructor privé
    /**
     * @throws ComputerDBException cdbex
     */
     ConnectionDB() throws ComputerDBException {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String url = new String(
                    "jdbc:mysql://localhost:3306/computer-database-db-test" + "?zeroDateTimeBehavior=convertToNull");
            this.conn = DriverManager.getConnection(url, "admincdb", "qwerty1234");
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new ComputerDBException("ConnectionDB can not be instantiated", e);
        }
    }

    public Connection getConn() {
        return conn;
    }

}
