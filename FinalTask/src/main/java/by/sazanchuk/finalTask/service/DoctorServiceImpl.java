package by.sazanchuk.finalTask.service;

import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.dao.DoctorDao;
import by.sazanchuk.finalTask.entity.Doctor;

import java.util.List;

public class DoctorServiceImpl extends ServiceImpl implements DoctorService {
    @Override
    public List<Doctor> findAll() throws DaoException {
        DoctorDao doctorDao = transaction.createDao(DoctorDao.class);
        return doctorDao.read();
    }

    @Override
    public Doctor findByIdentity(Integer identity) throws DaoException {
        DoctorDao doctorDao = transaction.createDao(DoctorDao.class);
        Doctor doctor = null;
        if (identity != null){
            doctor = doctorDao.read(identity);
        }
        return doctor;
    }

    @Override
    public int save(Doctor doctor) throws DaoException {
        DoctorDao doctorDao = transaction.createDao(DoctorDao.class);
        if (doctor.getIdentity() != null){
            doctor.setName(doctor.getName());
            doctorDao.update(doctor);
        } else {
            doctor.setIdentity(doctorDao.create(doctor));
        }
        doctorDao.update(doctor);
        return doctor.getIdentity();
    }

    @Override
    public void delete(Integer identity) throws DaoException {
        DoctorDao doctorDao = transaction.createDao(DoctorDao.class);
        if (doctorDao.read(identity) != null){
            doctorDao.delete(identity);
        }
    }
}
