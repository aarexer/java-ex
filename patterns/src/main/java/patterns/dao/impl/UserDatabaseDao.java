package patterns.dao.impl;

import patterns.dao.UserDao;
import patterns.dao.model.User;

import java.sql.*;
import java.util.List;

public class UserDatabaseDao implements UserDao {

    private final Connection connection;

    public UserDatabaseDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User getByLogin(String login) {
        return null;
//        String sql = "SELECT * FROM USERS WHERE id = ?;";
//        try(ResultSet statement = connection.createStatement()) {
//            statement.execute(sql);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return ;
    }

    @Override
    public User getByName(String firstname, String secondname) {
        return null;
    }

    @Override
    public User getById(Long id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public void put(User item) {

    }

    @Override
    public boolean update(User item) {
        return false;
    }

    @Override
    public boolean delete(User item) {
        return false;
    }
}
