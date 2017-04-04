package com.excilys.formation.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.excilys.formation.util.PersistenceException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

//Singleton Connection
public enum ConnectionDB {
    CONNECTION;
    private Connection conn;
    HikariConfig config;
    HikariDataSource hs;
    private Logger logger = LogManager.getRootLogger();

    /**
     * @throws PersistenceException cdbexc
     */
    ConnectionDB() throws PersistenceException {
        try {
            final String resourceName = "hikari.properties";
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            Properties props = new Properties();
            try (InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
                props.load(resourceStream);
                config = new HikariConfig(props);

                config.setMaximumPoolSize(20);
                config.setMinimumIdle(5);
                config.setIdleTimeout(60 * 1000);
                config.setConnectionTimeout(1000);
                config.setMaxLifetime(287400);
                hs = new HikariDataSource(config);
            }
        } catch (IllegalArgumentException | IOException e) {
            logger.error("ConnectionDB : cannot be instanciated");
            throw new PersistenceException(e);
        }
    }
    /**
     * @return Connection conn;
     */
    public Connection getConn() {
        try {
            return hs.getConnection();
        } catch (SQLException e) {
            logger.error("Conn : cannot be instanciated");
            throw new PersistenceException(e);
        }
    }

}
