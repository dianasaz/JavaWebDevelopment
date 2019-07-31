package by.sazanchuk.finalTask.command.action.service;

import by.sazanchuk.finalTask.command.ConfigurationManager;
import by.sazanchuk.finalTask.command.action.Command;
import by.sazanchuk.finalTask.command.action.CommandResult;
import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.dao.connectionPool.ConnectionPoolException;
import by.sazanchuk.finalTask.entity.Service;
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

public class WatchServiceCommand implements Command {
    private static final Logger logger = LogManager.getLogger(WatchServiceCommand.class);
    private static final String ID = "user_id";
    private static final String USER = "user";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        try {
            List<Service> services = getAllService();
            setAttributesToSession(services, request);
            return new CommandResult(ConfigurationManager.getProperty("path.page.service"), false);
        } catch (DaoException e1) {
            return new CommandResult("/controller?command=home_page", false);
        }
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


    private void setAttributesToSession(List<Service> services, HttpServletRequest request) {
        HttpSession session = request.getSession();
        //session.setAttribute("user_id", user.getId());
        // request.setAttribute("user", user);
        request.setAttribute("services", services);

    }

    private CommandResult goBackWithError(HttpServletRequest request, String error) {
        request.setAttribute(error, true);
        return new CommandResult(ConfigurationManager.getProperty("path.page.login"), false);
    }
}
