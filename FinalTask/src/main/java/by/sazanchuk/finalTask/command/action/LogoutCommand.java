package by.sazanchuk.finalTask.command.action;

import by.sazanchuk.finalTask.command.Page;
import by.sazanchuk.finalTask.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {
    private static final Logger logger = LogManager.getLogger(LoginCommand.class);
    private static final String ID = "user_id";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute(ID);
        logger.info("user was above: id:" + userId);
        session.removeAttribute(ID);
        return new CommandResult(Page.LOGIN.getPage(), false);
    }
}
