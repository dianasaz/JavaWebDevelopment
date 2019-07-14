package dao;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import entity.Pet;
import entity.Role;


import entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends BaseDao implements Dao<User> {
    private static final String INSERT_INTO_USER_ALL_INFORMATION = "INSERT INTO `mydatabase`.user (`login`, `email`, `password`, `role`) VALUES (?, ?, ?, ?)";
    private static final String SELECT_ALL_INFORMATION_FROM_USER_WITHOUT_ID = "SELECT `login`, `email`, `password`, `role` FROM `mydatabase`.user WHERE `user_id` = ?";
    private static final String UPDATE_USER = "UPDATE `mydatabase`.user SET `login` = ?, `email` = ?, `password` = ?, `role` = ? WHERE `user_id` = ?";
    private static final String DELETE_USER = "DELETE FROM `mydatabase`.user WHERE `user_id` = ?";
    private static final String SELECT_USER_ROLE_USER_ID_FROM_USER = "SELECT `user_id`, `role` FROM `mydatabase`.user WHERE `login` = ? AND `password` = ? AND `email` = ?";
    private static final String READ_ALL_INFORMATION_ABOUT_USER = "SELECT `user_id`, `login`, `email`, `password`, `role` FROM `mydatabase`.user ORDER BY `login`";


    private final Logger log = LogManager.getLogger(UserDao.class);

    public Integer create(User user) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(INSERT_INTO_USER_ALL_INFORMATION, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getRole().toString());
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
            } catch(SQLException e) {}
            try {
                if (statement != null) statement.close();
            } catch(SQLException e) {}
        }
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
                user.setEmail(resultSet.getString("email"));
                user.setRole(Role.setRole(resultSet.getString("role")));
            }
            return user;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
            } catch(SQLException e) {}
            try {
                if (statement != null) statement.close();
            } catch(SQLException e) {}
        }
    }

    public void update(User user) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE_USER);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getRole().toString());
            statement.setInt(5, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                if (statement != null)
                statement.close();
            } catch(SQLException e) {}
        }
    }

    public void delete(Integer id) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE_USER);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {}
        }
    }

    public User read(String login, String password, String email) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SELECT_USER_ROLE_USER_ID_FROM_USER);
            statement.setString(1, login);
            statement.setString(2, email);
            statement.setString(3, password);
            resultSet = statement.executeQuery();
            User user = null;
            if(resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("user_id"));
                user.setLogin(login);
                user.setPassword(password);
                user.setEmail(email);
                user.setRole(Role.setRole(resultSet.getString("role")));
            }
            return user;
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                if (resultSet != null)
                resultSet.close();
            } catch(SQLException e) {}
            try {
                if (statement != null)
                statement.close();
            } catch(SQLException e) {}
        }
    }

    public List<User> read() throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(READ_ALL_INFORMATION_ABOUT_USER );
            resultSet = statement.executeQuery();
            List<User> users = new ArrayList<>();
            User user = null;
            while(resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("user_id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setRole(Role.setRole(resultSet.getString("role")));
                users.add(user);
            }
            return users;
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                if (resultSet != null)
                resultSet.close();
            } catch(SQLException e) {}
            try {
                if (statement != null)
                statement.close();
            } catch(SQLException e) {}
        }
    }
}
