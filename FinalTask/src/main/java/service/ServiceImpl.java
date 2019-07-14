package service;

import dao.transaction.Transaction;

abstract public class ServiceImpl implements Service {
    protected Transaction transaction = null;

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
