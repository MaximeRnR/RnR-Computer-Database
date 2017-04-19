package com.excilys.formation.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.util.PersistenceException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

//Singleton Connection
public enum ConnectionDB {
    INSTANCE;
    HikariConfig config;
    HikariDataSource hs;
    private Logger logger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
    private final ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

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
                config.setMaximumPoolSize(100);
                config.setMinimumIdle(5);
                config.setIdleTimeout(60 * 1000);
                config.setConnectionTimeout(1000);
                config.setMaxLifetime(287400);
                hs = new HikariDataSource(config);
            }
        } catch (IOException e) {
            logger.error("ConnectionDB : " + e.getMessage());
            throw new PersistenceException(e);
        }
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                hs.close();
            }
        });
    }
    /**
     * @return Connection conn;
     */
    public Connection getConn() {


        try {
            if (threadLocal.get() == null || threadLocal.get().isClosed()) {
                threadLocal.set(hs.getConnection());

            }
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
        return threadLocal.get();
    }

}
