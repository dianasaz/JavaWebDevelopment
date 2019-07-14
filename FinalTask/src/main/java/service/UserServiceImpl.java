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
    public User findByLoginAndPasswordAndEmail(String login, String password, String email) throws DaoException {
        UserDao userDao = transaction.createDao(UserDao.class);
        User user = null;
        if (login != null && password != null && email != null) {
            user = userDao.read(login, password, email);
        }
        return user;
    }

    @Override
    public void save(User user) throws DaoException {
        UserDao userDao = transaction.createDao(UserDao.class);
        if(user.getId() != null) {
            if(user.getPassword() != null) {
                user.setPassword(user.getPassword());
            } else {
                User oldUser = userDao.read(user.getId());
                user.setPassword(oldUser.getPassword());
            }
            userDao.update(user);
        } else {
            user.setPassword(new String()); //TODO шифрование пароля
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
