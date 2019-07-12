package dao;

import entity.Event;
import entity.Pet;
import entity.PetList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class PetDao extends BaseDao implements Dao<Pet> {
    private final Logger log = LogManager.getLogger(PetDao.class);

    @Override
    public Integer create(Pet entity) throws DaoException {
        String sql = "INSERT INTO `mydatabase`.pet_info (`pet_id`, `name`, `kind`, `age`, `weight`, `event`) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getIdentity());
            statement.setInt(3, entity.getAge());
            statement.setDouble(4, entity.getWeight());
            statement.setInt(5, entity.getKind().getIdentity());
            statement.setString(6, null);
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
    public Pet read(Integer id) throws DaoException {
        String sql = "SELECT `name`, `kind`, `age`, `weight`, `event` FROM `mydatabase`.pet_info WHERE `pet_id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            Pet pet = null;
            if (resultSet.next()) {
                pet = new Pet();
                pet.setIdentity(id);
                pet.setAge(resultSet.getInt("age"));
                pet.setWeight(resultSet.getInt("weight"));
                pet.setKind(PetList.getById(resultSet.getInt("kind")));
                pet.setName(resultSet.getString("name"));
                pet.setEventList(null);
            }
            return pet;
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
    public void update(Pet entity) throws DaoException {
        String sql = "UPDATE `mydatabase`.pet_info SET `name` = ?, `age` = ?, `weight` = ?, `kind` = ?, `event` = ? WHERE `pet_id` = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getKind().getIdentity());
            statement.setDouble(3, entity.getWeight());
            statement.setInt(4, entity.getIdentity());
            statement.setInt(5, entity.getAge());
            //TODO event
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
        String sql = "DELETE FROM `mydatabase`.pet_info WHERE `pet_id` = ?";
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

    public List<Event> read(int pet_id) throws DaoException {
        String sql = "SELECT `event` FROM `mydatabase`.pet_info WHERE `pet_id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, pet_id);
            resultSet = statement.executeQuery();
            List<Event> events = null;
            Event event = null;
            while (resultSet.next()) {
                event = new Event();
                event.setIdentity(resultSet.getInt("event"));
                events.add(event);
            }
            return events;
        } catch(SQLException e) {
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
}
