package com.artuhin.project.services;

import com.artuhin.project.model.entity.Procedure;
import com.artuhin.project.model.enums.Role;
import com.artuhin.project.model.entity.User;
import com.artuhin.project.util.annotations.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public interface UserService extends Service {

    User createUser(User user);

    User getById(long id);

    List<User> getAll();

    boolean delete(long id);

    List<User> getAllMastersSortByRating();

    HashMap<Procedure, List<User>> getAllMastersBySkillsSortByRating();

    boolean updateRating(long id, int recall);

    boolean updateRole(long id, Role role);

    @Transactional
    boolean addSkill(long id, long procedureId);

    @Transactional
    boolean deleteSkill(long masterId, long procedureId);
}
