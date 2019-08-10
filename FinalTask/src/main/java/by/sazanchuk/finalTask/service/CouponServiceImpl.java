package by.sazanchuk.finalTask.service;

import by.sazanchuk.finalTask.dao.CouponDao;
import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.entity.Coupon;
import by.sazanchuk.finalTask.entity.Service;

import java.util.Date;
import java.util.List;

public class CouponServiceImpl extends ServiceImpl implements CouponService {
    public CouponServiceImpl() throws ServiceException {
    }

    @Override
    public List<Coupon> findAll() throws ServiceException {
        try {
            CouponDao couponDao = transaction.createDao(CouponDao.class);
            return couponDao.read();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Coupon findByIdentity(Integer identity) throws ServiceException {
        try {
            CouponDao couponDao = transaction.createDao(CouponDao.class);
            return couponDao.read(identity);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int save(Coupon coupon) throws ServiceException {
        try {
            CouponDao couponDao = transaction.createDao(CouponDao.class);
            if (coupon.getIdentity() == null) {
                coupon.setIdentity(couponDao.create(coupon));
            } else {
                couponDao.update(coupon);
            }
            return coupon.getIdentity();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Integer identity) throws ServiceException {
        try {
            CouponDao couponDao = transaction.createDao(CouponDao.class);
            if (identity != null) {
                couponDao.delete(identity);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean isTaken(Integer doctor_id, Date date) throws ServiceException {
        try {
            CouponDao couponDao = transaction.createDao(CouponDao.class);
            Coupon c = couponDao.isTaken(doctor_id, date);
            return c != null;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Coupon> getCouponsOfOneUser(int user_id) throws ServiceException {
        try {
            CouponDao couponDao = transaction.createDao(CouponDao.class);
            List<Coupon> coupons = couponDao.getCouponsOfOneUser(user_id);
            return coupons;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
