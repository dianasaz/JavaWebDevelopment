package by.sazanchuk.finalTask.service;

import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.dao.EventDao;
import by.sazanchuk.finalTask.entity.Event;

import java.util.List;

public class EventServiceImpl extends ServiceImpl implements EventService{

    @Override
    public List<Event> findAll() throws DaoException {
        EventDao eventDao = transaction.createDao(EventDao.class);
        return eventDao.read();
    }

    @Override
    public Event findByIdentity(Integer identity) throws DaoException {
        EventDao eventDao = transaction.createDao(EventDao.class);
        return eventDao.read(identity);
    }

    @Override
    public int save(Event event) throws DaoException {
        EventDao eventDao = transaction.createDao(EventDao.class);
        eventDao.update(event);
        return event.getIdentity();
    }

    @Override
    public void delete(Integer identity) throws DaoException {
        EventDao eventDao = transaction.createDao(EventDao.class);
        eventDao.delete(identity);
    }

    public List<Event> getEventsOfOnePet(Integer pet_identity) throws DaoException {
        EventDao eventDao = transaction.createDao(EventDao.class);
        return eventDao.readEventsOfOnePet(pet_identity);
    }

}
