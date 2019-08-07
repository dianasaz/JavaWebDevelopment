package by.sazanchuk.finalTask.service;

import by.sazanchuk.finalTask.dao.CouponDao;
import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.entity.Coupon;

import java.util.Date;
import java.util.List;

public class CouponServiceImpl extends ServiceImpl implements CouponService{
    public CouponServiceImpl() throws ServiceException {
    }

    @Override
    public List<Coupon> findAll() throws DaoException {
        CouponDao couponDao = transaction.createDao(CouponDao.class);
        return couponDao.read();
    }

    @Override
    public Coupon findByIdentity(Integer identity) throws DaoException {
        CouponDao couponDao = transaction.createDao(CouponDao.class);
        return couponDao.read(identity);
    }

    @Override
    public int save(Coupon coupon) throws DaoException {
        CouponDao couponDao = transaction.createDao(CouponDao.class);
        if (coupon.getIdentity() == null) {
            coupon.setIdentity(couponDao.create(coupon));
        }
        couponDao.update(coupon);
        return coupon.getIdentity();
    }

    @Override
    public void delete(Integer identity) throws DaoException {
        CouponDao couponDao = transaction.createDao(CouponDao.class);
        if (identity != null) {
            couponDao.delete(identity);
        }
    }

    @Override
    public boolean isTaken(Integer doctor_id, Date date) throws DaoException {
        CouponDao couponDao = transaction.createDao(CouponDao.class);
        Coupon c = couponDao.isTaken(doctor_id, date);
        return c != null;
    }
}
