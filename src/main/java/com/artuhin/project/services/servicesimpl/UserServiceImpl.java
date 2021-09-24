package com.artuhin.project.services.servicesimpl;

import com.artuhin.project.dao.daoimpl.ProcedureDao;
import com.artuhin.project.dao.daoimpl.UserDao;
import com.artuhin.project.model.entity.Procedure;
import com.artuhin.project.model.enums.Role;
import com.artuhin.project.model.entity.User;
import com.artuhin.project.factory.DaoFactory;
import com.artuhin.project.services.UserService;
import com.artuhin.project.util.annotations.Transactional;

import java.util.*;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl instance;
    private UserDao userDao = DaoFactory.getInstance().getUsersDao();
    private ProcedureDao procedureDao = DaoFactory.getInstance().getProcedureDao();

    public static synchronized UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    private UserServiceImpl() {
    }

    @Override
    public User createUser(User user) {
        return userDao.create(user);
    }

    @Override
    public User getById(long id) {
        return userDao.get(id);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Transactional
    @Override
    public boolean delete(long id) {
        return userDao.delete(id);
    }

    @Override
    public List<User> getAllMastersSortByRating() {
        return userDao.getAll().stream().filter(m -> m.getRole().equals(Role.MASTER)).sorted(Comparator.comparingDouble(User::getRating).reversed()).collect(Collectors.toList());
    }

    @Override
    public HashMap<Procedure, List<User>> getAllMastersBySkillsSortByRating() {
        List<Procedure> procedures = procedureDao.getAll();
        List<User> masters = userDao.getAll().stream().filter(m -> m.getRole().equals(Role.MASTER)).sorted(Comparator.comparingDouble(User::getId).reversed()).collect(Collectors.toList());
        HashMap<Procedure, List<User>> procedureListHashMap = new HashMap<>();
        procedures.forEach(p -> procedureListHashMap.put(p, masters.stream().filter(m->m.getSkills().contains(p)).collect(Collectors.toList())));
        return procedureListHashMap;
    }

    @Transactional
    @Override
    public boolean updateRating(long id, int recall) {
        User user = userDao.get(id);
        user.updateRating(recall);
        return userDao.update(user);
    }

    @Transactional
    @Override
    public boolean updateRole(long id, Role role) {
        User user = userDao.get(id);
        user.setRole(role);
        return userDao.update(user);
    }

    @Transactional
    @Override
    public boolean addSkill(long id, long procedureId) {
        User user = userDao.get(id);
        Set<Procedure>skills = user.getSkills();
        skills.add(procedureDao.get(procedureId));
        user.setSkills(skills);
        return userDao.update(user);
    }

    @Transactional
    @Override
    public boolean deleteSkill(long masterId, long procedureId) {
        User user = userDao.get(masterId);
        Set<Procedure>skills = user.getSkills();
        skills.remove(procedureDao.get(procedureId));
        user.setSkills(skills);
        return userDao.update(user);
    }
}
