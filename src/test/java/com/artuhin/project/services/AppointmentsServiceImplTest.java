package com.artuhin.project.services;

import com.artuhin.project.dao.daoimpl.AppointmentDao;
import com.artuhin.project.dao.daoimpl.ProcedureDao;
import com.artuhin.project.dao.daoimpl.UserDao;
import com.artuhin.project.model.entity.Appointment;
import com.artuhin.project.model.entity.Procedure;
import com.artuhin.project.model.entity.User;
import com.artuhin.project.model.enums.Role;
import com.artuhin.project.model.enums.Status;
import com.artuhin.project.services.servicesimpl.AppointmentsServiceImpl;
import com.artuhin.project.services.servicesimpl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;

public class AppointmentsServiceImplTest {
    UserDao userDao = mock(UserDao.class);
    ProcedureDao procedureDao = mock(ProcedureDao.class);
    AppointmentDao appointmentDao = mock(AppointmentDao.class);
    List<User> userList = new ArrayList<>();
    List<Procedure> procedureList = new ArrayList<>();
    Set<Procedure> procedureSet = new HashSet<>();
    AppointmentsServiceImpl appointmentService = AppointmentsServiceImpl.getInstance();
    UserServiceImpl userService = UserServiceImpl.getInstance();
    User master1 = new User();
    User master2 = new User();
    User client1 = new User();
    Procedure procedure1 = mock(Procedure.class);
    Procedure procedure2 = mock(Procedure.class);
    HashMap<Procedure, List<User>> procedureListHashMap = new HashMap<>();
    Appointment appointment1 = new Appointment();
    Appointment appointment2 = new Appointment();
    List<Appointment>appointmentList = new ArrayList<>();


    @Before
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        Field userDao1 = appointmentService.getClass().getDeclaredField("userDao");
        userDao1.setAccessible(true);
        userDao1.set(appointmentService, this.userDao);
        Field procedureDao1 = appointmentService.getClass().getDeclaredField("procedureDao");
        procedureDao1.setAccessible(true);
        procedureDao1.set(appointmentService, this.procedureDao);
        Field appointmentDao1 = appointmentService.getClass().getDeclaredField("appointmentDao");
        appointmentDao1.setAccessible(true);
        appointmentDao1.set(appointmentService, this.appointmentDao);
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
        appointment1.setId(1);
        appointment2.setId(2);
        appointment1.setStartTime(Timestamp.valueOf(LocalDateTime.now()));
        appointment2.setStartTime(Timestamp.valueOf(LocalDateTime.now()));
        appointment1.setStatus(Status.CONFIRMED);
        appointment2.setStatus(Status.CONFIRMED);
        appointmentList.add(appointment1);
        appointmentList.add(appointment2);

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
        Mockito.when(this.appointmentDao.create(any(Appointment.class))).thenReturn(appointment1);
        Mockito.when(this.appointmentDao.get(anyLong())).thenReturn(appointment1);
        Mockito.when(this.appointmentDao.update(any(Appointment.class))).thenReturn(true);
        Mockito.when(this.appointmentDao.delete(anyLong())).thenReturn(true);
        Mockito.when(this.appointmentDao.getAll()).thenReturn(appointmentList);
        Mockito.when(this.appointmentDao.checkFreeTime(any(Appointment.class))).thenReturn(true);
    }

    @Test
    public void isAppointmentCreated() {
        Assert.assertEquals(appointmentService.create(appointment1), appointment1);
    }

    @Test
    public void isAppointmentDelete() {
        Assert.assertTrue(appointmentService.delete(appointment1.getId()));
    }

    @Test
    public void isGetById() {
        Assert.assertEquals(appointmentService.getById(appointment1.getId()), appointment1);
    }

    @Test
    public void isGetAll() {
        Assert.assertEquals(appointmentService.getAll(), appointmentList);
    }

    @Test
    public void isUpdatedStartTime() {
        Assert.assertTrue(appointmentService.updateStartTime(appointment1.getId(), LocalDateTime.now().toLocalTime()));
    }

    @Test
    public void isUpdatedStatus() {
        Assert.assertTrue(appointmentService.updateStatus(appointment1.getId(), Status.CONFIRMED));
    }

    @Test
    public void isGetNotificationData() {
        Assert.assertNotNull(appointmentService.getDataForNotifications(Timestamp.valueOf(LocalDateTime.now())));
    }

}
