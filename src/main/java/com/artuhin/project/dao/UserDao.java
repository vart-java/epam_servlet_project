package com.artuhin.project.dao;

import com.artuhin.project.model.User;
import com.artuhin.project.util.rsparser.ResultSetParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class UserDao {
    private static final Logger LOGGER = LogManager.getLogger(UserDao.class);
    private static final String SQL_EXCEPTION = "SQL exception in users DAO";
    private static UserDao instance;

    private UserDao() {
    }

    public static synchronized UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }
        return instance;
    }

    public String create(User user) {
        if (null != get(user.getLogin())) {
            return "#userExists";
        }
        String createSql = "INSERT INTO users (role_name, login, password) VALUES (?, ?, ?)";
        String checkSql = "SELECT * FROM users WHERE login = ?";
        String name;
        try (ConnectionProxy connectionProxy = TransactionManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connectionProxy.prepareStatement(createSql);
             PreparedStatement preparedStatement1 = connectionProxy.prepareStatement(checkSql)) {
            preparedStatement.setString(1, user.getRole().toString().toLowerCase(Locale.ROOT));
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.executeUpdate();
            preparedStatement1.setString(1, user.getLogin());
            User user1 = ResultSetParser.getInstance().usersParser(preparedStatement1.executeQuery()).get(0);
            name = user1.getSimpleName();
        } catch (SQLException e) {
            LOGGER.error(SQL_EXCEPTION, e);
            return "#userExists";
        }
        return name;
    }

    public User get(String login) {
        User user = null;
        try (ConnectionProxy connectionProxy = TransactionManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connectionProxy
                     .prepareStatement("SELECT * FROM users WHERE login = ?")) {
            preparedStatement.setString(1, login);
            List<User> users = ResultSetParser.getInstance().usersParser(preparedStatement.executeQuery());
            if (!users.isEmpty()) {
                user = users.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean update(User user) {
        String updateSql = "UPDATE users SET password = ?, role_name = ?, rating = ?, recall_count = ?, specialization = ? WHERE login = ?";
        try (ConnectionProxy connection = TransactionManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateSql)) {
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getRole().toString().toLowerCase(Locale.ROOT));
            preparedStatement.setDouble(3, user.getRating());
            preparedStatement.setInt(4, user.getRecallCount());
            preparedStatement.setString(5, user.getSpecialization().getName());
            preparedStatement.setString(6, user.getLogin());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(SQL_EXCEPTION, e);
            return false;
        }
        return true;
    }

    public boolean delete(String login) {
        String deleteSql = "DELETE FROM users WHERE login = ?";
        try (ConnectionProxy connectionProxy = TransactionManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connectionProxy.prepareStatement(deleteSql)) {
            preparedStatement.setString(1, login);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(SQL_EXCEPTION, e);
            return false;
        }
        return true;
    }

    public List<User> getAll() {
        List<User> resultList = new ArrayList<>();
        String getAllSql = "SELECT * FROM users ORDER BY login";
        try (ConnectionProxy connectionProxy = TransactionManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connectionProxy.prepareStatement(getAllSql)) {
            resultList = ResultSetParser.getInstance().usersParser(preparedStatement.executeQuery());
        } catch (SQLException e) {
            LOGGER.error(SQL_EXCEPTION, e);
        }
        return resultList;
    }
}
