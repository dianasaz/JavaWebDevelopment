package by.sazanchuk.finalTask.dao.transaction;

import by.sazanchuk.finalTask.dao.Dao;
import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.dao.PetDao;
import by.sazanchuk.finalTask.dao.ServiceDao;
import by.sazanchuk.finalTask.dao.UserDao;

public interface Transaction {
    <Type extends Dao<?>> Type createDao(Class<Type> key) throws DaoException;

    void commit() throws DaoException;

    void rollback() throws DaoException;
}
