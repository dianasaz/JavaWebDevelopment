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
    private final Logger log = LogManager.getLogger(ServiceDao.class);

    @Override
    public Integer create(Event entity) throws DaoException {
        String sql = "INSERT INTO `mydatabase`.event (`service_id`, `pet`, `date`, `event_id`, `doctor`) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
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
                resultSet.close();
            } catch(SQLException | NullPointerException e) {}
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {}
        }
    }

    @Override
    public Event read(Integer id) throws DaoException {
        String sql = "SELECT `service_id`, `doctor`, `pet`, `date` FROM `mydatabase`.event WHERE `event_id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
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
                resultSet.close();
            } catch(SQLException | NullPointerException e) {}
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {}
        }
    }

    @Override
    public void update(Event entity) throws DaoException {
        String sql = "UPDATE `mydatabase`.event SET `service_id` = ?, `doctor` = ?, `pet`= ?, `date` = ? WHERE `service_id` = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
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
                statement.close();
            } catch(SQLException | NullPointerException e) {}
        }
    }

    @Override
    public void delete(Integer id) throws DaoException {
        String sql = "DELETE FROM `mydatabase`.event WHERE `event_id` = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
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

}
