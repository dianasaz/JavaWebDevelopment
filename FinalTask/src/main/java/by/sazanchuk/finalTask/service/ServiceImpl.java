package by.sazanchuk.finalTask.service;

import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.dao.connectionPool.ConnectionPoolException;
import by.sazanchuk.finalTask.dao.transaction.Transaction;
import by.sazanchuk.finalTask.dao.transaction.TransactionFactory;

abstract public class ServiceImpl implements Service {
    protected Transaction transaction = null;

    public ServiceImpl() throws ServiceException {
        try {
            transaction = TransactionFactory.getFactory().createTransaction();
        } catch (DaoException | ConnectionPoolException e) {
            throw new ServiceException(e);
        }
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
