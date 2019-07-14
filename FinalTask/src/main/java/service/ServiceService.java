package service;

import dao.DaoException;

import java.util.List;

public interface ServiceService extends Service {
    List<entity.Service> findAll() throws DaoException;

    entity.Service findByIdentity(Integer identity) throws DaoException;

    void save(entity.Service service) throws DaoException;

    void delete(Integer identity) throws DaoException;
}
