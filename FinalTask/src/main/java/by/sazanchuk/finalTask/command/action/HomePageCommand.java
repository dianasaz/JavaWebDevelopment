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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class HomePageCommand implements Command {
    private static final String SERVICES = "services";
    private static final String SERVICE_NAMES = "serviceNames";
    private static final String DOCTORS = "doctors";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
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

        } catch (DaoException e) {
        }
        setAttributesToSession(services, doctors, serviceNames, request);
        return new CommandResult(Page.HOME_PAGE.getPage(), false);
    }

    private List<Service> getAllService() throws DaoException, ServiceException {

        ServiceFactory factory = null;
        try {
            factory = new ServiceFactory();
        } catch (ConnectionPoolException e) {
        }
        ServiceService service = factory.getService(ServiceService.class);
        List<Service> services = service.findAll();
        return services;
    }

    private List<Doctor> getAllDoctors() throws DaoException, ServiceException {

        ServiceFactory factory = null;
        try {
            factory = new ServiceFactory();
        } catch (ConnectionPoolException e) {
        }
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
