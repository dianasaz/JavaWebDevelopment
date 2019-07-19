package by.sazanchuk.finalTask.dao;

import by.sazanchuk.finalTask.entity.Doctor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DoctorDao extends BaseDao implements Dao<Doctor> {
    private static final String INSERT_ALL_INFO = "INSERT INTO `mydatabase`.doctor (`id`, `name`) VALUES (?, ?)";
    private static final String SELECT_NAME = "SELECT `name` FROM `mydatabase`.doctor WHERE `id` = ?";
    private static final String UPDATE_DOCTOR = "UPDATE `mydatabase`.doctor SET `name` = ? WHERE `id` = ?";
    private static final String DELETE_BY_IDENTITY = "DELETE FROM `mydatabase`.doctor WHERE `id` = ?";
    private static final String SELECT_ALL_INFO_ORDER_BY_NAME = "SELECT `id`, `name` FROM `mydatabase`.doctor ORDER BY `name`";

    private final Logger log = LogManager.getLogger(DoctorDao.class);
//TODO sql strings before logger static and final

    //TODO delete nullpointerexception in by.sazanchuk.finalTask.dao by if(result.equels(null)
    @Override
    public Integer create(Doctor entity) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(INSERT_ALL_INFO, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, entity.getIdentity());
            statement.setString(2, entity.getName());
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
    public Doctor read(Integer id) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SELECT_NAME);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            Doctor doctor = null;
            if (resultSet.next()) {
                doctor = new Doctor();
                doctor.setIdentity(id);
                doctor.setName(resultSet.getString("price"));
                //TODO lists
            }
            return doctor;
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
    public void update(Doctor entity) throws DaoException {
        //TODO
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE_DOCTOR);
            statement.setInt(1, entity.getIdentity());
            statement.setString(2, entity.getName());
         //TODO
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
            statement = connection.prepareStatement(DELETE_BY_IDENTITY);
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

    public List<Doctor> read() throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SELECT_ALL_INFO_ORDER_BY_NAME);
            resultSet = statement.executeQuery();
            List<Doctor> doc = new ArrayList<>();
            Doctor doctor = null;
            while(resultSet.next()) {
                doctor = new Doctor();
                doctor.setIdentity(resultSet.getInt("doctor_id"));
                doctor.setName(resultSet.getString("name"));
               // doctor.setService(resultSet.getString("by.sazanchuk.finalTask.service"));
               // doctor.setPetList(resultSet.getString("pet"));
               // doctor.setWorkList(resultSet.getString("worklist")););
                doc.add(doctor);
            }
            return doc;
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

