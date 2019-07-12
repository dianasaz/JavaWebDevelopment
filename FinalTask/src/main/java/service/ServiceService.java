package service;

import dao.DaoException;

import java.util.List;

public interface ServiceService extends Service {
    List<Service> findAll() throws DaoException;

    Service findByIdentity(Integer identity) throws DaoException;

    void save(Service service) throws DaoException;

    void delete(Integer identity) throws DaoException;
}
