package by.sazanchuk.finalTask.service;

import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.entity.Doctor;

import java.util.List;

public interface DoctorService extends Service{
    List<Doctor> findAll() throws ServiceException;

    Doctor findByIdentity(Integer identity) throws ServiceException;

    Doctor findByName(String name) throws ServiceException;

    boolean isExist(Integer doctor_id,Integer service_id) throws ServiceException;

    int save(Doctor doctor) throws ServiceException;

    void deleteReferences(Doctor doctor) throws ServiceException;

    void save(Doctor doctor, by.sazanchuk.finalTask.entity.Service service) throws ServiceException;

    void delete(Integer identity) throws ServiceException;
}
