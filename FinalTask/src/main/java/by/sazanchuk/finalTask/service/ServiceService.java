package by.sazanchuk.finalTask.service;

import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.entity.Doctor;

import java.util.List;

public interface ServiceService extends Service {
    List<by.sazanchuk.finalTask.entity.Service> findAll() throws ServiceException;

    by.sazanchuk.finalTask.entity.Service findByIdentity(Integer identity) throws ServiceException;

    int save(by.sazanchuk.finalTask.entity.Service service) throws ServiceException;

    void delete(Integer identity) throws ServiceException;

    boolean searchService(String name) throws ServiceException;

    List<by.sazanchuk.finalTask.entity.Service> searchWithOneDoctor(Doctor doctor) throws ServiceException;

    by.sazanchuk.finalTask.entity.Service searchServiceByName(String name) throws ServiceException;

}
