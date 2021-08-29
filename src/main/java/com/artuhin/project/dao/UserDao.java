package com.artuhin.project.dao;

import com.artuhin.project.model.Procedure;
import com.artuhin.project.model.Role;
import com.artuhin.project.model.User;
import com.artuhin.project.util.rsparser.WithoutReflectionParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class UserDao {

    private static Logger LOGGER = LogManager.getLogger(UserDao.class);
    private final String SQL_EXCEPTION = "SQL exception user DAO";

    public String create(User user) {
        if (chekingIfUserExisting(user.getLogin(), user.getPassword())) {
            return "#userExists";
        }
        String CREATE_SQL = "INSERT INTO users (role_name, login, password) VALUES (?, ?, ?)";
        String CHECK_SQL = "SELECT * FROM users WHERE login = ?";
        String name;
        try (ConnectionProxy connectionProxy = TransactionManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connectionProxy.prepareStatement(CREATE_SQL);
             PreparedStatement preparedStatement1 = connectionProxy.prepareStatement(CHECK_SQL)) {
            preparedStatement.setString(1, user.getRole().toString().toLowerCase(Locale.ROOT));
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.executeUpdate();
            preparedStatement1.setString(1, user.getLogin());
            User user1 = WithoutReflectionParser.getInstance().usersParser(preparedStatement1.executeQuery()).get(0);
            name = user1.getSimpleName();
        } catch (SQLException e) {
            LOGGER.error(SQL_EXCEPTION, e);
            return "#userExists";
        }
        return name;
    }

    public boolean update(User user) {
        String UPDATE_SQL = "UPDATE users SET login = ?, password = ?, role_name = ?";
        try (ConnectionProxy connection = TransactionManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole().toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(SQL_EXCEPTION, e);
            return false;
        }
        return true;
    }

    public boolean updateRole(String login, Role role) {
        String UPDATE_SQL = "UPDATE users SET role_name = ? WHERE login =?";
        try (ConnectionProxy connection = TransactionManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, role.toString().toLowerCase(Locale.ROOT));
            preparedStatement.setString(2, login);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(SQL_EXCEPTION, e);
            return false;
        }
        return true;
    }

    public boolean delete(String login) {
        String DELETE_SQL = "DELETE FROM users WHERE login = ?";
        try (ConnectionProxy connectionProxy = TransactionManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connectionProxy.prepareStatement(DELETE_SQL)) {
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
        String GET_ALL_SQL = "SELECT * FROM users ORDER BY login";
        try (ConnectionProxy connectionProxy = TransactionManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connectionProxy.prepareStatement(GET_ALL_SQL)) {
            resultList = WithoutReflectionParser.getInstance().usersParser(preparedStatement.executeQuery());
        } catch (SQLException e) {
            LOGGER.error(SQL_EXCEPTION, e);
        }
        return resultList;
    }

    public boolean clearAll() {
        try (ConnectionProxy connectionProxy = TransactionManager.getInstance().getConnection();
             Statement statement = connectionProxy.createStatement()) {
            String DELETE_ALL_SQL = "DELETE * FROM users";
            statement.executeUpdate(DELETE_ALL_SQL);
        } catch (SQLException e) {
            LOGGER.error(SQL_EXCEPTION, e);
            return false;
        }
        return true;
    }

    public List<User> getAllMastersSortByRating() {
        List<User> resultList = new ArrayList<>();
        String GET_MASTERS_BY_RATING_SQL = "SELECT * FROM users WHERE role_name = ? ORDER BY rating DESC";
        try (ConnectionProxy connectionProxy = TransactionManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connectionProxy.prepareStatement(GET_MASTERS_BY_RATING_SQL)) {
            preparedStatement.setString(1, Role.MASTER.toString().toLowerCase(Locale.ROOT));
            resultList = WithoutReflectionParser.getInstance().usersParser(preparedStatement.executeQuery());
        } catch (SQLException e) {
            LOGGER.error(SQL_EXCEPTION, e);
        }
        return resultList;
    }

    public List<List<User>> getAllMastersBySpecializationSortByRating() {
        List<List<User>> resultList = new ArrayList<>();
        String GET_MASTERS_BY_RATING_SQL = "SELECT * FROM users WHERE role_name = ? ORDER BY rating DESC";
        String GET_PROCEDURES_SQL = "SELECT * FROM procedures";
        try (ConnectionProxy connectionProxy = TransactionManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connectionProxy.prepareStatement(GET_MASTERS_BY_RATING_SQL);
             PreparedStatement preparedStatement1 = connectionProxy.prepareStatement(GET_PROCEDURES_SQL)) {
            List<Procedure> procedureList = WithoutReflectionParser.getInstance().procedureParser(preparedStatement1.executeQuery());
            preparedStatement.setString(1, Role.MASTER.toString().toLowerCase(Locale.ROOT));
            List<User> userList = WithoutReflectionParser.getInstance().usersParser(preparedStatement.executeQuery());
            for (Procedure p : procedureList) {
                List<User> users = new ArrayList<>();
                for (User user : userList) {
                    if (user.getSpecialization().getName().equals(p.getName())) {
                        users.add(user);
                    }
                }
                resultList.add(users);
            }
        } catch (SQLException e) {
            LOGGER.error(SQL_EXCEPTION, e);
        }
        return resultList;
    }

    public List<User> getMastersByRating(double rating) {
        List<User> resultList = new ArrayList<>();
        String GET_MASTERS_BY_RATING_SQL = "SELECT * FROM users WHERE role_name = ? AND rating = ? ORDER BY login";
        try (ConnectionProxy connectionProxy = TransactionManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connectionProxy.prepareStatement(GET_MASTERS_BY_RATING_SQL)) {
            preparedStatement.setString(1, Role.MASTER.toString());
            preparedStatement.setDouble(2, rating);
            resultList = WithoutReflectionParser.getInstance().usersParser(preparedStatement.executeQuery());
        } catch (SQLException e) {
            LOGGER.error(SQL_EXCEPTION, e);
        }
        return resultList;
    }

    public List<User> getAllMastersSortByLogins() {
        List<User> resultList = new ArrayList<>();
        String GET_MASTERS_BY_LOGINS_SQL = "SELECT * FROM users WHERE role_name = ? ORDER BY login";
        try (ConnectionProxy connectionProxy = TransactionManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connectionProxy.prepareStatement(GET_MASTERS_BY_LOGINS_SQL)) {
            preparedStatement.setString(1, Role.MASTER.toString());
            resultList = WithoutReflectionParser.getInstance().usersParser(preparedStatement.executeQuery());
        } catch (SQLException e) {
            LOGGER.error(SQL_EXCEPTION, e);
        }
        return resultList;
    }


    public boolean chekingIfUserExisting(String login, String password) {
        boolean result = false;
        try (ConnectionProxy connectionProxy = TransactionManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connectionProxy
                     .prepareStatement("SELECT * FROM users WHERE login = ? AND password = ?")) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            result = preparedStatement.executeQuery().next();
        } catch (SQLException e) {
            LOGGER.error(SQL_EXCEPTION, e);
        }
        return result;
    }

    public User getByLogin(String login) {
        User user = new User();
        try (ConnectionProxy connectionProxy = TransactionManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connectionProxy
                     .prepareStatement("SELECT * FROM users WHERE login = ?")) {
            preparedStatement.setString(1, login);
            List<User> users = WithoutReflectionParser.getInstance().usersParser(preparedStatement.executeQuery());
            if (!users.isEmpty()) {
                user = users.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean updateRating(String login, int recall) {
        User user = getByLogin(login);
        double rate = user.getRating();
        int count = user.getRecallCount();
        double newRate;
        if (rate == 0 || count == 0) {
            newRate = recall;
            count = 1;
        } else {
            newRate = (rate * count + recall) / (++count);
        }
        String UPDATE_SQL = "UPDATE users SET rating = ?, recall_count = ? WHERE login = ?";
        try (ConnectionProxy connection = TransactionManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setDouble(1, newRate);
            preparedStatement.setInt(2, count);
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(SQL_EXCEPTION, e);
            return false;
        }
        return true;
    }

    public List<User> getAllByRole(String role) {
        List<User> users = new ArrayList<>();
        try (ConnectionProxy connectionProxy = TransactionManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connectionProxy
                     .prepareStatement("SELECT * FROM users WHERE role_name = ?")) {
            preparedStatement.setString(1, role.toLowerCase(Locale.ROOT));
            users = WithoutReflectionParser.getInstance().usersParser(preparedStatement.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public List<List<User>> getAllUserSortByRole() {
        List<List<User>> resultList = new ArrayList<>();
        String GET_ROLES_SQL = "SELECT * FROM role";
        try (ConnectionProxy connectionProxy = TransactionManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connectionProxy.prepareStatement(GET_ROLES_SQL)) {
            List<String> roleList = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            try {
                while (resultSet.next()) {
                    String roleName = resultSet.getString("name");
                    roleList.add(roleName);
                }
            } catch (SQLException e) {
                LOGGER.error("SQL exception from parser", e);
            }
            for (String s : roleList) {
                List<User> users = getAllByRole(s);
                resultList.add(users);
            }
        } catch (SQLException e) {
            LOGGER.error(SQL_EXCEPTION, e);
        }
        return resultList;
    }
}
