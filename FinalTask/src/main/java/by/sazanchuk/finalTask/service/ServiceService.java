package by.sazanchuk.finalTask.service;

import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.entity.Doctor;

import java.util.List;

public interface ServiceService extends Service {
    List<by.sazanchuk.finalTask.entity.Service> findAll() throws DaoException;

    by.sazanchuk.finalTask.entity.Service findByIdentity(Integer identity) throws DaoException;

    int save(by.sazanchuk.finalTask.entity.Service service) throws DaoException;

    void delete(Integer identity) throws DaoException;

    boolean searchService(String name) throws DaoException;

    List<by.sazanchuk.finalTask.entity.Service> searchWithOneDoctor(Doctor doctor) throws  DaoException;

    by.sazanchuk.finalTask.entity.Service searchServiceByName(String name) throws DaoException;

}
