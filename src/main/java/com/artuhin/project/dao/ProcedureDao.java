package com.artuhin.project.dao;

import com.artuhin.project.model.Procedure;
import com.artuhin.project.util.rsparser.ResultSetParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProcedureDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProcedureDao.class);
    private static final String SQL_EXCEPTION = "SQL exception procedure DAO";
    private static ProcedureDao instance;

    private ProcedureDao() {
    }

    public static synchronized ProcedureDao getInstance() {
        if (instance == null) {
            instance = new ProcedureDao();
        }
        return instance;
    }

    public List<Procedure> getAll() {
        List<Procedure> procedures = new ArrayList<>();
        String getAllSql = "SELECT * FROM procedures";
        try (ConnectionProxy connectionProxy = TransactionManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connectionProxy
                     .prepareStatement(getAllSql)) {
            procedures = ResultSetParser.getInstance().procedureParser(preparedStatement.executeQuery());
        } catch (SQLException e) {
            LOGGER.error(SQL_EXCEPTION, e);
        }
        return procedures;
    }

    public Procedure getProcedureByName(String name) {
        List<Procedure> procedures = new ArrayList<>();
        String getSql = "SELECT * FROM procedures WHERE name = ?";
        try (ConnectionProxy connectionProxy = TransactionManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connectionProxy
                     .prepareStatement(getSql)) {
            preparedStatement.setString(1, name);

            procedures = ResultSetParser.getInstance().procedureParser(preparedStatement.executeQuery());
        } catch (SQLException e) {
            LOGGER.error(SQL_EXCEPTION, e);
        }
        return procedures.get(0);
    }
}
