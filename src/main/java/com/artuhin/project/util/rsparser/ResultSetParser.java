package com.artuhin.project.util.rsparser;

import com.artuhin.project.util.annotations.Column;
import com.artuhin.project.util.annotations.Model;
import com.artuhin.project.util.annotations.ReferenceClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultSetParser {
    private static Logger LOGGER = LogManager.getLogger(ResultSetParser.class);

    private static ResultSetParser ourInstance = new ResultSetParser();

    public static ResultSetParser getInstance() {
        return ourInstance;
    }

    private ResultSetParser() {
    }

    public <T> List<T> parse(ResultSet rs, T instance) {
        List<T> result = new ArrayList<>();
        Class clazz = instance.getClass();
        T resultUnit = instance;
        if (!resultUnit.getClass().isAnnotationPresent(Model.class)) {
            LOGGER.error("Can`t parse instance because that isn`t model.");
            throw new IllegalArgumentException();
        }
        try {
            while (rs.next()) {
                for (Field f : clazz.getDeclaredFields()) {
                    if (f.isAnnotationPresent(Column.class)) {
                        String name = f.getAnnotation(Column.class).name();
                        f.setAccessible(true);
                        if (f.isAnnotationPresent(ReferenceClass.class)) {
                            f.set(resultUnit, partParse(rs, f));
                        } else {
                            f.set(resultUnit, rs.getObject(name));
                        }
                    }
                }
                result.add(resultUnit);
                resultUnit = (T) clazz.getConstructor().newInstance();
            }
        } catch (SQLException e) {
            LOGGER.error("SQL exception from parser", e);
        } catch (NoSuchMethodException e) {
            LOGGER.error("No such method!", e);
        } catch (InstantiationException e) {
            LOGGER.error("Instantiation exception!", e);
        } catch (IllegalAccessException e) {
            LOGGER.error("Can`t access to field", e);
        } catch (InvocationTargetException e) {
            LOGGER.error("Can`t invoke method", e);
        }
        return result;
    }

    private <F> F partParse(ResultSet rs, F f) throws SQLException, IllegalAccessException {
        Class clazz = f.getClass();
        if (!clazz.isAnnotationPresent(Model.class)) {
            LOGGER.error("Can`t parse instance because that isn`t model.");
            throw new IllegalArgumentException();
        }
        for (Field s : clazz.getDeclaredFields()) {
            if (s.isAnnotationPresent(Column.class)) {
                String name = s.getAnnotation(Column.class).name();
                s.setAccessible(true);
                s.set(clazz, rs.getObject(name));
            }
        }
        return f;
    }
}
