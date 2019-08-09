package by.sazanchuk.finalTask.command.action.doctor;

import by.sazanchuk.finalTask.command.ConfigurationManager;
import by.sazanchuk.finalTask.command.action.Command;
import by.sazanchuk.finalTask.command.action.CommandResult;
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
import java.util.Arrays;
import java.util.List;

public class EditDoctorCommand implements Command {
    private static final Logger logger = LogManager.getLogger(EditDoctorCommand.class);
    private static final String ID = "doctor_id";
    private static final String NAME = "name";
    private static final String SERVICES = "service";
    private static final String ERROR_UPDATE = "error_update";
    private static final String ERROR_NULL = "error_null";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, DaoException {
        String doctor_id = (String) request.getSession().getAttribute(ID);
        String name = request.getParameter(NAME);
        String[] service = request.getParameterValues(SERVICES);

        if (name == null && service == null) {
            String id = request.getParameter(ID);
            request.getSession().setAttribute(ID  , id);
            return goBackWithError(request, ERROR_UPDATE);
        } else {
            ServiceFactory factory = null;
            ServiceService serviceService = null;
            DoctorService doctorService = null;
            try {
                factory = new ServiceFactory();
                serviceService = factory.getService(ServiceService.class);
                doctorService = factory.getService(DoctorService.class);
            } catch (DaoException | ConnectionPoolException e) {
                logger.log(Level.INFO, "service error");
            }

            Doctor oldDoctor = searchDoctor(doctor_id);
            Doctor doctor = new Doctor();
            doctor.setIdentity(oldDoctor.getIdentity());
            if (name == null || name.isEmpty()) {
                doctor.setName(oldDoctor.getName());
            } else {
                doctor.setName(name);
                doctorService.save(doctor);
            }
            if (service == null || service.length == 0) {
                doctor.setService(oldDoctor.getService());
            } else {
                List<String> services = Arrays.asList(service);
                doctor.removeServices();
                doctorService.deleteReferences(doctor);
                for (String s : services) {
                    Service service1 = serviceService.searchServiceByName(s);
                    if (service1 != null) {
                        doctor.addService(service1);
                        doctorService.save(doctor, service1);
                    }
                }
            }

            request.getSession().removeAttribute(ID);
            return new CommandResult("/controller?command=watch_doctor", false);
        }
    }

    private CommandResult goBackWithError(HttpServletRequest request, String error) {
        request.setAttribute(error, true);
        return new CommandResult(ConfigurationManager.getProperty("path.page.edit_doctor"), false);
    }

    private Doctor searchDoctor(String id) throws DaoException {
        ServiceFactory factory = null;
        DoctorService service = null;
        try {
            factory = new ServiceFactory();
            service = factory.getService(DoctorService.class);
        } catch (DaoException | ConnectionPoolException e) {
            logger.log(Level.INFO, "service error");
        }
        if (service != null) {
            return service.findByIdentity(Integer.valueOf(id));
        } else return null;
    }
}

