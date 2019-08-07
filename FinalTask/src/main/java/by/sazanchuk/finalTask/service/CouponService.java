package by.sazanchuk.finalTask.service;

import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.entity.Coupon;

import java.util.Date;
import java.util.List;

public interface CouponService extends Service {

    List<Coupon> findAll() throws DaoException;

    Coupon findByIdentity(Integer identity) throws DaoException;

    int save(Coupon coupon) throws DaoException;

    void delete(Integer identity) throws DaoException;

    boolean isTaken(Integer doctor_id, Date date) throws DaoException;
}
