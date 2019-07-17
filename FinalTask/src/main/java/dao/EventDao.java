package dao;

import entity.Event;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EventDao extends BaseDao implements Dao<Event> {
    private static final String INSERT_ALL_INFO = "INSERT INTO `mydatabase`.event (`service_id`, `pet_id`, `date`, `id`, `doctor_id`) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_INFO = "SELECT `service_id`, `doctor_id`, `pet_id`, `date` FROM `mydatabase`.event WHERE `id` = ?";
    private static final String UPDATE_ALL_INFO = "UPDATE `mydatabase`.event SET `service_id` = ?, `doctor_id` = ?, `pet_id`= ?, `date` = ? WHERE `id` = ?";
    private static final String DELETE_FROM_DATABASE = "DELETE FROM `mydatabase`.event WHERE `id` = ?";

    private final Logger log = LogManager.getLogger(ServiceDao.class);

    @Override
    public Integer create(Event entity) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(INSERT_ALL_INFO, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, entity.getIdentity());
            statement.setInt(2, entity.getPet());
            statement.setInt(3, entity.getService());
            statement.setDate(4, (Date) entity.getDate());
            statement.setInt(5, entity.getDoctor());

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
    public Event read(Integer id) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SELECT_ALL_INFO);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            Event event = null;
            if (resultSet.next()) {
                event = new Event();
                event.setIdentity(id);
                event.setDate(resultSet.getDate("date"));
                event.setDoctor(resultSet.getInt("doctor"));
                event.setDoctor(resultSet.getInt("pet"));
                event.setDoctor(resultSet.getInt("service"));
            }
            return event;
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
    public void update(Event entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE_ALL_INFO);
            statement.setInt(1, entity.getDoctor());
            statement.setDate(2, (Date) entity.getDate());
            statement.setInt(3, entity.getService());
            statement.setInt(4, entity.getPet());
            statement.setInt(5, entity.getIdentity());
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

}
