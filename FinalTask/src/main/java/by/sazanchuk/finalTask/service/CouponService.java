package by.sazanchuk.finalTask.service;

import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.entity.Coupon;

import java.util.Date;
import java.util.List;

public interface CouponService extends Service {

    List<Coupon> findAll() throws ServiceException;

    Coupon findByIdentity(Integer identity) throws ServiceException;

    int save(Coupon coupon) throws ServiceException;

    void delete(Integer identity) throws ServiceException;

    boolean isTaken(Integer doctor_id, Date date) throws ServiceException;

    List<Coupon> getCouponsOfOneUser(int user_id) throws ServiceException;
}
