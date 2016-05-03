package patterns.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface DaoFactory {
    Connection getConnection() throws SQLException;

    UserDao getUserDao(Connection connection);
}
