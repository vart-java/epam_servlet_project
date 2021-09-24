package com.artuhin.project.dao.daoimpl;

import com.artuhin.project.dao.Dao;
import com.artuhin.project.dao.TransactionManager;
import com.artuhin.project.dao.management.ConnectionProxy;
import com.artuhin.project.model.entity.Procedure;
import com.artuhin.project.model.entity.User;
import com.artuhin.project.util.rsparser.ResultSetParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class UserDao implements Dao<User> {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDao.class);
    private static final String SQL_EXCEPTION = "SQL exception in users DAO";
    private static UserDao instance;
    private ProcedureDao procedureDao = ProcedureDao.getInstance();

    private UserDao() {
    }

    public static synchronized UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }
        return instance;
    }

    @Override
    public User create(User user) {
        String createSql = "INSERT INTO users (login, password, role_id) VALUES (?, ?, ?)";
        long id = 0;
        try (ConnectionProxy connectionProxy = TransactionManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connectionProxy.prepareStatement(createSql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setLong(3, user.getRole().ordinal());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getLong(1);
            }
            user.setId(id);
            procedureDao.setMasterSkills(user.getId(), user.getSkills());
            user.setPassword(null);
        } catch (SQLException e) {
            LOGGER.error(SQL_EXCEPTION, e);
        }
        return user;
    }

    @Override
    public User get(long id) {
        User user = null;
        try (ConnectionProxy connectionProxy = TransactionManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connectionProxy
                     .prepareStatement("SELECT * FROM users WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            List<User> users = ResultSetParser.getInstance().usersParser(preparedStatement.executeQuery());
            user = users.stream().findAny().orElseThrow(() -> new RuntimeException("User with id: " + id + "is absent"));
            user.builder().skills(procedureDao.getSkillsByMasterId(id));
        } catch (SQLException e) {
            LOGGER.error(SQL_EXCEPTION, e);
        }
        return user;
    }

    @Override
    public boolean update(User user) {
        String updateSql = "UPDATE users SET login = ?, role_id = ?, rating = ?, recall_count = ? WHERE id = ?";
        try (ConnectionProxy connection = TransactionManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateSql)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setLong(2, user.getRole().ordinal()+1);
            preparedStatement.setDouble(3, user.getRating());
            preparedStatement.setInt(4, user.getRecallCount());
            preparedStatement.setDouble(5, user.getId());
            preparedStatement.executeUpdate();
            procedureDao.setMasterSkills(user.getId(), user.getSkills());
        } catch (SQLException e) {
            LOGGER.error(SQL_EXCEPTION, e);
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(long id) {
        String deleteSql = "DELETE FROM users WHERE id = ?";
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
    public List<User> getAll() {
        List<User> resultList = new ArrayList<>();
        String getAllSql = "SELECT * FROM users";
        try (ConnectionProxy connectionProxy = TransactionManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connectionProxy.prepareStatement(getAllSql)) {
            resultList = ResultSetParser.getInstance().usersParser(preparedStatement.executeQuery());
            resultList.forEach(u -> u.setSkills(procedureDao.getSkillsByMasterId(u.getId())));
        } catch (SQLException e) {
            LOGGER.error(SQL_EXCEPTION, e);
        }
        return resultList;
    }
}
