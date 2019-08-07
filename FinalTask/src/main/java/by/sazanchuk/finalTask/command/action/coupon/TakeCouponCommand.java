package by.sazanchuk.finalTask.command.action.coupon;

import by.sazanchuk.finalTask.command.ConfigurationManager;
import by.sazanchuk.finalTask.command.action.Command;
import by.sazanchuk.finalTask.command.action.CommandResult;
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
    private static final String PET = "pet";
    private static final String SERVIES_NAMES = "serviceNames";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, DaoException {
        User user = (User) request.getSession().getAttribute(USER);
        Pet pet = (Pet) request.getSession().getAttribute(PET);
        String pet_id = request.getParameter(PET_ID);
        String serviceNumber = request.getParameter(SERVICE);
        String doctor = request.getParameter(DOCTOR);
        String date = request.getParameter(DATE);

        if (serviceNumber == null || doctor == null || pet == null || user == null || date == null){
            log.log(Level.INFO, "invalid info");
            return goBackWithError(request, "error");
        } else{
            if (pet_id == null){
                return new CommandResult("/controller?command=home_page", false);
            }
            String[] strings = (String[]) request.getSession().getAttribute(SERVIES_NAMES);
            String service = strings[Integer.valueOf(serviceNumber)];
            Service service1 = getService(service);
            ServiceFactory factory = null;
            try {
                factory = new ServiceFactory();
            } catch (ConnectionPoolException e) {
                System.out.println("lol");
            }

            Doctor d = getDoctor(doctor);

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
                coupon.setPet_id(pet.getIdentity());
                coupon.setUser_id(user.getId());
                coupon.setDoctor_id(d.getIdentity());
                coupon.setService_id(service1.getIdentity());
                coupon.setTaken(true);
                coupon.setTime(date1);
                couponService.save(coupon);
                return new CommandResult("/controller?command=profile", false);
            } else return goBackWithError(request, "error");
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
