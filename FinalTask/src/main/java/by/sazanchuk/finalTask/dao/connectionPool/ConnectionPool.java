package by.sazanchuk.finalTask.dao.connectionPool;

import com.mysql.jdbc.Driver;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);

    private static final String PROPERTY_PATH = "myDatabase.properties";

    private static final int INITIAL_CAPACITY = 20;

    private ArrayBlockingQueue<Connection> freeConnections = new ArrayBlockingQueue<>(INITIAL_CAPACITY);

    private ArrayBlockingQueue<Connection> releaseConnections = new ArrayBlockingQueue<>(INITIAL_CAPACITY);

    private static ReentrantLock lock = new ReentrantLock();

    private volatile static ConnectionPool connectionPool;

    public static ConnectionPool getInstance() {
        if (connectionPool == null) {
            try {
                lock.lock();
                if (connectionPool == null) {
                    connectionPool = new ConnectionPool();
                }
            } catch (ConnectionPoolException e) {
                LOGGER.error("Can not get Instance", e);
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }

        return connectionPool;
    }

    private ConnectionPool() throws ConnectionPoolException {
        try {
            lock.lock();
            if (connectionPool != null) {
                throw new UnsupportedOperationException();
            } else {
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Can't register driver");
            throw new ConnectionPoolException(e + "Cant' register driver");
        } finally {
            lock.unlock();
        }
        init();
    }

    private void init() throws ConnectionPoolException {

        Properties properties = new Properties();

        try {
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(PROPERTY_PATH);
            properties.load(inputStream);
        } catch (IOException e) {
            LOGGER.error("Error while reading properties", e);
            throw new ConnectionPoolException(e);
        }
        String connectionURL = properties.getProperty("url");
        String initialCapacityString = properties.getProperty("poolSize");
        Integer initialCapacity = Integer.valueOf(initialCapacityString);

        for (int i = 0; i < initialCapacity; i++) {
            try {
                Connection connection = new ConnectingPool(DriverManager.getConnection(connectionURL, properties)) {
                };
                freeConnections.add(connection);
            } catch (SQLException e) {
                LOGGER.error("Pool can not initialize", e);
                throw new RuntimeException("Pool can not initialize", e);
            }
        }

    }

    public Connection getConnection() throws ConnectionPoolException {
        try {
            Connection connection = freeConnections.take();
            releaseConnections.offer(connection);

            return connection;
        } catch (InterruptedException e) {
            throw new ConnectionPoolException("Can not get database", e);
        }

    }

    public void releaseConnection(Connection connection) {
        releaseConnections.remove(connection);
        freeConnections.offer(connection);

    }

    public void destroy() {
        freeConnections.addAll(releaseConnections);
        releaseConnections.clear();

        for (int i = 0; i < freeConnections.size(); i++) {
            try {
                ConnectingPool connection = (ConnectingPool) freeConnections.take();
                connection.realClose();
            } catch (InterruptedException e) {
                LOGGER.error("Connection close exception", e);
            }
        }


        Enumeration<java.sql.Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            java.sql.Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
                LOGGER.log(Level.INFO, String.format("Deregistering jdbc driver: %s", driver));
            } catch (SQLException e) {
                LOGGER.log(Level.ERROR, String.format("Error deregistering driver %s", driver), e);
            }
        }

    }

}
