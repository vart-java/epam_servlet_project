package com.artuhin.project.dao.daoimpl;

import com.artuhin.project.dao.Dao;
import com.artuhin.project.dao.TransactionManager;
import com.artuhin.project.dao.management.ConnectionProxy;
import com.artuhin.project.model.entity.Procedure;
import com.artuhin.project.util.rsparser.ResultSetParser;
import org.postgresql.util.PGInterval;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class ProcedureDao implements Dao<Procedure> {
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


    @Override
    public Procedure create(Procedure procedure) {
        String createSql = "INSERT INTO procedures (name, duration) VALUES (?, ?)";
        long id = -1;
        try (ConnectionProxy connectionProxy = TransactionManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connectionProxy.prepareStatement(createSql, Statement.RETURN_GENERATED_KEYS)) {
            PGInterval pgInterval = new PGInterval();
            pgInterval.setSeconds(procedure.getDuration());
            preparedStatement.setString(1, procedure.getName());
            preparedStatement.setObject(2, pgInterval);
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getLong(1);
            }
            procedure.setId(id);
        } catch (SQLException e) {
            LOGGER.error(SQL_EXCEPTION, e);
        }
        return procedure;
    }

    @Override
    public Procedure get(long id) {
        Procedure procedure = null;
        try (ConnectionProxy connectionProxy = TransactionManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connectionProxy
                     .prepareStatement("SELECT * FROM procedures WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            List<Procedure> procedures = ResultSetParser.getInstance().procedureParser(preparedStatement.executeQuery());
            procedure = procedures.stream().findAny().orElseThrow(() -> new RuntimeException("Procedure with id: " + id + "is absent"));
        } catch (SQLException e) {
            LOGGER.error(SQL_EXCEPTION, e);
        }
        return procedure;
    }

    @Override
    public boolean update(Procedure procedure) {
        String updateSql = "UPDATE procedures SET name = ?, duration = ? WHERE id = ?";
        try (ConnectionProxy connection = TransactionManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateSql)) {
            PGInterval pgInterval = new PGInterval();
            pgInterval.setSeconds(procedure.getDuration());
            preparedStatement.setString(1, procedure.getName());
            preparedStatement.setObject(2, pgInterval);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(SQL_EXCEPTION, e);
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(long id) {
        String deleteSql = "DELETE FROM procedures WHERE id = ?";
        try (ConnectionProxy connectionProxy = TransactionManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connectionProxy.prepareStatement(deleteSql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(SQL_EXCEPTION, e);
            return false;
        }
        return true;
    }

    @Override
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

    public Set<Procedure> getSkillsByMasterId(Long id) {
        Set<Procedure> procedures = new HashSet<>();
        String getSql = "SELECT * FROM users_procedures WHERE user_id = ?";
        try (ConnectionProxy connectionProxy = TransactionManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connectionProxy
                     .prepareStatement(getSql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                procedures.add(get(resultSet.getLong("procedure_id")));
            }
        } catch (SQLException e) {
            LOGGER.error(SQL_EXCEPTION, e);
        }
        return procedures;
    }

    public boolean setMasterSkills(long masterId, Set<Procedure> skills) {
        deleteAllMasterSkills(masterId);
        String createSkillsSql = "INSERT INTO users_procedures (user_id, procedure_id) VALUES (?, ?)";
        for (Procedure procedure : skills) {
            try (ConnectionProxy connectionProxy = TransactionManager.getInstance().getConnection();
                 PreparedStatement preparedStatement = connectionProxy.prepareStatement(createSkillsSql)) {
                preparedStatement.setLong(1, masterId);
                preparedStatement.setLong(2, procedure.getId());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                LOGGER.error(SQL_EXCEPTION, e);
                return false;
            }
        }
        return true;
    }

    private boolean deleteAllMasterSkills(long masterId){
        String deleteSkillsSql = "DELETE FROM users_procedures WHERE user_id = ?";
            try (ConnectionProxy connectionProxy = TransactionManager.getInstance().getConnection();
                 PreparedStatement preparedStatement = connectionProxy.prepareStatement(deleteSkillsSql)) {
                preparedStatement.setLong(1, masterId);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                LOGGER.error(SQL_EXCEPTION, e);
                return false;
            }
        return true;
    }
}



