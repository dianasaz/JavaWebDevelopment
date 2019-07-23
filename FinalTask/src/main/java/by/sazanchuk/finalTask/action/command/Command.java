package by.sazanchuk.finalTask.action.command;

import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, DaoException;
}