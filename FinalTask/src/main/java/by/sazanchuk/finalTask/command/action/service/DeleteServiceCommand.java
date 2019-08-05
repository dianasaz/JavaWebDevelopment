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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteServiceCommand implements Command {
    private static final String NAME = "name";
    private static final String USER_ID = "user_id";
    private static final String ERROR_DELETE = "error_delete";
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, DaoException {
        String name = request.getParameter(NAME);
    // User user = (User) request.getSession().getAttribute(USER);

        try {
        if (name != null | !name.isEmpty()) {
            deleteService(name, request);
            //logger.log(Level.INFO, "user registrated and authorized with login - " + parameters.get(LOGIN));
            return new CommandResult("/controller?command=watch_service", false);
        } else {
            request.setAttribute(ERROR_DELETE, true);
            return goBackWithError(request, "can't delete service");
        }
    } catch (DaoException | ConnectionPoolException e) {
        return goBackWithError(request, "ERROR");
        //throw new ServiceException(e);

    }

}


    private void deleteService(String name, HttpServletRequest request) throws DaoException, ServiceException, ConnectionPoolException {

            ServiceFactory factory = new ServiceFactory();

            ServiceService service = factory.getService(ServiceService.class);

            Service service1;
            service1 = service.searchServiceByName(name);
            if (service1 != null) {
                service.delete(service1.getIdentity());
            }

    }


    private CommandResult goBackWithError(HttpServletRequest request, String error) {
        request.setAttribute(error, true);
        return new CommandResult(ConfigurationManager.getProperty("path.page.service"), false);
    }
}



