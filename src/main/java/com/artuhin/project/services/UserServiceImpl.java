package com.artuhin.project.services;

import com.artuhin.project.dao.UserDao;
import com.artuhin.project.model.Procedure;
import com.artuhin.project.model.Role;
import com.artuhin.project.model.User;
import com.artuhin.project.factory.DaoFactory;
import com.artuhin.project.util.annotations.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl instance;
    private UserDao userDao = DaoFactory.getInstance().getUsersDao();

    public static synchronized UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    private UserServiceImpl() {
    }

    @Transactional
    @Override
    public String createUser(User user) {
        return userDao.create(user);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Transactional
    @Override
    public boolean delete(String login) {
        return userDao.delete(login);
    }

    @Override
    public List<User> getAllMastersSortByRating() {
        return userDao.getAll().stream().filter(m -> m.getRole().equals(Role.MASTER)).sorted(Comparator.comparingDouble(User::getRating)).collect(Collectors.toList());
    }

    @Override
    public List<List<User>> getAllMastersBySpecilizationSortByRating() {
        List<Procedure> procedures = DaoFactory.getInstance().getProcedureDao().getAll();
        List<User> masters = userDao.getAll().stream().filter(m -> m.getRole().equals(Role.MASTER)).collect(Collectors.toList());
        List<List<User>> sortMasters = new ArrayList<>();
        procedures.forEach(p -> sortMasters.add(masters.stream().filter(m -> m.getSpecialization().getName().equals(p.getName())).sorted(Comparator.comparingDouble(User::getRating).reversed()).collect(Collectors.toList())));
        return sortMasters;
    }

    @Transactional
    @Override
    public boolean updateRating(String login, int recall) {
        User user = userDao.get(login);
        user.updateRating(recall);
        return userDao.update(user);
    }

    @Transactional
    @Override
    public boolean updateRole(String login, Role role) {
        User user = userDao.get(login);
        user.setRole(role);
        return userDao.update(user);
    }

    @Override
    public List<List<User>> getAllSortByRole() {
        List<User> users = userDao.getAll();
        List<List<User>> sortUsers = new ArrayList<>();
        for (Role p : Role.values()) {
            sortUsers.add(users.stream().filter(u -> u.getRole().equals(p)).collect(Collectors.toList()));
        }
        return sortUsers;
    }

    @Transactional
    @Override
    public boolean updateSpecialization(String login, String specialization) {
        User user = userDao.get(login);
        user.setSpecialization(new Procedure(specialization));
        return userDao.update(user);
    }

    @Override
    public User getByLogin(String login) {
        return userDao.get(login);
    }
}
