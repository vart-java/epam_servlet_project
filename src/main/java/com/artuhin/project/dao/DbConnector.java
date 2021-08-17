package com.artuhin.project.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnector {
    private static final Logger LOGGER = LogManager.getLogger(DbConnector.class);
    private static final DbConnector instance = new DbConnector();
    private ComboPooledDataSource comboPooledDataSource;

    private DbConnector() {
        initDataSource();
    }

    public static DbConnector getInstance() {
        return instance;
    }

    private void initDataSource() {
        comboPooledDataSource = new ComboPooledDataSource();
        Properties properties = new Properties();

        try (InputStream is = getClass().getResourceAsStream("/db_connection.properties")) {
            properties.load(is);
            comboPooledDataSource.setDriverClass(properties.getProperty("jdbc_driver"));
        } catch (PropertyVetoException | IOException e) {
            LOGGER.error("cant getByID properties", e);
        }

        comboPooledDataSource.setJdbcUrl(properties.getProperty("url"));
        comboPooledDataSource.setUser(properties.getProperty("user"));
        comboPooledDataSource.setPassword(properties.getProperty("password"));
    }

    public Connection getConnection() {
        Connection connection = null;

        try {
            connection = comboPooledDataSource.getConnection();
        } catch (SQLException e) {
            LOGGER.error("SQL-exception!", e);
        }

        return connection;
    }
}
