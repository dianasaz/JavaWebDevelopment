package action;

import dao.DaoException;
import dao.connectionPool.ConnectionPoolException;
import dao.transaction.TransactionFactory;
import entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.ServiceException;
import service.ServiceFactory;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCommand implements Command {
    private static final Logger log = LogManager.getLogger(LoginCommand.class);
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login != null && password != null){
            try {
                if (checkUser(login, password)) {return new CommandResult(Page.HOME_PAGE.getPage(), true);}
                else return forwardToLoginWithError(request, "authentication_error");
            } catch (DaoException e) {
                e.printStackTrace();
            }
        }
        return forwardToLoginWithError(request, "authentication_error");

    }

    private boolean checkUser(String login, String password) throws DaoException {
        ServiceFactory factory = null;
        try {
            factory = new ServiceFactory(new TransactionFactory());
        } catch (DaoException | ConnectionPoolException e) {
            e.printStackTrace();
        }


        UserService service = null;
        try {
            service = factory.getService(UserService.class);
        } catch (DaoException e) {
            e.printStackTrace();
        }

        User user = null;

        user = service.findByLoginAndPassword(login, password);

        return user != null;
    }

    private CommandResult forwardToLoginWithError(HttpServletRequest request,String ERROR){
        request.setAttribute(ERROR, true);
        return new CommandResult( "/WEB-INF/login.jsp", false);
    }
}
