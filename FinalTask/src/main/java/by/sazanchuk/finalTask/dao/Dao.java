package by.sazanchuk.finalTask.dao;

public interface Dao <T> {
    Integer create(T entity) throws DaoException;

    T read(Integer identity) throws DaoException;

    void update(T entity) throws DaoException;

    void delete(Integer id) throws DaoException;
}
