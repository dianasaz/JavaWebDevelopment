package service;

import dao.DaoException;
import dao.UserDao;
import entity.User;

import java.util.List;

public class UserServiceImpl extends ServiceImpl implements UserService {
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
    public void save(User user) throws DaoException {
        UserDao userDao = transaction.createDao(UserDao.class);
        if(user.getId() != null) {
            if(user.getPassword() != null) {
                user.setPassword(PasswordCode.CodeMD5(user.getPassword()));
            } else {
                User oldUser = userDao.read(user.getId());
                user.setPassword(oldUser.getPassword());
            }
            userDao.update(user);
        } else {
            user.setPassword(PasswordCode.CodeMD5(new String()));
            user.setId(userDao.create(user));
        }
        userDao.update(user);
    }

    @Override
    public void delete(Integer identity) throws DaoException {
        UserDao userDao = transaction.createDao(UserDao.class);
        if (findByIdentity(identity) != null) {
            userDao.delete(identity);
        }
    }
}
