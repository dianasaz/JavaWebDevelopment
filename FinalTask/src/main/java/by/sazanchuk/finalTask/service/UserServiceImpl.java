package by.sazanchuk.finalTask.service;

import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.dao.UserDao;
import by.sazanchuk.finalTask.dao.connectionPool.ConnectionPoolException;
import by.sazanchuk.finalTask.entity.User;

import java.util.List;

public class UserServiceImpl extends ServiceImpl implements UserService {
    public UserServiceImpl() throws DaoException, ConnectionPoolException {
    }

    @Override
    public List<User> findAll() throws DaoException {
        UserDao userDao = transaction.createDao(UserDao.class);
        return userDao.read();
    }

    @Override
    public User findByIdentity(Integer identity) throws DaoException {
        UserDao userDao = transaction.createDao(UserDao.class);
        return userDao.read(identity);
    }

    @Override
    public User findByLoginAndPassword(String login, String password) throws DaoException {
        UserDao userDao = transaction.createDao(UserDao.class);
        User user = null;
        if (login != null && password != null) {
            user = userDao.read(login, PasswordCode.CodeMD5(password));
        }
        return user;
    }

    @Override
    public int save(User user) throws DaoException {
        UserDao userDao = transaction.createDao(UserDao.class);
        if(user.getId() != null) {
            if(user.getPassword() != null) {
                user.setPassword(PasswordCode.CodeMD5(user.getPassword()));
                user.setAddress(user.getAddress());
                user.setName(user.getName());
                user.setPhoneNumber(user.getPhoneNumber());
                user.setEmail(user.getEmail());
            } else {
                User oldUser = userDao.read(user.getId());
                user.setPassword(oldUser.getPassword());
                user.setAddress(user.getAddress());
                user.setName(user.getName());
                user.setPhoneNumber(user.getPhoneNumber());
                user.setEmail(user.getEmail());
            }
            userDao.update(user);
        } else {
            user.setPassword(PasswordCode.CodeMD5(user.getPassword()));
            user.setId(userDao.create(user));
        }
        userDao.update(user);
        return user.getId();
    }

    public boolean isExist(String login) throws DaoException {
        UserDao userDao = transaction.createDao(UserDao.class);
        return userDao.isExist(login);

    }


    @Override
    public void delete(Integer identity) throws DaoException {
        UserDao userDao = transaction.createDao(UserDao.class);
        if (findByIdentity(identity) != null) {
            userDao.delete(identity);
        }
    }
}
