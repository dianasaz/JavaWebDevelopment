package by.sazanchuk.finalTask.dao.transaction;

import by.sazanchuk.finalTask.dao.Dao;
import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.dao.EventDao;
import by.sazanchuk.finalTask.dao.PetDao;
import by.sazanchuk.finalTask.dao.ServiceDao;
import by.sazanchuk.finalTask.dao.UserDao;

public interface Transaction {
    UserDao getUserDao();

    PetDao getPetDao();

    ServiceDao getServiceDao();

    EventDao getEventDao();

    <Type extends Dao<?>> Type createDao(Class<Type> key) throws DaoException;

    void commit() throws DaoException;

    void rollback() throws DaoException;
}
