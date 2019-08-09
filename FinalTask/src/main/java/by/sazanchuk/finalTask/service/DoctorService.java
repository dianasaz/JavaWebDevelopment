package by.sazanchuk.finalTask.service;

import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.entity.Doctor;

import java.util.List;

public interface DoctorService extends Service{
    List<Doctor> findAll() throws DaoException;

    Doctor findByIdentity(Integer identity) throws DaoException;

    Doctor findByName(String name) throws DaoException;

    boolean isExist(Integer doctor_id,Integer service_id) throws DaoException;

    int save(Doctor doctor) throws DaoException;

    void deleteReferences(Doctor doctor) throws DaoException;

    void save(Doctor doctor, by.sazanchuk.finalTask.entity.Service service) throws DaoException;

    void delete(Integer identity) throws DaoException;
}
