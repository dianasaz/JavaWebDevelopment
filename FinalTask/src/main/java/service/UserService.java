package service;

import dao.DaoException;
import entity.User;

import java.util.List;

public interface UserService extends Service{
    List<User> findAll() throws DaoException;

    User findByIdentity(Integer identity) throws DaoException;

    User findByLoginAndPasswordAndEmail(String login, String password, String email) throws DaoException;

    void save(User user) throws DaoException;

    void delete(Integer identity) throws DaoException;
}
