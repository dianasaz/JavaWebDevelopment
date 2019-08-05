package by.sazanchuk.finalTask.command.action.coupon;

import by.sazanchuk.finalTask.command.ConfigurationManager;
import by.sazanchuk.finalTask.command.action.Command;
import by.sazanchuk.finalTask.command.action.CommandResult;
import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.dao.connectionPool.ConnectionPoolException;
import by.sazanchuk.finalTask.entity.Coupon;
import by.sazanchuk.finalTask.entity.Doctor;
import by.sazanchuk.finalTask.entity.Service;
import by.sazanchuk.finalTask.entity.User;
import by.sazanchuk.finalTask.service.CouponService;
import by.sazanchuk.finalTask.service.DoctorService;
import by.sazanchuk.finalTask.service.ServiceException;
import by.sazanchuk.finalTask.service.ServiceFactory;
import by.sazanchuk.finalTask.service.ServiceService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TakeCouponCommand implements Command {
    private static final Logger log = LogManager.getLogger(TakeCouponCommand.class);
    private static final String PET_ID = "pet_id";
    private static final String USER = "user";
    private static final String SERVICE = "service";
    private static final String DOCTOR = "doctor";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, DaoException {
        User user = (User) request.getSession().getAttribute(USER);
        String pet_id = request.getParameter(PET_ID);
        String service = request.getParameter(SERVICE);
        String doctor = request.getParameter(DOCTOR);

        if (service == null || doctor == null || pet_id == null || user == null){
            log.log(Level.INFO, "invalid info");
            return goBackWithError(request, "error");
        } else{
            createCoupon(user, pet_id, doctor, service);
            return new CommandResult("/controller?command=profile", false);
        }
    }

    private CommandResult goBackWithError(HttpServletRequest request, String error) {
        request.setAttribute(error, true);
        return new CommandResult(ConfigurationManager.getProperty("path.page.take_coupon"), false);
    }

    private Coupon createCoupon(User user, String pet_id, String doctor, String service) throws DaoException {
        ServiceFactory factory = null;
        try {
            factory = new ServiceFactory();
        } catch (ConnectionPoolException e) {
        }
        CouponService couponService = factory.getService(CouponService.class);
        ServiceService serviceService = factory.getService(ServiceService.class);
        DoctorService doctorService = factory.getService(DoctorService.class);

        Service s = serviceService.searchServiceByName(service);
        Doctor d = doctorService.findByName(doctor);

        Coupon coupon = new Coupon();
        coupon.setPet_id(Integer.valueOf(pet_id));
        coupon.setUser_id(user.getId());
        coupon.setDoctor_id(d.getIdentity());
        coupon.setService_id(s.getIdentity());
        coupon.setTaken(true);
        coupon.setTime(null);
        couponService.save(coupon);

        return coupon;
    }
}
