package dao;

import exception.PersistentException;

public interface Dao <T> {
    Integer create(T entity) throws PersistentException;

    T read(Integer identity) throws PersistentException;

    void update(T entity) throws PersistentException;

    void delete(Integer id) throws PersistentException;
}
