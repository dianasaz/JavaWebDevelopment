package by.sazanchuk.finalTask.command.action.profile;

import by.sazanchuk.finalTask.command.action.Command;
import by.sazanchuk.finalTask.command.action.CommandResult;
import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.entity.User;
import by.sazanchuk.finalTask.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProfileCommand implements Command {
    private static final String USER_ROLE = "user_role";
    private static final String USER = "user";
    private static final String VISITOR = "visitor";
    private static final String ADMIN = "admin";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, DaoException {
        User user = (User) request.getSession().getAttribute(USER);

        if (user.getRole().getName().equals(VISITOR)) return new CommandResult("/controller?command=profile_user", false);
        else return new CommandResult("/controller?command=profile_admin", false);
    }
}
