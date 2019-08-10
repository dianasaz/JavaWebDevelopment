package by.sazanchuk.finalTask.service;

import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.dao.DoctorDao;
import by.sazanchuk.finalTask.dao.ServiceDao;
import by.sazanchuk.finalTask.entity.Doctor;
import by.sazanchuk.finalTask.entity.Service;

import java.util.List;

public class DoctorServiceImpl extends ServiceImpl implements DoctorService {
    public DoctorServiceImpl() throws ServiceException {
    }

    @Override
    public List<Doctor> findAll() throws ServiceException {
        try {
            DoctorDao doctorDao = transaction.createDao(DoctorDao.class);
            ServiceDao serviceDao = transaction.createDao(ServiceDao.class);
            List<Doctor> doctors = doctorDao.read();
            for (int i = 0; i < doctors.size(); i++) {
                List<Service> services = serviceDao.searchWithOneDoctor(doctors.get(i));
                for (int j = 0; j < services.size(); j++) {
                    String a = services.get(j).getName();
                    Service s = serviceDao.read(services.get(j).getIdentity());
                    doctors.get(i).addService(s);
                }
            }
            return doctors;
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public Doctor findByIdentity(Integer identity) throws ServiceException {
        try {
            DoctorDao doctorDao = transaction.createDao(DoctorDao.class);
            Doctor doctor = null;
            if (identity != null) {
                doctor = doctorDao.read(identity);
            }
            return doctor;
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public Doctor findByName(String name) throws ServiceException {
        try {
            DoctorDao doctorDao = transaction.createDao(DoctorDao.class);
            Doctor doctor = null;
            if (name != null) {
                doctor = doctorDao.readByName(name);
            }
            return doctor;
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean isExist(Integer doctor_id, Integer service_id) throws ServiceException {
        try {
            DoctorDao doctorDao = transaction.createDao(DoctorDao.class);
            if (doctor_id != null && service_id != null) {
                return doctorDao.isExist(doctor_id, service_id);
            }
            return true;
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public int save(Doctor doctor) throws ServiceException {
        try {
            DoctorDao doctorDao = transaction.createDao(DoctorDao.class);
            if (doctor.getIdentity() != null) {
                doctor.setName(doctor.getName());
            } else {
                doctor.setIdentity(doctorDao.create(doctor));
            }
            doctorDao.update(doctor);
            return doctor.getIdentity();
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(Doctor doctor, Service service) throws ServiceException {
        try {
            DoctorDao doctorDao = transaction.createDao(DoctorDao.class);
            ServiceDao serviceDao = transaction.createDao(ServiceDao.class);
            if (serviceDao.readByName(service.getName()) != null) {
                int doctor_id = save(doctor);
                doctorDao.createDS(doctor_id, service.getIdentity());
            }
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    public void deleteReferences(Doctor doctor) throws ServiceException {
        try {
            DoctorDao doctorDao = transaction.createDao(DoctorDao.class);
            doctorDao.deleteDS(doctor.getIdentity());
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Integer identity) throws ServiceException {
        try {
            DoctorDao doctorDao = transaction.createDao(DoctorDao.class);
            if (doctorDao.read(identity) != null) {
                doctorDao.delete(identity);
            }
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }
}
