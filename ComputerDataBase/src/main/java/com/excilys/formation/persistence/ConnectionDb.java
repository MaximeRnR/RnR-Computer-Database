package com.excilys.formation.persistence;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.excilys.formation.util.PersistenceException;
import com.zaxxer.hikari.HikariDataSource;

//Singleton Connection
public class ConnectionDb {
    private Logger logger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
    private final ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    private HikariDataSource hs;
    /**
     * @throws PersistenceException cdbexc
     */
    ConnectionDb() throws PersistenceException {
            hs = (HikariDataSource) context.getBean("HikariDataSource");
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
