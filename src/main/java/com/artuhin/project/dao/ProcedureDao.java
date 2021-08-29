package com.artuhin.project.dao;

import com.artuhin.project.model.Procedure;
import com.artuhin.project.util.rsparser.WithoutReflectionParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProcedureDao {
    private static Logger LOGGER = LogManager.getLogger(ProcedureDao.class);
    private final String SQL_EXCEPTION = "SQL exception procedure DAO";

    public List<Procedure> getAll() {
        List<Procedure> procedures = new ArrayList<>();
        String GET_SQL = "SELECT * FROM procedures";
        try (ConnectionProxy connectionProxy = TransactionManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connectionProxy
                     .prepareStatement(GET_SQL)) {
            procedures = WithoutReflectionParser.getInstance().procedureParser(preparedStatement.executeQuery());
        } catch (SQLException e) {
            LOGGER.error(SQL_EXCEPTION, e);
        }
        return procedures;
    }

    public Procedure getProcedureByName(String name) {
        List<Procedure> procedures = new ArrayList<>();
        String GET_SQL = "SELECT * FROM procedures WHERE name = ?";
        try (ConnectionProxy connectionProxy = TransactionManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connectionProxy
                     .prepareStatement(GET_SQL)) {
            preparedStatement.setString(1, name);

            procedures = WithoutReflectionParser.getInstance().procedureParser(preparedStatement.executeQuery());
        } catch (SQLException e) {
            LOGGER.error(SQL_EXCEPTION, e);
        }
        return procedures.get(0);
    }
}
