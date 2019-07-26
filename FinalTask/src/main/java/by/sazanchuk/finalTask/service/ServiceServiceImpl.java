package by.sazanchuk.finalTask.service;

import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.dao.ServiceDao;
import by.sazanchuk.finalTask.dao.connectionPool.ConnectionPoolException;
import by.sazanchuk.finalTask.entity.Service;

import java.util.List;

public class ServiceServiceImpl extends ServiceImpl implements ServiceService {
    ServiceServiceImpl() throws DaoException, ConnectionPoolException {
    }

    @Override
    public List<by.sazanchuk.finalTask.entity.Service> findAll() throws DaoException {
        ServiceDao serviceDao = transaction.createDao(ServiceDao.class);
        return serviceDao.read();
    }

    @Override
    public by.sazanchuk.finalTask.entity.Service findByIdentity(Integer identity) throws DaoException {
        ServiceDao serviceDao = transaction.createDao(ServiceDao.class);
        Service s = null;
        if (identity != null) {
            s = serviceDao.read(identity);
        }
        return s;
    }

    @Override
    public void save(by.sazanchuk.finalTask.entity.Service service) throws DaoException {
        ServiceDao serviceDao = transaction.createDao(ServiceDao.class);
        if (service.getIdentity() != null){
            if (service.getName() != null && service.getPrice() != null){
                service.setName(service.getName());
                service.setPrice(service.getPrice());
            } else {
                //by.sazanchuk.finalTask.service.setPrice();
            }
        }
    }

    @Override
    public void delete(Integer identity) throws DaoException {
        ServiceDao serviceDao = transaction.createDao(ServiceDao.class);
        if (identity != null) serviceDao.delete(identity);
    }
}
