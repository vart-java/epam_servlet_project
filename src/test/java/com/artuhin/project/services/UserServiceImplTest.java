package com.artuhin.project.services;

import com.artuhin.project.dao.daoimpl.ProcedureDao;
import com.artuhin.project.dao.daoimpl.UserDao;
import com.artuhin.project.model.entity.Procedure;
import com.artuhin.project.model.enums.Role;
import com.artuhin.project.model.entity.User;
import com.artuhin.project.services.servicesimpl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;

public class UserServiceImplTest {
    UserDao userDao = mock(UserDao.class);
    ProcedureDao procedureDao = mock(ProcedureDao.class);
    List<User> userList = new ArrayList<>();
    List<Procedure> procedureList = new ArrayList<>();
    Set<Procedure> procedureSet = new HashSet<>();
    UserServiceImpl userService = UserServiceImpl.getInstance();
    User master1 = new User();
    User master2 = new User();
    User client1 = new User();
    Procedure procedure1 = mock(Procedure.class);
    Procedure procedure2 = mock(Procedure.class);
    HashMap<Procedure, List<User>> procedureListHashMap = new HashMap<>();


    @Before
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        Field userDao1 = userService.getClass().getDeclaredField("userDao");
        userDao1.setAccessible(true);
        userDao1.set(userService, this.userDao);
        Field procedureDao1 = userService.getClass().getDeclaredField("procedureDao");
        procedureDao1.setAccessible(true);
        procedureDao1.set(userService, this.procedureDao);
        master1.setRating(9);
        master2.setRating(8);
        master1.setRole(Role.MASTER);
        master2.setRole(Role.MASTER);
        client1.setRole(Role.CLIENT);
        procedureSet.add(procedure1);
        procedureSet.add(procedure2);
        master1.setSkills(procedureSet);
        master2.setSkills(procedureSet);
        userList.add(master1);
        userList.add(master2);
        userList.add(client1);
        procedureList.add(procedure1);
        procedureList.add(procedure2);

        procedureListHashMap.put(procedure1, userList.stream().filter(m -> m.getRole().equals(Role.MASTER)).sorted(Comparator.comparingDouble(User::getId).reversed()).collect(Collectors.toList()));
        procedureListHashMap.put(procedure2, userList.stream().filter(m -> m.getRole().equals(Role.MASTER)).sorted(Comparator.comparingDouble(User::getId).reversed()).collect(Collectors.toList()));

        Mockito.when(this.userDao.create(any(User.class))).thenReturn(master1);
        Mockito.when(this.userDao.get(anyLong())).thenReturn(master1);
        Mockito.when(this.userDao.update(any(User.class))).thenReturn(true);
        Mockito.when(this.userDao.delete(anyLong())).thenReturn(true);
        Mockito.when(this.userDao.getAll()).thenReturn(userList);
        Mockito.when(this.procedureDao.create(any(Procedure.class))).thenReturn(procedure1);
        Mockito.when(this.procedureDao.get(anyLong())).thenReturn(procedure1);
        Mockito.when(this.procedureDao.update(any(Procedure.class))).thenReturn(true);
        Mockito.when(this.procedureDao.delete(anyLong())).thenReturn(true);
        Mockito.when(this.procedureDao.getAll()).thenReturn(procedureList);
    }

    @Test
    public void isUserCreated() {
        Assert.assertEquals(userService.createUser(master1), master1);
    }

    @Test
    public void isUserGet() {
        Assert.assertEquals(userService.getById(master1.getId()), master1);
    }

    @Test
    public void isGetAll() {
        Assert.assertEquals(userService.getAll(), userList);
    }

    @Test
    public void isDeleted() {
        Assert.assertTrue(userService.delete(master1.getId()));
    }

    @Test
    public void isGetAllMastersSortByRating() {
        Assert.assertEquals(userService.getAllMastersSortByRating(), userList.stream().filter(m -> m.getRole().equals(Role.MASTER)).sorted(Comparator.comparingDouble(User::getId).reversed()).collect(Collectors.toList()));
    }

    @Test
    public void isGetAllMastersBySkillSortByRating() {
        Assert.assertEquals(userService.getAllMastersBySkillsSortByRating(), procedureListHashMap);
    }

    @Test
    public void isRatingUpdated() {
        Assert.assertTrue(userService.updateRating(master1.getId(), 1));
    }

    @Test
    public void isRoleUpdated() {
        Assert.assertTrue(userService.updateRole(master1.getId(), master1.getRole()));
    }

    @Test
    public void isSkillAdded() {
        Assert.assertTrue(userService.addSkill(master1.getId(), procedure1.getId()));
    }

    @Test
    public void isSkillDeleted() {
        Assert.assertTrue(userService.deleteSkill(master1.getId(), procedure1.getId()));
    }

}
