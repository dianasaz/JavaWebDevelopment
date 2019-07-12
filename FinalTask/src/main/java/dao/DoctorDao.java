package dao;

import entity.Doctor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DoctorDao extends BaseDao implements Dao<Doctor> {
    private final Logger log = LogManager.getLogger(DoctorDao.class);
//TODO sql strings before logger static and final

    //TODO delete nullpointerexception in dao by if(result.equels(null)
    @Override
    public Integer create(Doctor entity) throws DaoException {
        String sql = "INSERT INTO `mydatabase`.doctor_info (`doctor_id`, `service`, `name`, `pet`) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, entity.getIdentity());
            statement.setString(2, entity.getName());
            //TODO servicelist petlist worklist
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
    public Doctor read(Integer id) throws DaoException {
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
                doctor.setIdentity(id);
                doctor.setName(resultSet.getString("price"));
                //TODO lists
            }
            return doctor;
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
    public void update(Doctor entity) throws DaoException {
        String sql = "UPDATE `mydatabase`.doctor_info SET `service` = ?, `name` = ? WHERE `doctor_id` = ?";
        //TODO
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, entity.getIdentity());
            statement.setString(2, entity.getName());
         //TODO
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
        String sql = "DELETE FROM `mydatabase`.doctor_info WHERE `doctor_id` = ?";
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

    public List<Doctor> read() throws DaoException {
        //TODO worklist
        String sql = "SELECT `doctor_id`, `service`, `pet` FROM `mydatabase`.doctor_info ORDER BY `name`";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            List<Doctor> doc = new ArrayList<>();
            Doctor doctor = null;
            while(resultSet.next()) {
                doctor = new Doctor();
                doctor.setIdentity(resultSet.getInt("doctor_id"));
                doctor.setName(resultSet.getString("name"));
               // doctor.setService(resultSet.getString("service"));
               // doctor.setPetList(resultSet.getString("pet"));
               // doctor.setWorkList(resultSet.getString("worklist")););
                doc.add(doctor);
            }
            return doc;
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

