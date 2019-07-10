package dao;

import java.sql.Connection;

abstract public class BaseDao {
    protected Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
