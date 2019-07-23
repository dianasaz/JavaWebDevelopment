package by.sazanchuk.finalTask.action.command;

import by.sazanchuk.finalTask.action.ConfigurationManager;
import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.dao.connectionPool.ConnectionPoolException;
import by.sazanchuk.finalTask.entity.User;
import by.sazanchuk.finalTask.service.ServiceException;
import by.sazanchuk.finalTask.service.ServiceFactory;
import by.sazanchuk.finalTask.service.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ProfileCommand implements Command {
    private static final Logger logger = LogManager.getLogger(LoginCommand.class);
    private static final String ID = "user_id";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        User user = (User) request.getSession().getAttribute("user");

        try {
            User u = null;
            if (user != null){
            u = initializeUser(user.getId(), request);}
            if (u != null)  return new CommandResult(ConfigurationManager.getProperty("path.page.profile"), false);
        } catch (DaoException e) {
        }

           return goBackWithError(request, "error");
    }

    private User initializeUser(Integer id, HttpServletRequest request) throws DaoException, ServiceException {

        ServiceFactory factory = null;
        try {
            factory = new ServiceFactory();
        } catch (ConnectionPoolException e) {}
        UserService service = factory.getService(UserService.class);
        User user = service.findByIdentity(id);
            setAtributesToSession(user, request);
            return user;
    }

    private void setAtributesToSession(User user, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("user_id", user.getId());
        request.setAttribute("user", user);
        request.setAttribute("login", user.getLogin());
        request.setAttribute("email", user.getEmail());
        request.setAttribute("name", user.getName());
        request.setAttribute("phone", user.getPhoneNumber());


    }

    private CommandResult goBackWithError(HttpServletRequest request, String error) {
        request.setAttribute(error, true);
        return new CommandResult(ConfigurationManager.getProperty("path.page.login"), false);
    }
}
