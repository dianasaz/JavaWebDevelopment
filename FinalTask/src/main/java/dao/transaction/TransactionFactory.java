package dao.transaction;

import dao.DaoException;
import dao.connectionPool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionFactory {
    private static Logger logger = LogManager.getLogger(TransactionFactory.class);
    private Connection connection;

    public TransactionFactory() throws DaoException {
        connection = ConnectionPool.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
        } catch(SQLException e) {
            logger.error("It is impossible to turn off autocommiting for database connection", e);
            throw new DaoException(e);
        }
    }

    public Transaction createTransaction() throws DaoException {
        return new TransactionImpl(connection);
    }

    public void close() {
        try {
            connection.close();
        } catch(SQLException e) {}
    }
}