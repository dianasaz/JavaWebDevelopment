package by.sazanchuk.finalTask.service;

import by.sazanchuk.finalTask.dao.transaction.Transaction;

abstract public class ServiceImpl implements Service {
    protected Transaction transaction = null;

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
