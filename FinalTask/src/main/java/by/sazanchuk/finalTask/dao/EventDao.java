package by.sazanchuk.finalTask.dao;

import by.sazanchuk.finalTask.entity.Event;
import by.sazanchuk.finalTask.entity.Pet;
import by.sazanchuk.finalTask.entity.PetList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EventDao extends BaseDao implements Dao<Event> {
    private static final String INSERT_ALL_INFO = "INSERT INTO event (`service_id`, `pet_id`, `date`, `id`, `doctor_id`) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_INFO = "SELECT `service_id`, `doctor_id`, `pet_id`, `date` FROM event WHERE `id` = ?";
    private static final String UPDATE_ALL_INFO = "UPDATE event SET `service_id` = ?, `doctor_id` = ?, `pet_id`= ?, `date` = ? WHERE `id` = ?";
    private static final String DELETE_FROM_DATABASE = "DELETE FROM event WHERE `id` = ?";
    private static final String SELECT_ALL_EVENTS_WITH_ONE_PET = "SELECT `id` FROM event WHERE `pet_id` = ?";
    private static final String READ_ALL_INFORMATION_ABOUT_EVENT = "SELECT `id`, `date`, `pet_id`, `service_id`, `doctor_id` FROM event ORDER BY `date`";

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
                event.setDate(resultSet.getDate("date_id"));
                event.setDoctor(resultSet.getInt("doctor_id"));
                event.setDoctor(resultSet.getInt("pet_id"));
                event.setDoctor(resultSet.getInt("service_id"));
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


    public List<Event> readEventsOfOnePet(int pet_id) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SELECT_ALL_EVENTS_WITH_ONE_PET);
            statement.setInt(1, pet_id);
            resultSet = statement.executeQuery();
            List<Event> events = null;
            Event event = null;
            while (resultSet.next()) {
                event = new Event();
                event.setIdentity(resultSet.getInt("id"));
                events.add(event);
            }
            return events;
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

    public List<Event> read() throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(READ_ALL_INFORMATION_ABOUT_EVENT );
            resultSet = statement.executeQuery();
            List<Event> events = new ArrayList<>();
            Event event = null;
            while(resultSet.next()) {
                event = new Event();
                event.setIdentity(resultSet.getInt("id"));
                event.setDate(resultSet.getDate("date_id"));
                event.setDoctor(resultSet.getInt("doctor_id"));
                event.setDoctor(resultSet.getInt("pet_id"));
                event.setDoctor(resultSet.getInt("service_id"));
                events.add(event);
            }
            return events;
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
