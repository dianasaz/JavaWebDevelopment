package by.sazanchuk.finalTask.dao;

import by.sazanchuk.finalTask.entity.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceDao extends BaseDao implements Dao<Service> {
    private static final String SELECT_ALL_INFORMATION_ABOUT_SERVICE = "SELECT `name`, `price`, `id` FROM `mydatabase`.by.sazanchuk.finalTask.service ORDER BY `name`";
    private static final String DELETE_FROM_DATABASE = "DELETE FROM `mydatabase`.by.sazanchuk.finalTask.service WHERE `id` = ?";
    private static final String UPDATE_NAME_AND_PRICE = "UPDATE `mydatabase`.by.sazanchuk.finalTask.service SET `name` = ?, `price` = ? = ? WHERE `id` = ?";
    private static final String SELECT_NAME_AND_PRICE = "SELECT `name`, `price` FROM `mydatabase`.by.sazanchuk.finalTask.service WHERE `id` = ?";
    private static final String INSERT_ALL_INFORMATION = "INSERT INTO `mydatabase`.by.sazanchuk.finalTask.service (`id`, `name`, `price`) VALUES (?, ?, ?)";


    private final Logger log = LogManager.getLogger(ServiceDao.class);

        @Override
        public Integer create(Service entity) throws DaoException {
            PreparedStatement statement = null;
            ResultSet resultSet = null;
            try {
                statement = connection.prepareStatement(INSERT_ALL_INFORMATION, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, entity.getName());
                statement.setInt(2, entity.getPrice());
                statement.setInt(3, entity.getIdentity());
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

        @Override
        public Service read(Integer id) throws DaoException {
            PreparedStatement statement = null;
            ResultSet resultSet = null;
            try {
                statement = connection.prepareStatement(SELECT_NAME_AND_PRICE);
                statement.setInt(1, id);
                resultSet = statement.executeQuery();
                Service service = null;
                if (resultSet.next()) {
                    service = new Service();
                    service.setIdentity(id);
                    service.setPrice(resultSet.getInt("price"));
                    service.setName(resultSet.getString("name"));
                }
                return service;
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

        @Override
        public void update(Service entity) throws DaoException {
            PreparedStatement statement = null;
            try {
                statement = connection.prepareStatement(UPDATE_NAME_AND_PRICE);
                statement.setInt(1, entity.getIdentity());
                statement.setString(2, entity.getName());
                statement.setDouble(3, entity.getPrice());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new DaoException(e);
            } finally {
                try {
                    if (statement != null) statement.close();
                } catch(SQLException e) {}
            }
        }

        @Override
        public void delete(Integer id) throws DaoException {
            PreparedStatement statement = null;
            try {
                statement = connection.prepareStatement(DELETE_FROM_DATABASE);
                statement.setInt(1, id);
                statement.executeUpdate();
            } catch(SQLException e) {
                throw new DaoException(e);
            } finally {
                try {
                    if (statement != null) statement.close();
                } catch(SQLException e) {}
            }
        }

    public List<Service> read() throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SELECT_ALL_INFORMATION_ABOUT_SERVICE);
            resultSet = statement.executeQuery();
            List<Service> services = new ArrayList<>();
            Service service = null;
            while(resultSet.next()) {
                service = new Service();
                service.setName(resultSet.getString("name"));
                service.setPrice(resultSet.getInt("price"));
                service.setIdentity(resultSet.getInt("id"));
                services.add(service);
            }
            return services;
        } catch(SQLException e) {
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
}