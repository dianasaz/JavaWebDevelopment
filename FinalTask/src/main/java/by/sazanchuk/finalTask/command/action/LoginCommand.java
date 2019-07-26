package by.sazanchuk.finalTask.command.action;

import by.sazanchuk.finalTask.command.ConfigurationManager;
import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.dao.connectionPool.ConnectionPoolException;
import by.sazanchuk.finalTask.entity.User;
import by.sazanchuk.finalTask.service.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.sazanchuk.finalTask.service.ServiceException;
import by.sazanchuk.finalTask.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {
    private static final Logger logger = LogManager.getLogger(LoginCommand.class);
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String ERROR_LOGIN = "error_login";
    private static final String ERROR_PASSWORD = "error_password";
    private static final String ERROR_AUTHENTIFICATION = "error_authentification";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        if (login == null || login.isEmpty()) {
            logger.log(Level.INFO, "invalid login was received");
            return goBackWithError(request, ERROR_LOGIN);
        }
        if (password == null || password.isEmpty()) {
            logger.log(Level.INFO, "invalid password was received");
            return goBackWithError(request, ERROR_PASSWORD);
        }
        boolean userExist = false;
        try {
            userExist = initializeUser(login, password, request);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        if (userExist) {
            logger.log(Level.INFO, "user authorized with login - " + login);
            return new CommandResult("/controller?command=profile", false);
        } else {
            logger.log(Level.INFO, "user with such login and password doesn't exist");
            return goBackWithError(request, ERROR_AUTHENTIFICATION);
        }
    }

    private boolean initializeUser(String login, String password, HttpServletRequest request) throws DaoException, ServiceException {

        ServiceFactory factory = null;
        try {
            factory = new ServiceFactory();
        } catch (ConnectionPoolException e) {}
        UserService service = factory.getService(UserService.class);
        User user = service.findByLoginAndPassword(login, password);
        if (user != null && user.getId() != null) {
            setAttributesToSession(user, request);
            return true;
        } else {
            return false;
        }
    }

    private void setAttributesToSession(User user, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("user_id", user.getId());
        session.setAttribute("user", user);
        request.setAttribute("user", true);
    }

    private CommandResult goBackWithError(HttpServletRequest request, String error) {
        request.setAttribute(error, true);
        return new CommandResult(ConfigurationManager.getProperty("path.page.login"), false);
    }
}
