package by.sazanchuk.finalTask.action;

import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.dao.connectionPool.ConnectionPoolException;
import by.sazanchuk.finalTask.dao.transaction.TransactionFactory;
import by.sazanchuk.finalTask.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.sazanchuk.finalTask.service.ServiceException;
import by.sazanchuk.finalTask.service.ServiceFactory;
import by.sazanchuk.finalTask.service.UserService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {
    private static final Logger log = LogManager.getLogger(LoginCommand.class);


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login != null && password != null){
            try {
                if (checkUser(login, password, request)) {return new CommandResult("controller?command=main", true);}
                else goBackWithError(request, "error null");
            } catch (DaoException e) {
            }
        }
        return goBackWithError(request, "authentication_error");

    }

    private boolean checkUser(String login, String password, HttpServletRequest request) throws DaoException {
        ServiceFactory factory = null;
        try {
            factory = new ServiceFactory(new TransactionFactory());
        } catch (DaoException | ConnectionPoolException e) {

        }

        UserService service = null;
        try {
            service = factory.getService(UserService.class);
        } catch (DaoException e) {

        }

        User user = null;

        user = service.findByLoginAndPassword(login, password);

        if (user.getPassword()!= null && user.getLogin()!= null){
            setAtributesToSession(user, request);
            return true;
        }
        else return false;
    }


    private CommandResult forwardToLoginWithError(HttpServletRequest request,String ERROR){
        request.setAttribute(ERROR, true);
        return new CommandResult( Page.REGISTER.getPage(), false);
    }

    private CommandResult goBackWithError(HttpServletRequest request, String error) {
        request.setAttribute(error, true);
        return new CommandResult(ConfigurationManager.getProperty("path.page.login"), false);
    }

    private void setAtributesToSession(User user, HttpServletRequest request) {

        HttpSession session = request.getSession();
        session.setAttribute(user.getName(), user);
    }
}
