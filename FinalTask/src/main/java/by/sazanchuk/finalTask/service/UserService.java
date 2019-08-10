package by.sazanchuk.finalTask.service;

import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.entity.User;

import java.util.List;

public interface UserService extends Service{
    List<User> findAll() throws ServiceException;

    User findByIdentity(Integer identity) throws ServiceException;

    User findByLoginAndPassword(String login, String password) throws ServiceException;

    int save(User user) throws ServiceException;

    void delete(Integer identity) throws ServiceException;

    boolean isExist(String login) throws ServiceException;

    boolean searchEmail(String email) throws ServiceException;
}
