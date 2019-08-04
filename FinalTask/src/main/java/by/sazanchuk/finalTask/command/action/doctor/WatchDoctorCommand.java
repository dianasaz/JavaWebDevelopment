package by.sazanchuk.finalTask.command.action.doctor;

import by.sazanchuk.finalTask.command.ConfigurationManager;
import by.sazanchuk.finalTask.command.action.Command;
import by.sazanchuk.finalTask.command.action.CommandResult;
import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.dao.connectionPool.ConnectionPoolException;
import by.sazanchuk.finalTask.entity.Doctor;
import by.sazanchuk.finalTask.service.DoctorService;
import by.sazanchuk.finalTask.service.ServiceException;
import by.sazanchuk.finalTask.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class WatchDoctorCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        try {
            List<Doctor> doctors = getAllDoctors();
            setAttributesToSession(doctors, request);
            return new CommandResult(ConfigurationManager.getProperty("path.page.doctor"), false);
        } catch (DaoException e1) {
            return new CommandResult("/controller?command=home_page", false);
        }
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


    private void setAttributesToSession(List<Doctor> doctors, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("doctors", doctors);

    }
}
