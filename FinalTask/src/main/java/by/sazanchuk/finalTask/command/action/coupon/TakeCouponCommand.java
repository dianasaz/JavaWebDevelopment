package by.sazanchuk.finalTask.command.action.coupon;

import by.sazanchuk.finalTask.command.ConfigurationManager;
import by.sazanchuk.finalTask.command.action.Command;
import by.sazanchuk.finalTask.command.action.CommandResult;
import by.sazanchuk.finalTask.dao.Dao;
import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.dao.connectionPool.ConnectionPoolException;
import by.sazanchuk.finalTask.entity.Coupon;
import by.sazanchuk.finalTask.entity.Doctor;
import by.sazanchuk.finalTask.entity.Pet;
import by.sazanchuk.finalTask.entity.Service;
import by.sazanchuk.finalTask.entity.User;
import by.sazanchuk.finalTask.service.CouponService;
import by.sazanchuk.finalTask.service.DoctorService;
import by.sazanchuk.finalTask.service.PetService;
import by.sazanchuk.finalTask.service.ServiceException;
import by.sazanchuk.finalTask.service.ServiceFactory;
import by.sazanchuk.finalTask.service.ServiceService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TakeCouponCommand implements Command {
    private static final Logger log = LogManager.getLogger(TakeCouponCommand.class);
    private static final String PET_ID = "pet_id";
    private static final String USER = "user";
    private static final String SERVICE = "service";
    private static final String DOCTOR = "doctor";
    private static final String DATE = "date";
    private static final String SERVIES_NAMES = "serviceNames";
    private static final String ERROR_TIME = "error_time";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, DaoException {
        User user = (User) request.getSession().getAttribute(USER);
        String pet_id = request.getParameter(PET_ID);
        String serviceNumber = request.getParameter(SERVICE);
        String doctor = request.getParameter(DOCTOR + serviceNumber);
        String date = request.getParameter(DATE);

        if (serviceNumber == null || doctor == null || user == null || date == null || serviceNumber.isEmpty() || doctor.isEmpty() || date.isEmpty()){
            log.log(Level.INFO, "invalid info");
            request.getSession().setAttribute("pet_id", pet_id);
            return goBackWithError(request, "error");
        } else{
            if (pet_id == null){
                return new CommandResult("/controller?command=home_page", false);
            }
            String[] strings = (String[]) request.getSession().getAttribute(SERVIES_NAMES);
            String service = strings[Integer.valueOf(serviceNumber)-1];
            Service service1 = getService(service);
            int serviceId = service1.getIdentity();
            ServiceFactory factory = null;
            try {
                factory = new ServiceFactory();
            } catch (ConnectionPoolException e) {
                System.out.println("lol");
            }

            Doctor d = getDoctor(doctor);
            int doctorId = d.getIdentity();
            int petId = Integer.valueOf(pet_id);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            CouponService couponService = factory.getService(CouponService.class);
            Date date1 = null;
            try {
                date1 = dateFormat.parse(date);
            } catch (ParseException e) {
                System.out.println("mda");
            }
            if (!couponService.isTaken(d.getIdentity(), date1)) {
                Coupon coupon = new Coupon();
                coupon.setPet_id(petId);
                coupon.setUser_id(user.getId());
                coupon.setDoctor_id(doctorId);
                coupon.setService_id(serviceId);
                coupon.setTime(date1);
                try {
                    couponService.save(coupon);
                } catch (DaoException e){
                    log.log(Level.INFO, "sorry");
                    return goBackWithError(request, "error");
                }

                request.getSession().removeAttribute("pet_id");
                return new CommandResult("/controller?command=profile", false);
            } else return goBackWithError(request, ERROR_TIME);
        }
    }

    private CommandResult goBackWithError(HttpServletRequest request, String error) {
        request.setAttribute(error, true);
        return new CommandResult(ConfigurationManager.getProperty("path.page.take_coupon"), false);
    }

    private Service getService(String s) throws DaoException {
        ServiceFactory factory = null;
        try {
            factory = new ServiceFactory();
        } catch (ConnectionPoolException e) {
        }
        assert factory != null;
        ServiceService serviceService = factory.getService(ServiceService.class);
        return serviceService.searchServiceByName(s);
    }

    private Doctor getDoctor(String name) throws DaoException {
        ServiceFactory factory = null;
        try {
            factory = new ServiceFactory();
        } catch (ConnectionPoolException e) {
        }
        assert factory != null;
        DoctorService doctorService = factory.getService(DoctorService.class);
        return doctorService.findByName(name);
    }


    private Pet searchPet(String pet_id) throws DaoException {
        ServiceFactory factory = null;
        try {
            factory = new ServiceFactory();
        } catch (ConnectionPoolException e) {
        }
        PetService petService = factory.getService(PetService.class);
        Pet pet = petService.findByIdentity(Integer.valueOf(pet_id));

        return pet;
    }

}
