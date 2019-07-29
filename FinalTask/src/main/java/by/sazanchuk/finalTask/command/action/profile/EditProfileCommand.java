package by.sazanchuk.finalTask.command.action.profile;

import by.sazanchuk.finalTask.command.ConfigurationManager;
import by.sazanchuk.finalTask.command.action.Command;
import by.sazanchuk.finalTask.command.action.CommandResult;
import by.sazanchuk.finalTask.command.action.authorization.RegisterCommand;
import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.dao.connectionPool.ConnectionPoolException;
import by.sazanchuk.finalTask.entity.Role;
import by.sazanchuk.finalTask.entity.User;
import by.sazanchuk.finalTask.service.ServiceException;
import by.sazanchuk.finalTask.service.ServiceFactory;
import by.sazanchuk.finalTask.service.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.print.attribute.standard.MediaSize;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class EditProfileCommand implements Command {
    private static final Logger logger = LogManager.getLogger(RegisterCommand.class);

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String NAME = "name";
    private static final String ADDRESS = "address";
    private static final String PHONE_NUMBER = "phoneNumber";
    private static final String EMAIL = "email";
    private static final String ERROR_REGISTRATION = "error_registration";
    private static final String ERROR = "error_";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        User olduser = (User) request.getSession().getAttribute("user");


        Map<String, String> parameters = new HashMap<>();
        parameters.put(LOGIN, request.getParameter(LOGIN));
        parameters.put(PASSWORD, request.getParameter(PASSWORD));
        parameters.put(NAME, request.getParameter(NAME));
        parameters.put(ADDRESS, request.getParameter(ADDRESS));
        parameters.put(PHONE_NUMBER, request.getParameter(PHONE_NUMBER));
        parameters.put(EMAIL, request.getParameter(EMAIL));

        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            if (entry.getValue() == null || entry.getValue().isEmpty()) {
                return goBackWithError(request, ERROR + entry.getKey());
            }
        }
        try {
            updateUser(parameters, olduser, request);
            logger.log(Level.INFO, "user registrated and authorized with login - " + parameters.get(LOGIN));
            return new CommandResult("controller?command=profile", true);
        } catch (DaoException | ConnectionPoolException e) {
            return new CommandResult("controller?command=edit_profile");
        }

    }

    private boolean checkIfUserExist(String login) throws DaoException, ConnectionPoolException {

        ServiceFactory factory = new ServiceFactory();

        UserService service = factory.getService(UserService.class);
        return service.isExist(login);
    }

    private void updateUser(Map<String, String> parameters, User olduser, HttpServletRequest request) throws DaoException, ServiceException, ConnectionPoolException {
        User user = new User();
  /*      if (parameters.get(LOGIN) == null || parameters.get(LOGIN).isEmpty()){
            parameters.put(LOGIN, olduser.getLogin());
        } else parameters.put(LOGIN, request.getParameter(LOGIN));
        if (parameters.get(PASSWORD) == null || parameters.get(PASSWORD).isEmpty()){
            parameters.put(PASSWORD, olduser.getPassword());
        } else parameters.put(PASSWORD, request.getParameter(PASSWORD));
        if (parameters.get(NAME) == null || parameters.get(NAME).isEmpty()){
            parameters.put(NAME, olduser.getName());
        } else parameters.put(NAME, request.getParameter(NAME));
        if (parameters.get(ADDRESS) == null || parameters.get(ADDRESS).isEmpty()){
            parameters.put(ADDRESS, olduser.getAddress());
        } else parameters.put(ADDRESS, request.getParameter(ADDRESS));
        if (parameters.get(PHONE_NUMBER) == null || parameters.get(PHONE_NUMBER).isEmpty()){
            parameters.put(PHONE_NUMBER, String.valueOf(olduser.getPhoneNumber()));
        } else parameters.put(PHONE_NUMBER, request.getParameter(PHONE_NUMBER));
        if (parameters.get(EMAIL) == null || parameters.get(EMAIL).isEmpty()){
            parameters.put(EMAIL, olduser.getEmail());
        } else parameters.put(EMAIL, request.getParameter(EMAIL));
*/
        user.setLogin(parameters.get(LOGIN));
        user.setPassword(parameters.get(PASSWORD));
        user.setRole(Role.VISITOR);
        user.setAddress(parameters.get(ADDRESS));
        user.setEmail(parameters.get(EMAIL));
        user.setPhoneNumber(Integer.valueOf(parameters.get(PHONE_NUMBER)));
        user.setName(parameters.get(NAME));
        user.setId(olduser.getId());


        ServiceFactory factory = new ServiceFactory();
        UserService service = factory.getService(UserService.class);

        int id = service.save(user);
        if (id != 0) {
            user.setId(id);
        } else {
            throw new ServiceException("Can't save user!");
        }
        setAttributesToSession(user, request);

    }

    private void setAttributesToSession(User user, HttpServletRequest request) {
        HttpSession session = request.getSession();
      //  session.setAttribute("user", user);
    }

    private CommandResult goBackWithError(HttpServletRequest request, String error) {
        request.setAttribute(error, true);
        return new CommandResult(ConfigurationManager.getProperty("path.page.edit_profile"), false);
    }
}
