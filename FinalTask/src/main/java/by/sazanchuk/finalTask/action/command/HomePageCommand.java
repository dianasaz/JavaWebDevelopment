package by.sazanchuk.finalTask.action.command;

import by.sazanchuk.finalTask.action.Page;
import by.sazanchuk.finalTask.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomePageCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return new CommandResult(Page.HOME_PAGE.getPage(), false);
    }
}
