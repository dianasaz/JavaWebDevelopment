package by.sazanchuk.finalTask.service;

import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.entity.Event;

import java.util.List;

public interface EventService extends Service {
    List<Event> findAll() throws DaoException;

    by.sazanchuk.finalTask.entity.Event findByIdentity(Integer identity) throws DaoException;

    int save(by.sazanchuk.finalTask.entity.Event event) throws DaoException;

    void delete(Integer identity) throws DaoException;
}
