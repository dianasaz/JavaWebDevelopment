package by.sazanchuk.finalTask.service;

import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.dao.connectionPool.ConnectionPoolException;
import by.sazanchuk.finalTask.entity.Coupon;
import by.sazanchuk.finalTask.entity.Doctor;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CouponServiceImplTest {

    @Test
    public void testOne() throws ConnectionPoolException, DaoException, ParseException {
        ServiceFactory factory = new ServiceFactory();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        CouponService service = factory.getService(CouponService.class);
        /*String date = "2019-09-31 18:00";
        Date date1 = dateFormat.parse(date);
        if (!service.isTaken(3, date1)) {
            Coupon coupon = new Coupon();
            coupon.setPet_id(1);
            coupon.setUser_id(1);
            coupon.setDoctor_id(4);
            coupon.setService_id(1);
            coupon.setTime(date1);
            service.save(coupon);
        } else System.out.println("lox");*/
        List<Coupon> coupons = service.findAll();
        for (Coupon c : coupons){
            service.delete(c.getIdentity());
        }
    }
}