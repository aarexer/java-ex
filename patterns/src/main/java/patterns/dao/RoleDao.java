package patterns.dao;

import patterns.dao.model.Role;

public interface RoleDao extends ItemDao<Role> {

    Role getByTitle(String title);

}
