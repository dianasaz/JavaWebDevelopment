package by.sazanchuk.finalTask.command.action;

import by.sazanchuk.finalTask.command.Page;
import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.dao.connectionPool.ConnectionPoolException;
import by.sazanchuk.finalTask.entity.Doctor;
import by.sazanchuk.finalTask.entity.Service;
import by.sazanchuk.finalTask.service.DoctorService;
import by.sazanchuk.finalTask.service.ServiceException;
import by.sazanchuk.finalTask.service.ServiceFactory;
import by.sazanchuk.finalTask.service.ServiceService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class HomePageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(HomePageCommand.class);

    private static final String SERVICES = "services";
    private static final String SERVICE_NAMES = "serviceNames";
    private static final String DOCTORS = "doctors";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response){
        List<Service> services = null;
        List<Doctor> doctors = null;
        String[] serviceNames = null;
        try {
            services = getAllService();
            doctors = getAllDoctors();
            serviceNames = new String[services.size()];
            for (int i = 0; i < services.size(); i++){
                serviceNames[i] = services.get(i).getName();
            }

        } catch (ServiceException e) {
            logger.log(Level.INFO, e.getMessage());
            return new CommandResult(Page.HOME_PAGE.getPage(), false);
        }
        setAttributesToSession(services, doctors, serviceNames, request);
        return new CommandResult(Page.HOME_PAGE.getPage(), false);
    }

    private List<Service> getAllService() throws ServiceException {
        ServiceFactory factory = new ServiceFactory();
        ServiceService service = factory.getService(ServiceService.class);
        List<Service> services = service.findAll();
        return services;
    }

    private List<Doctor> getAllDoctors() throws ServiceException {

        ServiceFactory factory = new ServiceFactory();
        DoctorService service = factory.getService(DoctorService.class);
        List<Doctor> doctors = service.findAll();
        return doctors;
    }


    private void setAttributesToSession(List<Service> services, List<Doctor> doctors, String[] names, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(SERVICES, services);
        session.setAttribute(SERVICE_NAMES, names);
        session.setAttribute(DOCTORS, doctors);
    }

}
