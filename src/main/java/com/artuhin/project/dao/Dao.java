package com.artuhin.project.dao;

import java.util.List;

public interface Dao<T> {
    T create(T t);
    T get (long id);
    boolean update (T t);
    boolean delete (long id);
    List<T> getAll();
}
