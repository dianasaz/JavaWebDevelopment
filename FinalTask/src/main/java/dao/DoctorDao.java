package dao;

import entity.Doctor;
import entity.Service;
import entity.ServiceList;
import exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.print.Doc;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DoctorDao extends BaseDao implements Dao<Doctor> {
    private final Logger log = LogManager.getLogger(DoctorDao.class);

    @Override
    public Integer create(Doctor entity) throws PersistentException {
        String sql = "INSERT INTO `mydatabase`.doctor_info (`doctor_id`, `service`, `name`, `pet`) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, entity.getDoctorID());
            statement.setString(2, entity.getName());
            //TODO servicelist petlist worklist
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
    public Doctor read(Integer id) throws PersistentException {
        String sql = "SELECT `service`, `name`, `pet` FROM `mydatabase`.doctor_info WHERE `doctor_id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            Doctor doctor = null;
            if (resultSet.next()) {
                doctor = new Doctor();
                doctor.setDoctorID(id);
                doctor.setName(resultSet.getString("price"));
                //TODO lists
            }
            return doctor;
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
    public void update(Doctor entity) throws PersistentException {
        String sql = "UPDATE `mydatabase`.doctor_info SET `service` = ?, `name` = ? WHERE `doctor_id` = ?";
        //TODO
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, entity.getDoctorID());
            statement.setString(2, entity.getName());
         //TODO
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
        String sql = "DELETE FROM `mydatabase`.doctor_info WHERE `doctor_id` = ?";
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

