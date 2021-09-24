package com.artuhin.project.dao;


import com.artuhin.project.dao.management.ConnectionProxy;
import com.artuhin.project.dao.management.DbConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class TransactionManager {

    private final DbConnector dataSource = DbConnector.getInstance();
    private static final ThreadLocal<ConnectionProxy> currentConnection = new ThreadLocal<>();
    private static final String CAN_T_CONNECT_TO_DB = "Can`t connect to DB";
    private static TransactionManager instance;
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionManager.class);

    public static synchronized TransactionManager getInstance() {
        if (instance == null) {
            instance = new TransactionManager();
        }
        return instance;
    }

    private TransactionManager() {
    }

    public ConnectionProxy getConnection() {
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
            LOGGER.error(CAN_T_CONNECT_TO_DB, e);
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
                LOGGER.error(CAN_T_CONNECT_TO_DB, e);
            }
        }
        currentConnection.remove();
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
            LOGGER.error(CAN_T_CONNECT_TO_DB, e);
        }
        return currentConnection.get();
    }


}
