package by.sazanchuk.finalTask.action;

import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.dao.connectionPool.ConnectionPoolException;
import by.sazanchuk.finalTask.dao.transaction.TransactionFactory;
import by.sazanchuk.finalTask.entity.Gender;
import by.sazanchuk.finalTask.entity.Role;
import by.sazanchuk.finalTask.entity.User;
import by.sazanchuk.finalTask.service.ServiceException;
import by.sazanchuk.finalTask.service.ServiceFactory;
import by.sazanchuk.finalTask.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegisterCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, DaoException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");

        if (login == null || password == null || name == null || email == null || phoneNumber == null || address == null){
            return forwardToLoginWithError(request, "incorrect_password_data");
        }

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

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setLogin(login);
        user.setAddress(address);
        user.setName(name);
        user.setPhoneNumber(Integer.valueOf(phoneNumber));
        user.setGender(Gender.WOMEN);
        user.setRole(Role.VISITOR);
        int userId = service.save(user);
        user.setId(userId);

        setAtributesToSession(user, request);

        return new CommandResult("controller?command=main", true);
    }

    private CommandResult forwardToLoginWithError(HttpServletRequest request,String ERROR){
        request.setAttribute(ERROR, true);
        return new CommandResult(Page.LOGIN.getPage(), false);
    }

    private void setAtributesToSession(User user, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("id", user.getId());
        session.setAttribute("role", user.getClass().getSimpleName().toLowerCase());
        session.setAttribute("user", user.getLogin());
    }

    private CommandResult goBackWithError(HttpServletRequest request, String error) {
        request.setAttribute(error, true);
        return new CommandResult(ConfigurationManager.getProperty("path.page.register"), false);
    }
}
