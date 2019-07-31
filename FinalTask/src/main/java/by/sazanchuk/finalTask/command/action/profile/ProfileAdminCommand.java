package by.sazanchuk.finalTask.command.action.profile;

import by.sazanchuk.finalTask.command.ConfigurationManager;
import by.sazanchuk.finalTask.command.action.Command;
import by.sazanchuk.finalTask.command.action.CommandResult;
import by.sazanchuk.finalTask.command.action.authorization.LoginCommand;
import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.dao.connectionPool.ConnectionPoolException;
import by.sazanchuk.finalTask.entity.Pet;
import by.sazanchuk.finalTask.entity.User;
import by.sazanchuk.finalTask.service.PetService;
import by.sazanchuk.finalTask.service.ServiceException;
import by.sazanchuk.finalTask.service.ServiceFactory;
import by.sazanchuk.finalTask.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class ProfileAdminCommand implements Command {
    private static final Logger logger = LogManager.getLogger(LoginCommand.class);
    private static final String ID = "user_id";
    private static final String USER = "user";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, DaoException {
        User user = (User) request.getSession().getAttribute(USER);

        try {
            User u = null;
            if (user != null) {
                u = initializeUser(user.getId());
                List<Pet> pets = new ArrayList<>();
                pets = getPets(u.getId());
                setAttributesToSession(u, pets, request);
            }
            if (u != null) return new CommandResult(ConfigurationManager.getProperty("path.page.profileUser"), false);
        } catch (DaoException e) {
        }

        return goBackWithError(request, "error");
    }

    private User initializeUser(Integer id) throws DaoException, ServiceException {

        ServiceFactory factory = null;
        try {
            factory = new ServiceFactory();
        } catch (ConnectionPoolException e) {
        }
        UserService service = factory.getService(UserService.class);
        User user = service.findByIdentity(id);
        return user;
    }

    private List<Pet> getPets(Integer id) throws DaoException, ServiceException {

        ServiceFactory factory = null;
        try {
            factory = new ServiceFactory();
        } catch (ConnectionPoolException e) {
        }
        PetService service = factory.getService(PetService.class);
        List<Pet> pets = new ArrayList<>();
        pets = service.getPetsOfOneUser(id);
        return pets;
    }


    private void setAttributesToSession(User user, List<Pet> pets, HttpServletRequest request) {
        HttpSession session = request.getSession();
        //session.setAttribute("user_id", user.getId());
        // request.setAttribute("user", user);
        request.setAttribute("login", user.getLogin());
        request.setAttribute("email", user.getEmail());
        request.setAttribute("name", user.getName());
        request.setAttribute("phone", user.getPhoneNumber());
        request.setAttribute("address", user.getAddress());
        request.setAttribute("pets", pets);

    }

    private CommandResult goBackWithError(HttpServletRequest request, String error) {
        request.setAttribute(error, true);
        return new CommandResult(ConfigurationManager.getProperty("path.page.login"), false);
    }
}