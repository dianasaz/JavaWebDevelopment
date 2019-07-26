package by.sazanchuk.finalTask.dao;

import by.sazanchuk.finalTask.entity.Role;


import by.sazanchuk.finalTask.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends BaseDao implements Dao<User> {
    private static final String INSERT_INTO_USER_ALL_INFORMATION = "INSERT INTO `mydatabase`.user (`login`, `password`, `role`, `email`, `name`, `phoneNumber`, `address`) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_INFORMATION_FROM_USER_WITHOUT_ID = "SELECT `login`, `password`, `role`, `email`, `name`, `phoneNumber`, `address` FROM `mydatabase`.user WHERE `user_id` = ?";
    private static final String UPDATE_USER = "UPDATE `mydatabase`.user SET `login` = ?, `password` = ?, `role` = ?, `email` = ?, `name` = ?, `phoneNumber` = ?, `address` = ? WHERE `user_id` = ?";
    private static final String DELETE_USER = "DELETE FROM `mydatabase`.user WHERE `user_id` = ?";
    private static final String READ_ALL_INFORMATION_ABOUT_USER = "SELECT `user_id`, `login`, `password`, `role`, `email`, `name`, `phoneNumber`, `address` FROM `mydatabase`.user ORDER BY `login`";
    private static final String SEARCH_LOGIN = "SELECT `login` FROM `mydatabase`.user WHERE `login` = ?";
    private static final String SELECT_USER_ID_FROM_USER = "SELECT `user_id` FROM `mydatabase`.user WHERE `login` = ? AND `password` = ?";


    private final Logger log = LogManager.getLogger(UserDao.class);

    public Integer create(User user) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(INSERT_INTO_USER_ALL_INFORMATION, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRole().toString());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getName());
            statement.setInt(6, user.getPhoneNumber());
            statement.setString(7, user.getAddress());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                log.error("There is no autoincremented index after trying to add record into table `users`");
                throw new DaoException();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
            } catch (SQLException e) {
            }
        }
    }

    public boolean isExist(String login) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            statement = connection.prepareStatement(SEARCH_LOGIN, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
            }
        } catch (SQLException e) {
            throw new DaoException();
        }

        return user != null;
    }

    public User read(Integer id) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SELECT_ALL_INFORMATION_FROM_USER_WITHOUT_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = new User();
                user.setId(id);
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(Role.setRole(resultSet.getString("role")));
                user.setEmail(resultSet.getString("email"));
                user.setName(resultSet.getString("name"));
                user.setPhoneNumber(resultSet.getInt("phoneNumber"));
                user.setAddress(resultSet.getString("address"));
            }
            return user;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
            } catch (SQLException e) {
            }
        }
    }

    public void update(User user) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE_USER);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRole().toString());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getName());
            statement.setInt(6, user.getPhoneNumber());
            statement.setString(7, user.getAddress());
            statement.setInt(8, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void delete(Integer id) throws DaoException {
        PreparedStatement statement = null;
        PreparedStatement statement1 = null;
        try {
            statement = connection.prepareStatement(DELETE_USER);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public User read(String login, String password) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SELECT_USER_ID_FROM_USER);
            statement.setString(1, login);
            statement.setString(2, password);

            resultSet = statement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("user_id"));
                user.setLogin(login);
                user.setPassword(password);
            }
            return user;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
            } catch (SQLException e) {
            }
        }
    }

    public List<User> read() throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(READ_ALL_INFORMATION_ABOUT_USER);
            resultSet = statement.executeQuery();
            List<User> users = new ArrayList<>();
            User user = null;
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("user_id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(Role.setRole(resultSet.getString("role")));
                user.setEmail(resultSet.getString("email"));
                user.setName(resultSet.getString("name"));
                user.setPhoneNumber(resultSet.getInt("phoneNumber"));
                user.setAddress(resultSet.getString("address"));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
            } catch (SQLException e) {
            }
        }
    }
}
