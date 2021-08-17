package com.artuhin.project.services;

import com.artuhin.project.model.Event;
import com.artuhin.project.factory.DaoFactory;

import java.util.List;

public class EventsServiceImpl implements EventsService {
    private static EventsServiceImpl ourInstance = new EventsServiceImpl();

    public static EventsServiceImpl getInstance() {
        return ourInstance;
    }

    private EventsServiceImpl() {
    }


    @Override
    public int create(Event event) {
        return DaoFactory.getInstance().getEventDao().create(event);
    }

    @Override
    public boolean update(Event event) {
        return DaoFactory.getInstance().getEventDao().update(event);
    }

    @Override
    public boolean delete(int id) {
        return DaoFactory.getInstance().getEventDao().delete(id);
    }

    @Override
    public Event getById(int id) {
        return DaoFactory.getInstance().getEventDao().getByID(id);
    }

    @Override
    public boolean clearAll() {
        return DaoFactory.getInstance().getEventDao().clearAll();
    }

    @Override
    public List<Event> getAll() {
        return DaoFactory.getInstance().getEventDao().getAll();
    }

    @Override
    public List<Event> getByClientId(long id) {
        return DaoFactory.getInstance().getEventDao().getEventsByClientId(id);
    }

    @Override
    public List<Event> getByMasterId(long id) {
        return DaoFactory.getInstance().getEventDao().getEventsByMasterId(id);
    }
}
