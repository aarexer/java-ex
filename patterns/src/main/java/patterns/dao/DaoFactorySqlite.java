package patterns.dao;

import patterns.dao.impl.UserDatabaseDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactorySqlite implements DaoFactory {

    public DaoFactorySqlite() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        String url = "jdbc:sqlite:daoExample.db";
        return DriverManager.getConnection(url);
    }

    @Override
    public UserDao getUserDao(Connection connection) {
        return new UserDatabaseDao(connection);
    }
}
