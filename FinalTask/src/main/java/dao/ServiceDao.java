package dao;

import entity.Service;
import entity.ServiceList;
import exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ServiceDao extends BaseDao implements Dao<Service> {
        private final Logger log = LogManager.getLogger(ServiceDao.class);

        @Override
        public Integer create(Service entity) throws PersistentException {
            String sql = "INSERT INTO `mydatabase`.service_info (`service_id`, `service`, `price`) VALUES (?, ?, ?)";
            PreparedStatement statement = null;
            ResultSet resultSet = null;
            try {
                statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                statement.setInt(1, entity.getKind().getIdentity());
                statement.setInt(2, entity.getPrice());
                statement.setInt(3, entity.getServiceID());
                statement.executeUpdate();
                resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                } else {
                    log.error("There is no autoincremented index after trying to add record into table `users`");
                    throw new PersistentException();
                }
            } catch (SQLException e) {
                throw new PersistentException(e);
            } finally {
                try {
                    resultSet.close();
                } catch(SQLException | NullPointerException e) {}
                try {
                    statement.close();
                } catch(SQLException | NullPointerException e) {}
            }
        }

        @Override
        public Service read(Integer id) throws PersistentException {
            String sql = "SELECT `service`, `price` FROM `mydatabase`.service_info WHERE `service_id` = ?";
            PreparedStatement statement = null;
            ResultSet resultSet = null;
            try {
                statement = connection.prepareStatement(sql);
                statement.setInt(1, id);
                resultSet = statement.executeQuery();
                Service service = null;
                if (resultSet.next()) {
                    service = new Service();
                    service.setServiceID(id);
                    service.setPrice(resultSet.getInt("price"));
                    service.setKind(ServiceList.getById(resultSet.getInt("service")));
                }
                return service;
            } catch (SQLException e) {
                throw new PersistentException(e);
            } finally {
                try {
                    resultSet.close();
                } catch(SQLException | NullPointerException e) {}
                try {
                    statement.close();
                } catch(SQLException | NullPointerException e) {}
            }
        }

        @Override
        public void update(Service entity) throws PersistentException {
            String sql = "UPDATE `mydatabase`.service_info SET `service` = ?, `price` = ? = ? WHERE `service_id` = ?";
            PreparedStatement statement = null;
            try {
                statement = connection.prepareStatement(sql);
                statement.setInt(1, entity.getServiceID());
                statement.setInt(2, entity.getKind().getIdentity());
                statement.setDouble(3, entity.getPrice());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new PersistentException(e);
            } finally {
                try {
                    statement.close();
                } catch(SQLException | NullPointerException e) {}
            }
        }

        @Override
        public void delete(Integer id) throws PersistentException {
            String sql = "DELETE FROM `mydatabase`.service_info WHERE `service_id` = ?";
            PreparedStatement statement = null;
            try {
                statement = connection.prepareStatement(sql);
                statement.setInt(1, id);
                statement.executeUpdate();
            } catch(SQLException e) {
                throw new PersistentException(e);
            } finally {
                try {
                    statement.close();
                } catch(SQLException | NullPointerException e) {}
            }
        }

}
