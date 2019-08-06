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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class WatchDoctorCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, DaoException {
        List<Doctor> doctors = getAllDoctors();
        request.setAttribute("doctors", doctors);
        return new CommandResult(ConfigurationManager.getProperty("path.page.doctor"), false);
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

}
