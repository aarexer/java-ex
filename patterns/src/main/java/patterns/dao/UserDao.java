package patterns.dao;

import patterns.dao.model.User;

public interface UserDao extends ItemDao<User> {

    User getByLogin(String login);

    User getByName(String firstname, String secondname);

}
