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

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddServiceCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AddServiceCommand.class);
    private static final String NAME = "name";
    private static final String PRICE = "price";
    private static final String ERROR_NULL = "price";


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, DaoException {
        String name = request.getParameter(NAME);
        Integer price = Integer.valueOf(request.getParameter(PRICE));

        if (name == null || price == 0) {
            logger.log(Level.INFO, "name or price is null");
            return goBackWithError(request, ERROR_NULL);
        } else {
            if (!searchService(name)) {
                createService(name, price, request);
                return new CommandResult("/controller?command=services", false); //TODO
            } else {
                return new CommandResult("/", false);
            }
        }
    }


    private CommandResult goBackWithError(HttpServletRequest request, String error) {
        request.setAttribute(error, true);
        return new CommandResult(ConfigurationManager.getProperty("path.page.login"), false);
    }

    private Integer createService(String name, Integer price, HttpServletRequest request) {

        ServiceFactory factory = null;
        ServiceService service = null;
        try {
            factory = new ServiceFactory();
            service = factory.getService(ServiceService.class);
        } catch (DaoException | ConnectionPoolException e) {
            logger.log(Level.INFO, "service error");
        }

        Service s = new Service();
        s.setName(name);
        s.setPrice(price);

        try {
            assert service != null;
            service.save(s);
        } catch (DaoException e) {
            logger.log(Level.INFO, "save error");
        }

        return s.getIdentity();
    }

    private boolean searchService(String name) throws DaoException {

        ServiceFactory factory = null;
        ServiceService service = null;
        try {
            factory = new ServiceFactory();
            service = factory.getService(ServiceService.class);
        } catch (DaoException | ConnectionPoolException e) {
            logger.log(Level.INFO, "service error");
        }
        assert service != null;
        return service.searchService(name);
    }
}
