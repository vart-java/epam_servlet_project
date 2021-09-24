package com.artuhin.project.services;

import com.artuhin.project.dao.daoimpl.ProcedureDao;
import com.artuhin.project.model.entity.Procedure;
import com.artuhin.project.services.servicesimpl.ProcedureServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;

public class ProcedureServiceImplTest {
    ProcedureServiceImpl procedureService = ProcedureServiceImpl.getInstance();
    ProcedureDao procedureDao = mock(ProcedureDao.class);
    List<Procedure> procedureList = new ArrayList<>();
    Procedure procedure1 = mock(Procedure.class);
    Procedure procedure2 = mock(Procedure.class);


    @Before
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        Field procedureDao1 = procedureService.getClass().getDeclaredField("procedureDao");
        procedureDao1.setAccessible(true);
        procedureDao1.set(procedureService, this.procedureDao);
        procedureList.add(procedure1);
        procedureList.add(procedure2);

        Mockito.when(this.procedureDao.create(any(Procedure.class))).thenReturn(procedure1);
        Mockito.when(this.procedureDao.get(anyLong())).thenReturn(procedure1);
        Mockito.when(this.procedureDao.update(any(Procedure.class))).thenReturn(true);
        Mockito.when(this.procedureDao.delete(anyLong())).thenReturn(true);
        Mockito.when(this.procedureDao.getAll()).thenReturn(procedureList);
    }

    @Test
    public void isGetAll() {
        Assert.assertEquals(procedureService.getAll(), procedureList);
    }

    @Test
    public void isGetProcedureById() {
        Assert.assertEquals(procedureService.getProcedureById(1L), procedure1);
    }

}
