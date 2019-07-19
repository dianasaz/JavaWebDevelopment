package by.sazanchuk.finalTask.dao;


import by.sazanchuk.finalTask.entity.Coupon;
import by.sazanchuk.finalTask.entity.Doctor;
import by.sazanchuk.finalTask.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class CouponDao extends BaseDao implements Dao<Coupon> {
    private static final String INSERT_ALL_INFO = "INSERT INTO `mydatabase`.coupon (`id`, `user_id`, `doctor_id`, `time`, `taken`) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_NAME = "SELECT `user_id`, `doctor_id`, `time`, `taken` FROM `mydatabase`.coupon WHERE `id` = ?";
    private static final String UPDATE_DOCTOR = "UPDATE `mydatabase`.coupon SET `user_id` = ? && `doctor_id` = ? && `time` = ? && `taken` = ? WHERE `id` = ?";
    private static final String DELETE_BY_IDENTITY = "DELETE FROM `mydatabase`.coupon WHERE `id` = ?";
    private static final String SELECT_ALL_INFO_ORDER_BY_NAME = "SELECT `id`, `user_id`,`doctor_id`, `time`, `taken` FROM `mydatabase`.coupon ORDER BY `name`";

    private final Logger log = LogManager.getLogger(DoctorDao.class);
//TODO sql strings before logger static and final

    //TODO delete nullpointerexception in by.sazanchuk.finalTask.dao by if(result.equels(null)
    @Override
    public Integer create(Coupon entity) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(INSERT_ALL_INFO, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, entity.getIdentity());
            statement.setObject(2, entity.getDoctor());
            statement.setTime(3, (Time) entity.getTime());
            statement.setInt(4, entity.getUser().getId());
            statement.setBoolean(5, entity.isTaken());
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
    public Coupon read(Integer id) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SELECT_NAME);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            Coupon coupon = null;
            if (resultSet.next()) {
                coupon = new Coupon();
                coupon.setIdentity(id);
                coupon.setDoctor((Doctor) resultSet.getObject("doctor_id"));
                coupon.setTaken(resultSet.getBoolean("taken"));
                coupon.setUser((User) resultSet.getObject("user_id"));
                coupon.setTime(resultSet.getTime("time"));

            }
            return coupon;
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
    public void update(Coupon entity) throws DaoException {
        //TODO
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE_DOCTOR);
            statement.setInt(1, entity.getIdentity());
            statement.setObject(2, entity.getDoctor());
            statement.setTime(3, (Time) entity.getTime());
            statement.setInt(4, entity.getUser().getId());
            statement.setBoolean(5, entity.isTaken());
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

    public List<Coupon> read() throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SELECT_ALL_INFO_ORDER_BY_NAME);
            resultSet = statement.executeQuery();
            List<Coupon> cou = new ArrayList<>();
            Coupon coupon = null;
            while(resultSet.next()) {
                coupon = new Coupon();
                coupon.setIdentity(resultSet.getInt("id"));
                coupon.setDoctor((Doctor) resultSet.getObject("doctor_id"));
                coupon.setTaken(resultSet.getBoolean("taken"));
                coupon.setUser((User) resultSet.getObject("user_id"));
                coupon.setTime(resultSet.getTime("time"));
                cou.add(coupon);
            }
            return cou;
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

