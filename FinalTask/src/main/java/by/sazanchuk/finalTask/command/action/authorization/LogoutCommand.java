package by.sazanchuk.finalTask.command.action.authorization;

import by.sazanchuk.finalTask.command.Page;
import by.sazanchuk.finalTask.command.action.Command;
import by.sazanchuk.finalTask.command.action.CommandResult;
import by.sazanchuk.finalTask.command.action.authorization.LoginCommand;
import by.sazanchuk.finalTask.entity.User;
import by.sazanchuk.finalTask.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {
    private static final Logger logger = LogManager.getLogger(LoginCommand.class);
    private static final String ID = "user_id";
    private static final String USER = "user";


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response)  {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute(ID);
        logger.info("user was above: id:" + userId);
        session.removeAttribute(ID);
        session.removeAttribute(USER);

        return new CommandResult(Page.HOME_PAGE.getPage(), false);
    }
}
