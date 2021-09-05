package com.artuhin.project.services;

import com.artuhin.project.dao.UserDao;
import com.artuhin.project.model.Procedure;
import com.artuhin.project.model.Role;
import com.artuhin.project.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

public class UserServiceImplTest {
    UserDao userDao = mock(UserDao.class);
    User user = new User();
    List<User> userList = new ArrayList<>();
    UserServiceImpl userService = UserServiceImpl.getInstance();
    List<Procedure>procedures = new ArrayList<>();
    User master1 = new User();
    User master2 = new User();
    User master3 = new User();


    @Before
    public void setUp() throws NoSuchFieldException, IllegalAccessException {

        Field userDao1 = userService.getClass().getDeclaredField("userDao");
        userDao1.setAccessible(true);
        userDao1.set(userService, this.userDao);
        user.setLogin("test@mail.com");
        user.setRole(Role.MASTER);
        user.setSpecialization(new Procedure("hair_coloring"));
        user.setPassword("password");
        user.setRecallCount(1);
        user.setRating(10);
        userList.add(user);
        Mockito.when(this.userDao.get(any())).thenReturn(user);
        Mockito.when(this.userDao.getAll()).thenReturn(userList);
        Mockito.when(this.userDao.update(any())).thenReturn(true);
        Mockito.when(this.userDao.delete(any())).thenReturn(true);
        Mockito.when(this.userDao.create(any())).thenReturn(user.getLogin());

        master1.setRole(Role.MASTER);
        master2.setRole(Role.MASTER);
        master3.setRole(Role.MASTER);
        master1.setSpecialization((new Procedure("hair_coloring")));
        master2.setSpecialization((new Procedure("nail_staining")));
        master3.setSpecialization((new Procedure("nail_staining")));
        master1.setRating(9);
        master2.setRating(8);
        master3.setRating(7);
        userList.add(master1);
        userList.add(master2);
        userList.add(master3);
        procedures.add(new Procedure("hair_coloring"));
        procedures.add(new Procedure("nail_staining"));
        procedures.add(new Procedure("eyelash_extension"));
        procedures.add(new Procedure("face_massage"));

    }

    @Test
    public void isUserCreated(){
        Assert.assertEquals(userService.createUser(user), user.getLogin());
    }

    @Test
    public void isGetAll(){
        Assert.assertEquals(userService.getAll(), userList);
    }

    @Test
    public void isDeleted(){
        Assert.assertTrue(userService.delete(user.getLogin()));
    }

    @Test
    public void isGetAllMastersSortByRating(){
        Assert.assertEquals(userService.getAllMastersSortByRating(), userList.stream().filter(m -> m.getRole().equals(Role.MASTER)).sorted(Comparator.comparingDouble(User::getRating)).collect(Collectors.toList()));
    }

    @Test
    public void isGetAllMastersBySpecilizationSortByRating(){
        List<User> masters = userList.stream().filter(m -> m.getRole().equals(Role.MASTER)).collect(Collectors.toList());
        List<List<User>> sortMasters = new ArrayList<>();
        procedures.forEach(p -> sortMasters.add(masters.stream().filter(m -> m.getSpecialization().getName().equals(p.getName())).sorted(Comparator.comparingDouble(User::getRating).reversed()).collect(Collectors.toList())));
        Assert.assertEquals(userService.getAllMastersBySpecilizationSortByRating(), sortMasters);
    }

    @Test
    public void isRatingUpdated(){
        Assert.assertTrue(userService.updateRating(user.getLogin(), 1));
    }

    @Test
    public void isRoleUpdated(){
        Assert.assertTrue(userService.updateRole(user.getLogin(), user.getRole()));
    }

    @Test
    public void isGetAllSortByRole(){
        List<User> users = userDao.getAll();
        List<List<User>> sortUsers = new ArrayList<>();
        for (Role p : Role.values()) {
            sortUsers.add(users.stream().filter(u -> u.getRole().equals(p)).collect(Collectors.toList()));
        }
        Assert.assertEquals(userService.getAllSortByRole(), sortUsers);
    }




}
