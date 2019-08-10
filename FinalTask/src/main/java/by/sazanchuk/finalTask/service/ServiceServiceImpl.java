package by.sazanchuk.finalTask.service;

import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.dao.ServiceDao;
import by.sazanchuk.finalTask.dao.connectionPool.ConnectionPoolException;
import by.sazanchuk.finalTask.entity.Doctor;
import by.sazanchuk.finalTask.entity.Service;

import java.util.List;

public class ServiceServiceImpl extends ServiceImpl implements ServiceService {


    public ServiceServiceImpl() throws ServiceException {
    }

    @Override
    public List<by.sazanchuk.finalTask.entity.Service> findAll() throws ServiceException {
        ServiceDao serviceDao = null;
        try {
            serviceDao = transaction.createDao(ServiceDao.class);
            return serviceDao.read();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Service> searchWithOneDoctor(Doctor doctor) throws ServiceException {
        ServiceDao serviceDao = null;
        try {
            serviceDao = transaction.createDao(ServiceDao.class);
            return serviceDao.searchWithOneDoctor(doctor);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public by.sazanchuk.finalTask.entity.Service findByIdentity(Integer identity) throws ServiceException {
        ServiceDao serviceDao = null;
        try {
            serviceDao = transaction.createDao(ServiceDao.class);
            Service s = null;
            if (identity != null) {
                s = serviceDao.read(identity);
            }
            return s;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }

    @Override
    public int save(by.sazanchuk.finalTask.entity.Service service) throws ServiceException {
        ServiceDao serviceDao = null;
        try {
            serviceDao = transaction.createDao(ServiceDao.class);
            if (service.getIdentity() != null){
                service.setName(service.getName());
                service.setPrice(service.getPrice());
            } else {
                service.setIdentity(serviceDao.create(service));
            }
            serviceDao.update(service);
            return service.getIdentity();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }

    @Override
    public void delete(Integer identity) throws ServiceException {
        ServiceDao serviceDao = null;
        try {
            serviceDao = transaction.createDao(ServiceDao.class);
            if (identity != null) serviceDao.delete(identity);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean searchService(String name) throws ServiceException {
        ServiceDao serviceDao = null;
        try {
            serviceDao = transaction.createDao(ServiceDao.class);
            return serviceDao.searchService(name) != null;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }

    @Override
    public Service searchServiceByName(String name) throws ServiceException {
        ServiceDao serviceDao = null;
        try {
            serviceDao = transaction.createDao(ServiceDao.class);
            return serviceDao.readByName(name);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }
}
