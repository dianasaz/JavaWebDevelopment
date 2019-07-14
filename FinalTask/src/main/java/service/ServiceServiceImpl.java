package service;

import dao.DaoException;
import dao.ServiceDao;
import entity.Service;

import java.util.List;

public class ServiceServiceImpl extends ServiceImpl implements ServiceService {
    @Override
    public List<entity.Service> findAll() throws DaoException {
        ServiceDao serviceDao = transaction.createDao(ServiceDao.class);
        return serviceDao.read();
    }

    @Override
    public entity.Service findByIdentity(Integer identity) throws DaoException {
        ServiceDao serviceDao = transaction.createDao(ServiceDao.class);
        Service s = null;
        if (identity != null) {
            s = serviceDao.read(identity);
        }
        return s;
    }

    @Override
    public void save(entity.Service service) throws DaoException {
        ServiceDao serviceDao = transaction.createDao(ServiceDao.class);
        if (service.getIdentity() != null){
            if (service.getName() != null && service.getPrice() != null){
                service.setName(service.getName());
                service.setPrice(service.getPrice());
            } else {
                //service.setPrice();
            }
        }
    }

    @Override
    public void delete(Integer identity) throws DaoException {
        ServiceDao serviceDao = transaction.createDao(ServiceDao.class);
        if (identity != null) serviceDao.delete(identity);
    }
}
