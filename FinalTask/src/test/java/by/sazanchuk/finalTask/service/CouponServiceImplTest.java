package by.sazanchuk.finalTask.service;

import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.dao.connectionPool.ConnectionPoolException;
import by.sazanchuk.finalTask.entity.Coupon;
import by.sazanchuk.finalTask.entity.Doctor;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CouponServiceImplTest {

    @Test
    public void testOne() throws ParseException, ServiceException {
        ServiceFactory factory = new ServiceFactory();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        CouponService service = factory.getService(CouponService.class);
        String date = "2019-09-03 10:00";
        Date date1 = dateFormat.parse(date);
        if (!service.isTaken(2, date1)) {
            Coupon coupon = new Coupon();
            coupon.setUser_id(2);
            coupon.setPet_id(2);
            coupon.setDoctor_id(2);
            coupon.setService_id(3);
            coupon.setTime(date1);
            System.out.println(coupon);


        } else System.out.println("lox");
    }

    @Test
    public void testTwo() throws ServiceException {
        ServiceFactory factory = new ServiceFactory();
        CouponService service = factory.getService(CouponService.class);
        List<Coupon> coupons = service.getCouponsOfOneUser(2);
        System.out.println(coupons.toString());
    }
    }
