package com.artuhin.project.services;

import com.artuhin.project.model.Event;
import com.artuhin.project.model.Role;

import java.util.HashMap;
import java.util.List;

public interface EventsService extends Service {

    int create(Event event);

    boolean update(Event event);

    boolean delete(int id);

    Event getById(int id);

    boolean clearAll();

    List<Event> getAll();

    List<Event> getByClientId(long id);

    List<Event> getByMasterId(long id);


}
