package by.sazanchuk.finalTask.command.action;

import by.sazanchuk.finalTask.command.Page;
import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.dao.connectionPool.ConnectionPoolException;
import by.sazanchuk.finalTask.entity.Service;
import by.sazanchuk.finalTask.service.ServiceException;
import by.sazanchuk.finalTask.service.ServiceFactory;
import by.sazanchuk.finalTask.service.ServiceService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class HomePageCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        List<Service> services = null;
        try {
            services = getAllService();
        } catch (DaoException e) {
        }
        setAttributesToSession(services, request);
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


    private void setAttributesToSession(List<Service> services, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("services", services);

    }

}
