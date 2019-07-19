package by.sazanchuk.finalTask.dao.transaction;

import by.sazanchuk.finalTask.dao.Dao;
import by.sazanchuk.finalTask.dao.DaoException;

public interface Transaction {

    <Type extends Dao<?>> Type createDao(Class<Type> key) throws DaoException;

    void commit() throws DaoException;

    void rollback() throws DaoException;
}
