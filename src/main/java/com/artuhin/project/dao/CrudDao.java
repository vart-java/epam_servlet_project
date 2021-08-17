package com.artuhin.project.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface CrudDao<T> extends Dao {

    int create(T t);

    boolean update(T t);

    boolean delete(int id);

    T getByID(int id) throws NoSuchMethodException, InvocationTargetException;

    List<T> getAll() throws NoSuchMethodException, InvocationTargetException;

    boolean clearAll();
}
