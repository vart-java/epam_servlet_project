package com.artuhin.project.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class TransactionManager {

    private final DbConnector dataSource = DbConnector.getInstance();
    private static final ThreadLocal<ConnectionProxy> currentConnection = new ThreadLocal<>();
    private static TransactionManager instance;
    private static Logger LOGGER = LogManager.getLogger(DbConnector.class);

    public static synchronized TransactionManager getInstance() {
        if (instance == null) {
            instance = new TransactionManager();
        }
        return instance;
    }

    private TransactionManager() {
    }

    ConnectionProxy getConnection() {
        if (currentConnection.get() == null) {
            return new ConnectionProxy(dataSource.getConnection());
        } else {
            return provideConnection();
        }

    }

    public ConnectionProxy beginTransaction() {

        if (currentConnection.get() != null) {
            throw new IllegalStateException();
        }
        return provideConnection();
    }

    public void commit() {

        if (currentConnection.get() == null) {
            throw new IllegalStateException();
        }

        currentConnection.get().setTransactionActive(false);

        try {
            currentConnection.get().commit();
        } catch (SQLException e) {
            LOGGER.error("Can`t connect to DB", e);
            try {
                LOGGER.error("Transaction is being rolled back");
                currentConnection.get().rollback();
            } catch (SQLException e1) {
                LOGGER.error("Failed to rollback dao.transaction \n", e1);
            }
        } finally {
            try {
                currentConnection.get().setAutoCommit(true);
                currentConnection.get().close();
            } catch (SQLException e) {
                LOGGER.error("Can`t connect to DB", e);
            }
        }

        currentConnection.set(null);
    }

    private ConnectionProxy provideConnection() {

        if (currentConnection.get() != null) {
            return currentConnection.get();
        }

        currentConnection.set(new ConnectionProxy(dataSource.getConnection()));
        currentConnection.get().setTransactionActive(true);

        try {
            currentConnection.get().setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.error("Can`t connect to DB", e);
        }
        return currentConnection.get();
    }
}
