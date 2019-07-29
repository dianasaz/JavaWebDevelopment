package by.sazanchuk.finalTask.service;

import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.entity.User;

import java.util.List;

public interface UserService extends Service{
    List<User> findAll() throws DaoException;

    User findByIdentity(Integer identity) throws DaoException;

    User findByLoginAndPassword(String login, String password) throws DaoException;

    int save(User user) throws DaoException;

    void delete(Integer identity) throws DaoException;

    boolean isExist(String login) throws DaoException;

    boolean searchEmail(String email) throws DaoException;
}
